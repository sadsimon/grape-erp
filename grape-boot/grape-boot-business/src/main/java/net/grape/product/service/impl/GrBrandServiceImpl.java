package net.grape.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.grape.enums.DataStateEnum;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.product.convert.GrBrandConvert;
import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrStoreEntity;
import net.grape.product.mapper.GrBrandMapper;
import net.grape.product.query.BrandQuery;
import net.grape.product.service.IGrBrandService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.BrandVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Service
public class GrBrandServiceImpl extends BaseServiceImpl<GrBrandMapper, GrBrandEntity> implements IGrBrandService {

    @Override
    public PageResult<BrandVO> page(BrandQuery query) {

        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrBrandEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<BrandVO> list = baseMapper.getList(params);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<GrBrandEntity> list(String param) {
        return baseMapper.selectList(getWrapper(param));
    }

    @Override
    public List<GrBrandEntity> getBrandList(int state) {
        return baseMapper.selectList(getWrapper(state));
    }

    private Wrapper<GrBrandEntity> getWrapper(int state) {
        LambdaQueryWrapper<GrBrandEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrBrandEntity::getState, state);
        return wrapper;
    }

    private Wrapper<GrBrandEntity> getWrapper(String param) {
        LambdaQueryWrapper<GrBrandEntity> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(param)) {
            wrapper.eq(GrBrandEntity::getBrandCode, param)
                    .or()
                    .eq(GrBrandEntity::getBrandName, param);
            wrapper.eq(GrBrandEntity:: getState,DataStateEnum.STATE_EFFECTIVE);
        }
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BrandVO vo) {
        GrBrandEntity entity = GrBrandConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BrandVO vo) {
        GrBrandEntity entity = GrBrandConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    private Map<String, Object> getParams(BrandQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("param", query.getParam());
        return params;
    }
}
