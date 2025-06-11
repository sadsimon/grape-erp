package net.grape.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.order.convert.GrDocumentAccountDetailConvert;
import net.grape.order.entity.GrDocumentAccountDetailEntity;
import net.grape.order.mapper.GrDocumentAccountDetailMapper;
import net.grape.order.service.IGrDocumentAccountDetailService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 结算单据支付账号详情 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Service
public class GrDocumentAccountDetailServiceImpl extends BaseServiceImpl<GrDocumentAccountDetailMapper, GrDocumentAccountDetailEntity> implements IGrDocumentAccountDetailService {

    @Override
    public List<GrDocumentAccountDetailVO> getlistByDocumentId(Long documentId) {
        List<GrDocumentAccountDetailVO> list = baseMapper.getlistByDocumentId(documentId);
        return list;
    }

    @Override
    public List<GrDocumentAccountDetailVO> getlistByDocumentCode(String documentCode) {
        List<GrDocumentAccountDetailVO> list = baseMapper.getlistByDocumentCode(documentCode);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(List<GrDocumentAccountDetailVO> list, Long documentId) {
        //先删除历史
        deleteByDocumentId(documentId);
        if (list != null && list.size() > 0) {
            list.stream().forEach(entity -> {entity.setDocumentId(documentId); entity.setId(null);});
            baseMapper.insertOrUpdate(GrDocumentAccountDetailConvert.INSTANCE.convertList1(list));
        }
    }

    @Override
    public void deleteByDocumentId(Long documentId) {
        List<GrDocumentAccountDetailEntity> detailList = baseMapper.selectList(getWrapper(documentId));
        delete(detailList.stream().map(GrDocumentAccountDetailEntity::getId).toList());
    }

    private Wrapper<GrDocumentAccountDetailEntity> getWrapper(Long documentId) {
        LambdaQueryWrapper<GrDocumentAccountDetailEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(documentId != null,GrDocumentAccountDetailEntity::getDocumentId, documentId);
        return wrapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
