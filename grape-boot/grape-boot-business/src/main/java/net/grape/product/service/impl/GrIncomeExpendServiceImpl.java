package net.grape.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.grape.enums.DataStateEnum;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.product.convert.GrIncomeExpendConvert;
import net.grape.product.entity.GrIncomeExpendEntity;
import net.grape.product.mapper.GrIncomeExpendMapper;
import net.grape.product.query.IncomeExpendQuery;
import net.grape.product.service.IGrIncomeExpendService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrIncomeExpendVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收支项目 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-05
 */
@Service
public class GrIncomeExpendServiceImpl extends BaseServiceImpl<GrIncomeExpendMapper, GrIncomeExpendEntity> implements IGrIncomeExpendService {

    @Override
    public PageResult<GrIncomeExpendVO> page(IncomeExpendQuery query) {

        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrIncomeExpendEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<GrIncomeExpendVO> list = baseMapper.getList(params);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<GrIncomeExpendEntity> list(String param) {
        return baseMapper.selectList(getWrapper(param));
    }

    @Override
    public List<GrIncomeExpendEntity> getIncomeExpendList(int state, String amountType) {
        return baseMapper.selectList(getWrapper(state, amountType));
    }

    private Wrapper<GrIncomeExpendEntity> getWrapper(int state, String amountType) {
        LambdaQueryWrapper<GrIncomeExpendEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrIncomeExpendEntity::getState, state);
        wrapper.eq(StrUtil.isNotBlank(amountType),GrIncomeExpendEntity::getAmountType, amountType);
        return wrapper;
    }

    private Wrapper<GrIncomeExpendEntity> getWrapper(String param) {
        LambdaQueryWrapper<GrIncomeExpendEntity> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(param)) {
            wrapper.eq(GrIncomeExpendEntity::getName, param);
            wrapper.eq(GrIncomeExpendEntity:: getState, DataStateEnum.STATE_EFFECTIVE);
        }
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GrIncomeExpendVO vo) {
        GrIncomeExpendEntity entity = GrIncomeExpendConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GrIncomeExpendVO vo) {
        GrIncomeExpendEntity entity = GrIncomeExpendConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    private Map<String, Object> getParams(IncomeExpendQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("param", query.getParam());
        return params;
    }
}
