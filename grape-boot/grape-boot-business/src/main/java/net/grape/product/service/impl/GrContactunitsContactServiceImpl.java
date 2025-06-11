package net.grape.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.product.convert.GrContactunitsContactConvert;
import net.grape.product.entity.GrContactunitsContactEntity;
import net.grape.product.mapper.GrContactunitsContactMapper;
import net.grape.product.service.IGrContactunitsContactService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.ContactunitsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 往来单位联系人 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Service
public class GrContactunitsContactServiceImpl extends BaseServiceImpl<GrContactunitsContactMapper, GrContactunitsContactEntity> implements IGrContactunitsContactService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrupdate(List<ContactunitsVO.GrContactunitsContact> list, Long contactunitsId) {
        list.stream().forEach(contact -> contact.setContactunitsId(contactunitsId));
        List<GrContactunitsContactEntity> entities = GrContactunitsContactConvert.INSTANCE.convertList(list);
        baseMapper.insertOrUpdate(entities,5);
    }

    @Override
    public List<ContactunitsVO.GrContactunitsContact> findByContactunitsId(Long contactunitsId) {
        List<GrContactunitsContactEntity> list = baseMapper.selectList(getWrapper(contactunitsId));
        return GrContactunitsContactConvert.INSTANCE.convertList2(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    private Wrapper<GrContactunitsContactEntity> getWrapper(Long contactunitsId) {
        LambdaQueryWrapper<GrContactunitsContactEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(contactunitsId != null, GrContactunitsContactEntity::getContactunitsId, contactunitsId);
        return wrapper;
    }
}
