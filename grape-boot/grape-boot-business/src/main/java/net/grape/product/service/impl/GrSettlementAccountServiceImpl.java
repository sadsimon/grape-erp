package net.grape.product.service.impl;

import net.grape.enums.DataStateEnum;
import net.grape.product.convert.GrBrandConvert;
import net.grape.product.convert.GrSettlementAccountConvert;
import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrSettlementAccountEntity;
import net.grape.product.mapper.GrSettlementAccountMapper;
import net.grape.product.service.IGrSettlementAccountService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrSettlementAccountVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 结算账户 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-06
 */
@Service
public class GrSettlementAccountServiceImpl extends BaseServiceImpl<GrSettlementAccountMapper, GrSettlementAccountEntity> implements IGrSettlementAccountService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GrSettlementAccountVO vo) {
        GrSettlementAccountEntity entity = GrSettlementAccountConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GrSettlementAccountVO vo) {
        GrSettlementAccountEntity entity = GrSettlementAccountConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
