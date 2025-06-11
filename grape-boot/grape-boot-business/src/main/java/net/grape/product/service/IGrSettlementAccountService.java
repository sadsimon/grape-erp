package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrSettlementAccountEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.GrSettlementAccountVO;

import java.util.List;

/**
 * <p>
 * 结算账户 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-06
 */
public interface IGrSettlementAccountService extends BaseService<GrSettlementAccountEntity> {

    void save(GrSettlementAccountVO vo);

    void update(GrSettlementAccountVO vo);

    void delete(List<Long> idList);
}
