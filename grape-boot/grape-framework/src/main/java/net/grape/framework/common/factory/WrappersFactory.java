package net.grape.framework.common.factory;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.grape.framework.mybatis.handler.FieldMetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 更新方法：null会被覆盖方法
 */
@Component
public class WrappersFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> LambdaUpdateWrapper<T> updateWithNullField(T entity) {
        // 获取实体类的表信息
        TableInfoHelper.getTableInfo(entity.getClass());

        // 创建 UpdateWrapper
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        List<Field> allFields = TableInfoHelper.getAllFields(entity.getClass());
        MetaObject metaObject = SystemMetaObject.forObject(entity);

        // 遍历所有字段，包括 null 值字段
        for (Field field : allFields) {
            Object value = metaObject.getValue(field.getName());
            // 将字段值设置到 UpdateWrapper 中
            updateWrapper.set(StringUtils.camelToUnderline(field.getName()), value);
            //强制删除状态为未删除
            updateWrapper.set(StringUtils.camelToUnderline("deleted"), 0);
        }

        // 调用 updateFill 方法，确保自动填充字段被正确处理
        MetaObjectHandler metaObjectHandler = applicationContext.getBean(FieldMetaObjectHandler.class);
        if (metaObjectHandler != null) {
            metaObjectHandler.updateFill(metaObject);
        }

        // 返回 LambdaUpdateWrapper
        return updateWrapper.lambda();
    }
}
