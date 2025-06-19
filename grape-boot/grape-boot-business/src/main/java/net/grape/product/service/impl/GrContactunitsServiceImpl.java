package net.grape.product.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.order.mapper.GrDocumentAccountDetailMapper;
import net.grape.order.mapper.GrDocumentMapper;
import net.grape.product.convert.GrContactunitsConvert;
import net.grape.product.entity.GrContactunitsEntity;
import net.grape.product.mapper.GrContactunitsMapper;
import net.grape.product.query.ContactunitsQuery;
import net.grape.product.service.IGrContactunitsService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.ContactunitsListVO;
import net.grape.product.vo.ContactunitsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Service
@AllArgsConstructor
public class GrContactunitsServiceImpl extends BaseServiceImpl<GrContactunitsMapper, GrContactunitsEntity> implements IGrContactunitsService {

    private final GrDocumentMapper grDocumentMapper;

    private final GrDocumentAccountDetailMapper grDocumentAccountDetailMapper;

    @Override
    public PageResult<ContactunitsListVO> page(ContactunitsQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrContactunitsEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<ContactunitsListVO> list = baseMapper.getList(params);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ContactunitsVO vo) {
        GrContactunitsEntity entity = GrContactunitsConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);
        vo.setId(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ContactunitsVO vo) {
        GrContactunitsEntity entity = GrContactunitsConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    private Map<String, Object> getParams(ContactunitsQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryIdList", query.getCategoryIdList());
        params.put("param", query.getParam());
        params.put("type", query.getType());

        // 数据权限
        params.put(Constant.DATA_SCOPE, getDataScope("t1", null));

        // 机构过滤
        /*if (query.getOrgId() != null) {
            params.put("orgList", query.getOrgId());
        }*/

        return params;
    }

    @Override
    public List<String> getContactunitsNameList(List<Long> idList) {
        if (idList.isEmpty()) {
            return null;
        }

        return baseMapper.selectBatchIds(idList).stream().map(GrContactunitsEntity::getContactunitsName).toList();
    }

    /**
     * 此前应付
     * @param contactunitsId、
     * （总应该付款-总退货应该付款）-（总已收款-总已退款）
     * @return
     */
    @Override
    public BigDecimal getHistoryPayAmount(Long contactunitsId) {
        //总应该付款
        Map needAmountMap = grDocumentMapper.getSumAmount(contactunitsId,"2");
        BigDecimal needAmount = new BigDecimal(0);
        if(needAmountMap != null) {
            needAmount = (BigDecimal) needAmountMap.get("should_amount");
        }

        //总退货应该付款
        Map needReturnAmountMap = grDocumentMapper.getSumAmount(contactunitsId,"1");
        BigDecimal needReturnAmount = new BigDecimal(0);
        if(needReturnAmountMap != null) {
            needReturnAmount = (BigDecimal) needReturnAmountMap.get("should_amount");
        }

        //总已收款
        Map alreadyAmountMap = grDocumentAccountDetailMapper.getSumPayAmount(contactunitsId,"2");
        BigDecimal alreadyAmount = new BigDecimal(0);
        if(alreadyAmountMap != null) {
            alreadyAmount = (BigDecimal) alreadyAmountMap.get("pay_amount");
        }

        //总已退款
        Map alreadyReturnAmountMap = grDocumentAccountDetailMapper.getSumPayAmount(contactunitsId,"1");
        BigDecimal alreadyReturnAmount = new BigDecimal(0);
        if(alreadyReturnAmountMap != null) {
            alreadyReturnAmount = (BigDecimal) alreadyReturnAmountMap.get("pay_amount");
        }
        log.debug("needAmount:"+needAmount.toString());
        log.debug("alreadyAmount:"+alreadyAmount.toString());
        return NumberUtil.sub(NumberUtil.sub(needAmount,needReturnAmount),NumberUtil.sub(alreadyAmount,alreadyReturnAmount));
    }

    /**
     * 此前应收
     * @param contactunitsId
     * @return
     */
    @Override
    public BigDecimal getHistoryReceivePayment(Long contactunitsId) {
        return this.getHistoryPayAmount(contactunitsId).negate();
    }

    @Override
    public void updateAdvance(Long contactunitsId, BigDecimal amount, String documentType) {
        if ("38".equals(documentType)) {
            GrContactunitsEntity grContactunitsEntity = getById(contactunitsId);
            //单据预付款，就是供应商的预收款
            grContactunitsEntity.setAdvanceIn(NumberUtil.add(grContactunitsEntity.getAdvanceIn(),amount));
            updateById(grContactunitsEntity);
        }else if("39".equals(documentType)){
            GrContactunitsEntity grContactunitsEntity = getById(contactunitsId);
            //单据预收款款，就是供应商的预付款
            grContactunitsEntity.setAdvanceOut(NumberUtil.add(grContactunitsEntity.getAdvanceOut(),amount));
            updateById(grContactunitsEntity);
        }
    }

    @Override
    public void returnAdvance(Long contactunitsId, BigDecimal amount, String documentType) {
        if ("38".equals(documentType)) {
            GrContactunitsEntity grContactunitsEntity = getById(contactunitsId);
            //单据预付款，就是供应商的预收款
            grContactunitsEntity.setAdvanceIn(NumberUtil.sub(grContactunitsEntity.getAdvanceIn(),amount));
            updateById(grContactunitsEntity);
        } else if ("39".equals(documentType)) {
            GrContactunitsEntity grContactunitsEntity = getById(contactunitsId);
            //单据预收款款，就是供应商的预付款
            grContactunitsEntity.setAdvanceOut(NumberUtil.sub(grContactunitsEntity.getAdvanceOut(),amount));
            updateById(grContactunitsEntity);
        }
    }
}
