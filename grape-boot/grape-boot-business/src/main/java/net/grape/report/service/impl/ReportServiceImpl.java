package net.grape.report.service.impl;

import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import net.grape.report.mapper.ReportMapper;
import net.grape.report.service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.DateTime;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;

    @Override
    public Map getHomeData(String type) {
        //公式：（总销售额-总成本） -（总退货额-退的货的成本）
        DateTime documentTimeStart = null;
        DateTime documentTimeEnd = null;
        DateTime now = DateUtil.date();
        if ("today".equals(type)){
            documentTimeStart = DateUtil.beginOfDay(now);
            documentTimeEnd = DateUtil.endOfDay(now);
        }else if ("week".equals(type)){
            documentTimeStart = DateUtil.beginOfWeek(now);
            documentTimeEnd = DateUtil.endOfWeek(now);
        }else if ("month".equals(type)){
            documentTimeStart = DateUtil.beginOfMonth(now);
            documentTimeEnd = DateUtil.endOfMonth(now);
        }else if ("year".equals(type)){
            documentTimeStart = DateUtil.beginOfYear(now);
            documentTimeEnd = DateUtil.endOfYear(now);
        }

        //获取销售的商品和个数
        List<Map> list = reportMapper.getSaleCountProduct("13",documentTimeStart,documentTimeEnd);
        BigDecimal costAll = new BigDecimal(0);
        for (Map map : list) {
            //销售的商品
            Long productId = (Long) map.get("product_id");
            BigDecimal quantity = (BigDecimal) map.get("quantity");
            //根获每个product_id的成本价
            Map costMap = reportMapper.getCost(productId,documentTimeStart,documentTimeEnd);
            BigDecimal cost = new BigDecimal(0);
            if (costMap != null){
                cost = (BigDecimal) costMap.get("cost");
            }
            //总成本=成本价*销售个数
            BigDecimal costById = NumberUtil.mul(cost, quantity);
            costAll = NumberUtil.add(costAll, costById);
        }

        //获取退货的商品和个数
        List<Map> listReturn = reportMapper.getSaleCountProduct("14",documentTimeStart,documentTimeEnd);
        BigDecimal costReturnAll = new BigDecimal(0);
        for (Map map : listReturn) {
            //销售的商品
            Long productId = (Long) map.get("product_id");
            BigDecimal quantity = (BigDecimal) map.get("quantity");
            //根获每个product_id的成本价
            Map costMap = reportMapper.getCost(productId,documentTimeStart,documentTimeEnd);
            BigDecimal cost = (BigDecimal) costMap.get("cost");
            //总成本=成本价*销售个数
            BigDecimal costById = NumberUtil.mul(cost, quantity);
            costReturnAll = NumberUtil.add(costReturnAll, costById);
        }
        Map map = reportMapper.getHomeData(null,documentTimeStart,documentTimeEnd);
        BigDecimal a = NumberUtil.sub((BigDecimal)map.get("sale_amount"),costAll);
        BigDecimal b = NumberUtil.sub((BigDecimal)map.get("return_amount"),costReturnAll);
        //总利润=（总销售额-总成本） -（总退货额-退的货的成本）
        map.put("gross_margin",
                NumberUtil.sub(
                        a,
                        b

        ));
        List<Map<String, Object>> listSaleAmount = reportMapper.saleAmountLine(type,documentTimeStart,documentTimeEnd);
        List listAmount = makeData(type);
        if (listSaleAmount != null && !list.stream().allMatch(Objects::isNull)){
            for(Map<String, Object> map2 : listSaleAmount){
                //小时有0时，周月年没有0所以要删除第一个值
                listAmount.set(Integer.parseInt(map2.get("document_time").toString())-("today".equals(type)?0:1),map2.get("sale_amount"));
            }
        }

        map.put("sales_list",listAmount);
        map.put("sale_amount",NumberUtil.sub((BigDecimal)map.get("sale_amount"),(BigDecimal)map.get("return_amount")));
        return map;
    }

    private List makeData(String type){
        int length = 0;
        if ("today".equals(type)){
            length = 24;
        }else if ("week".equals(type)){
            length = 7;
        }else if ("month".equals(type)){
            length = 31;
        }else if ("year".equals(type)){
            length = 12;
        }
        List list  = new ArrayList();
        for (int i=0;i<length;i++){
            list.add(0);
        }
        return list;
    }

}
