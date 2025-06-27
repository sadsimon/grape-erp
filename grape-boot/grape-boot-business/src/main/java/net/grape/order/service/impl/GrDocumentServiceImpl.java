package net.grape.order.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.grape.enums.DocumentEnum;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.order.convert.GrDocumentConvert;
import net.grape.order.entity.GrDocumentEntity;
import net.grape.order.mapper.GrDocumentMapper;
import net.grape.order.query.DocumentQuery;
import net.grape.order.service.IGrDocumentAccountDetailService;
import net.grape.order.service.IGrDocumentDetailService;
import net.grape.order.service.IGrDocumentService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.order.service.IGrDocumentSettleDetailService;
import net.grape.order.service.impl.documentHandler.Document;
import net.grape.order.service.impl.documentHandler.DocumentConfig;
import net.grape.order.service.impl.documentHandler.ExecuteFactory;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;
import net.grape.product.service.IGrContactunitsService;
import net.grape.stock.service.IGrCurrentStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 单据 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@AllArgsConstructor
@Service
public class GrDocumentServiceImpl extends BaseServiceImpl<GrDocumentMapper, GrDocumentEntity> implements IGrDocumentService {

    private final IGrDocumentDetailService iGrDocumentDetailService;
    private final IGrCurrentStockService iGrCurrentStockService;
    private final IGrDocumentSettleDetailService iGrDocumentSettleDetailService;
    private final IGrDocumentAccountDetailService iGrDocumentAccountDetailService;
    private final IGrContactunitsService iGrContactunitsService;
    //private final ExecuteFactory executeFactory;

    @Override
    public PageResult<GrDocumentVO> page(DocumentQuery query) {
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrDocumentEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<GrDocumentVO> list = baseMapper.getDocumentList(params);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public PageResult<GrDocumentVO> pageCheckList(DocumentQuery query) {

        Map<String, Object> params = getParams(query);
        // 分页查询
        IPage<GrDocumentEntity> page = getPage(query);
        params.put(Constant.PAGE, page);
        List<GrDocumentVO> list = baseMapper.checkList(params);
        list.stream().forEach(vo ->{vo.setPendingAmount(this.getPendingAmount(vo.getDocumentCode(),vo.getFinalAmount()));});
        return new PageResult<>(list, page.getTotal());
    }

    private BigDecimal getPendingAmount(String documentCode,BigDecimal finalAmount){
        List<GrDocumentSettleDetailVO> settleDetailList = iGrDocumentSettleDetailService.getlistByDocumentCode(documentCode);
        BigDecimal settlePay = settleDetailList.stream().map(GrDocumentSettleDetailVO::getPaymentAmount).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return NumberUtil.sub(finalAmount,settlePay);

    }

    private Map<String, Object> getParams(DocumentQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("documentCode", query.getDocumentCode());
        params.put("contactunitsId", query.getContactunitsId());
        params.put("userId", query.getUserId());
        params.put("types", query.getTypes());
        params.put("module", query.getModule());
        if(query.getDocumentTimes() != null && query.getDocumentTimes().size()==2) {
            params.put("documentTimeStart", query.getDocumentTimes().get(0));
            params.put("documentTimeEnd", query.getDocumentTimes().get(1));
        }
        return params;
    }

    @Override
    public List<GrDocumentEntity> list(String param) {
        return List.of();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GrDocumentVO vo) {
        DocumentConfig documentConfig = ExecuteFactory.getDocument(vo).getDocumentConfig();
        vo = documentConfig.getDocumentVO();
        GrDocumentEntity grDocumentEntity = GrDocumentConvert.INSTANCE.convert(vo);
        baseMapper.insert(grDocumentEntity);
        iGrDocumentDetailService.saveOrUpdateList(vo.getDocumentDetailList(), vo.getDocumentCode(), grDocumentEntity.getId());
        //计算和保存库存
        if(vo.getDocumentDetailList() != null && !vo.getDocumentDetailList().isEmpty() && documentConfig.getIsNeedStock()) {
            GrDocumentVO finalVo = vo;
            vo.getDocumentDetailList().stream().forEach(detail -> {
                iGrCurrentStockService.updateStock(finalVo.getDocumentType(), detail.getProductId(), detail.getStoreId(), detail.getInStoreId(), detail.getUnitId(), detail.getQuantity(),true);
            });
        }
        //单据付款详情
        vo.setId(grDocumentEntity.getId());
        iGrDocumentSettleDetailService.saveOrUpdateList(vo.getDocumentSettleDetailList(),grDocumentEntity.getId());
        //账户收款/扣款
        iGrDocumentAccountDetailService.saveOrUpdateList(vo.getDocumentAccountDetailList(),grDocumentEntity.getId());
        //扣往来单位的预付款、预收款（单据的预付款==往来单位的预收款，反之亦然）
        iGrContactunitsService.updateAdvanceIn(vo.getContactunitsId(), vo.getAdvanceOut(), vo.getDocumentType());
        iGrContactunitsService.updateAdvanceIOut(vo.getContactunitsId(), vo.getAdvanceIn(), vo.getDocumentType());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GrDocumentVO vo) {
        DocumentConfig documentConfig = ExecuteFactory.getDocument(vo).getDocumentConfig();
        vo = documentConfig.getDocumentVO();
        //还原库存
        returnStock(vo.getId(),documentConfig.getIsNeedStock());
        //还原预付款/预收款
        returnAdvance(vo.getId(),vo.getContactunitsId(), vo.getDocumentType());

        GrDocumentEntity grDocumentEntity = GrDocumentConvert.INSTANCE.convert(vo);
        updateById(grDocumentEntity);
        iGrDocumentDetailService.saveOrUpdateList(vo.getDocumentDetailList(),vo.getDocumentCode(), grDocumentEntity.getId());
        //计算库存和保存库存
        if(vo.getDocumentDetailList() != null && !vo.getDocumentDetailList().isEmpty() && documentConfig.getIsNeedStock()) {
            GrDocumentVO finalVo = vo;
            vo.getDocumentDetailList().stream().forEach(detail -> {
                iGrCurrentStockService.updateStock(finalVo.getDocumentType(),detail.getProductId(), detail.getStoreId(), detail.getInStoreId(), detail.getUnitId(), detail.getQuantity(),true);
            });
        }
        //单据付款详情
        vo.setId(grDocumentEntity.getId());
        iGrDocumentSettleDetailService.saveOrUpdateList(vo.getDocumentSettleDetailList(), grDocumentEntity.getId());
        //账户收款/扣款
        iGrDocumentAccountDetailService.saveOrUpdateList(vo.getDocumentAccountDetailList(), grDocumentEntity.getId());
        //扣往来单位的预付款、预收款（单据的预付款==往来单位的预收款，反之亦然）
        iGrContactunitsService.updateAdvanceIn(vo.getContactunitsId(), vo.getAdvanceOut(), vo.getDocumentType());
        iGrContactunitsService.updateAdvanceIOut(vo.getContactunitsId(), vo.getAdvanceIn(), vo.getDocumentType());
    }

    private void returnStock(Long id,boolean isNeedStock){
        GrDocumentEntity entityOld = baseMapper.selectById(id);
        List<GrDocumentDetailVO> detailVOListOld = iGrDocumentDetailService.getlistByDocumentId(id);
        if(detailVOListOld != null && !detailVOListOld.isEmpty() && isNeedStock) {
            detailVOListOld.stream().forEach(detail -> {
                iGrCurrentStockService.updateStock(entityOld.getDocumentType(), detail.getProductId(), detail.getStoreId(), detail.getInStoreId(), detail.getUnitId(), detail.getQuantity(), false);
            });
        }
    }

    private void returnAdvance(Long id, Long contactunitsId, String documentType){
        List<GrDocumentAccountDetailVO> accountDetailList = iGrDocumentAccountDetailService.getlistByDocumentId(id);
        BigDecimal advanceOut = accountDetailList.stream()
                .filter(detail -> "2".equals(detail.getAccountType()))
                .map(accountDetail -> accountDetail.getAmount())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal advanceIn = accountDetailList.stream()
                .filter(detail -> "3".equals(detail.getAccountType()))
                .map(accountDetail -> accountDetail.getAmount())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //单据的预付款==往来单位的预收款，反之亦然
        iGrContactunitsService.returnAdvanceIn(contactunitsId, advanceOut, documentType);
        iGrContactunitsService.returnAdvanceIOut(contactunitsId, advanceIn, documentType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        for(Long id : idList){
            //回退库存
            GrDocumentEntity entity = getById(id);
            Document document = ExecuteFactory.getDocument(GrDocumentConvert.INSTANCE.convert(entity));
            returnStock(id,document.getDocumentConfig().getIsNeedStock());
            //回退预付款、预收款
            returnAdvance(id,document.getDocumentConfig().getDocumentVO().getContactunitsId(),document.getDocumentConfig().getDocumentVO().getDocumentType());
            //删除详情
            iGrDocumentDetailService.deleteByDocumentId(id);
            iGrDocumentSettleDetailService.deleteByDocumentId(id);
            iGrDocumentAccountDetailService.deleteByDocumentId(id);
        }
        removeByIds(idList);
    }

    @Override
    public GrDocumentVO getDocumentById(Long id) {
        GrDocumentEntity documentEntity = baseMapper.selectById(id);
        GrDocumentVO vo = GrDocumentConvert.INSTANCE.convert(documentEntity);
        vo.setDocumentDetailList(iGrDocumentDetailService.getlistByDocumentId(id));
        vo.setDocumentSettleDetailList(iGrDocumentSettleDetailService.getlistByDocumentId(id));
        vo.setDocumentAccountDetailList(iGrDocumentAccountDetailService.getlistByDocumentId(id));
        return vo;
    }
}
