package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrIncomeExpendEntity;
import net.grape.framework.mybatis.service.BaseService;

import net.grape.product.query.IncomeExpendQuery;
import net.grape.product.vo.GrIncomeExpendVO;

import java.util.List;

/**
 * <p>
 * 收支项目 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-05
 */
public interface IGrIncomeExpendService extends BaseService<GrIncomeExpendEntity> {

    PageResult<GrIncomeExpendVO> page(IncomeExpendQuery query);

    List<GrIncomeExpendEntity> list(String param);

    List<GrIncomeExpendEntity> getIncomeExpendList(int state, String amountType);

    void save(GrIncomeExpendVO vo);

    void update(GrIncomeExpendVO vo);

    void delete(List<Long> idList);

}
