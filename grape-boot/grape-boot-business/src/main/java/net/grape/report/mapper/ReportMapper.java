package net.grape.report.mapper;

import net.grape.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper extends BaseDao{

    Map getHomeData(Long productId, Date documentTimeStart, Date documentTimeEnd);
    Map getCost(Long productId, Date documentTimeStart, Date documentTimeEnd);
    List<Map> getSaleCountProduct(String documentType, Date documentTimeStart, Date documentTimeEnd);
    List<Map<String, Object>> saleAmountLine(String type, Date documentTimeStart, Date documentTimeEnd);
}
