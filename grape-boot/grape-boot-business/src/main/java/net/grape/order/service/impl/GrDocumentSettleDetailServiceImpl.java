package net.grape.order.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import net.grape.order.convert.GrDocumentAccountDetailConvert;
import net.grape.order.convert.GrDocumentSettleDetailConvert;
import net.grape.order.entity.GrDocumentAccountDetailEntity;
import net.grape.order.entity.GrDocumentSettleDetailEntity;
import net.grape.order.mapper.GrDocumentMapper;
import net.grape.order.mapper.GrDocumentSettleDetailMapper;
import net.grape.order.service.IGrDocumentAccountDetailService;
import net.grape.order.service.IGrDocumentService;
import net.grape.order.service.IGrDocumentSettleDetailService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单据结算 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Service
@AllArgsConstructor
public class GrDocumentSettleDetailServiceImpl extends BaseServiceImpl<GrDocumentSettleDetailMapper, GrDocumentSettleDetailEntity> implements IGrDocumentSettleDetailService {

    private final GrDocumentMapper grDocumentMapper;

    private final IGrDocumentAccountDetailService iGrDocumentAccountDetailService;

    @Override
    public List<GrDocumentSettleDetailVO> getlistByDocumentId(Long documentId) {
        List<GrDocumentSettleDetailEntity> list = baseMapper.selectList(getWrapper(documentId,null));
        return GrDocumentSettleDetailConvert.INSTANCE.convertList(list);
    }

    @Override
    public List<GrDocumentSettleDetailVO> getlistByDocumentCode(String documentCode) {
        List<GrDocumentSettleDetailEntity> list = baseMapper.selectList(getWrapper(null,documentCode));
        return GrDocumentSettleDetailConvert.INSTANCE.convertList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(List<GrDocumentSettleDetailVO> list, Long documentId) {
        //先删除历史
        deleteByDocumentId(documentId);
        if (list != null && list.size() > 0) {
            list.stream().forEach(entity -> {entity.setDocumentId(documentId);entity.setId(null);});
            baseMapper.insertOrUpdate(GrDocumentSettleDetailConvert.INSTANCE.convertList1(list));
        }
    }

    @Override
    public void deleteByDocumentId(Long documentId) {
        List<GrDocumentSettleDetailEntity> detailList = baseMapper.selectList(getWrapper(documentId,null));
        delete(detailList.stream().map(GrDocumentSettleDetailEntity::getId).toList());
    }

    private Wrapper<GrDocumentSettleDetailEntity> getWrapper(Long documentId, String documentCode) {
        LambdaQueryWrapper<GrDocumentSettleDetailEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(documentId != null,GrDocumentSettleDetailEntity::getDocumentId, documentId);
        wrapper.eq(StrUtil.isNotBlank(documentCode),GrDocumentSettleDetailEntity::getDocumentCode, documentCode);
        return wrapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
