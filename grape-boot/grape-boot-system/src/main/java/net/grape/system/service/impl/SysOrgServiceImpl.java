package net.grape.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.TreeUtils;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysOrgConvert;
import net.grape.system.dao.SysOrgDao;
import net.grape.system.dao.SysUserDao;
import net.grape.system.entity.SysOrgEntity;
import net.grape.system.entity.SysUserEntity;
import net.grape.system.service.SysOrgService;
import net.grape.system.vo.SysOrgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author 1161095164@qq.com
 *  
 */
@Service
@AllArgsConstructor
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {
    private final SysUserDao sysUserDao;

    @Override
    public List<SysOrgVO> getList() {
        Map<String, Object> params = new HashMap<>();

        // 数据权限
        params.put(Constant.DATA_SCOPE, getDataScope("t1", "id"));

        // 机构列表
        List<SysOrgEntity> entityList = baseMapper.getList(params);

        return TreeUtils.build(SysOrgConvert.INSTANCE.convertList(entityList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysOrgVO vo) {
        SysOrgEntity entity = SysOrgConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysOrgVO vo) {
        SysOrgEntity entity = SysOrgConvert.INSTANCE.convert(vo);

        // 上级机构不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new ServerException("上级机构不能为自身");
        }

        // 上级机构不能为下级
        List<Long> subOrgList = getSubOrgIdList(entity.getId());
        if (subOrgList.contains(entity.getPid())) {
            throw new ServerException("上级机构不能为下级");
        }

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // 判断是否有子机构
        long orgCount = count(new QueryWrapper<SysOrgEntity>().eq("pid", id));
        if (orgCount > 0) {
            throw new ServerException("请先删除子机构");
        }

        // 判断机构下面是否有用户
        long userCount = sysUserDao.selectCount(new QueryWrapper<SysUserEntity>().eq("org_id", id));
        if (userCount > 0) {
            throw new ServerException("机构下面有用户，不能删除");
        }

        // 删除
        removeById(id);
    }

    @Override
    public List<Long> getSubOrgIdList(Long id) {
        // 所有机构的id、pid列表
        List<SysOrgEntity> orgList = baseMapper.getIdAndPidList();

        // 递归查询所有子机构ID列表
        List<Long> subIdList = new ArrayList<>();
        getTree(id, orgList, subIdList);

        // 本机构也添加进去
        subIdList.add(id);

        return subIdList;
    }

    @Override
    public List<String> getNameList(List<Long> idList) {
        if (idList.isEmpty()) {
            return null;
        }

        return baseMapper.selectBatchIds(idList).stream().map(SysOrgEntity::getName).toList();
    }

    private void getTree(Long id, List<SysOrgEntity> orgList, List<Long> subIdList) {
        for (SysOrgEntity org : orgList) {
            if (ObjectUtil.equals(org.getPid(), id)) {
                getTree(org.getId(), orgList, subIdList);

                subIdList.add(org.getId());
            }
        }
    }
}
