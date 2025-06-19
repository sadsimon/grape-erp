package net.grape.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import net.grape.order.convert.GrDocumentDetailConvert;
import net.grape.order.entity.GrDocumentDetailEntity;
import net.grape.order.mapper.GrDocumentDetailMapper;
import net.grape.order.service.IGrDocumentDetailService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.product.service.IGrProductImgService;
import net.grape.product.vo.GrProductImgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 单据详情 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@Service
@AllArgsConstructor
public class GrDocumentDetailServiceImpl extends BaseServiceImpl<GrDocumentDetailMapper, GrDocumentDetailEntity> implements IGrDocumentDetailService {

    private final IGrProductImgService iGrProductImgService;

    @Override
    public List<GrDocumentDetailVO> getlist(String documentCode) {
        List<GrDocumentDetailVO> list = baseMapper.getlist(documentCode,null);
        list.stream().forEach(documentDetailVO -> {
            documentDetailVO.setImgList(iGrProductImgService.getImgList(documentDetailVO.getProductId()).stream().map(GrProductImgVO::getUrl).toList());
        });
        return list;
    }

    @Override
    public List<GrDocumentDetailVO> getlistByDocumentId(Long documentId) {
        List<GrDocumentDetailVO> list = baseMapper.getlist(null,documentId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(List<GrDocumentDetailVO> list, String documentCode, Long documentId){
        //先删除历史
        deleteByDocumentId(documentId);
        if (list != null && list.size() > 0) {
            list.stream().forEach(entity -> {entity.setDocumentCode(documentCode);
                entity.setDocumentId(documentId);
                entity.setId(null);});
            baseMapper.insertOrUpdate(GrDocumentDetailConvert.INSTANCE.convertList1(list));
        }
    }

    @Override
    public void deleteByDocumentId(Long documentId) {
        List<GrDocumentDetailEntity> detailList = baseMapper.selectList(getWrapper(null , documentId));
        delete(detailList.stream().map(GrDocumentDetailEntity::getId).toList());
    }

    private Wrapper<GrDocumentDetailEntity> getWrapper(String documentCode, Long documentId) {
        LambdaQueryWrapper<GrDocumentDetailEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtil.isNotBlank(documentCode),GrDocumentDetailEntity::getDocumentCode, documentCode);
        wrapper.eq(documentId != null,GrDocumentDetailEntity::getDocumentId, documentId);
        return wrapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
