package net.grape.framework.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.grape.framework.mybatis.handler.FieldMetaObjectHandler;
import net.grape.framework.mybatis.interceptor.DataScopeInnerInterceptor;
import net.grape.framework.mybatis.interceptor.IgnoredTables;
import net.grape.framework.mybatis.interceptor.TenantContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 配置
 *
 * @author 1161095164@qq.com
 *
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 数据权限
        mybatisPlusInterceptor.addInnerInterceptor(new DataScopeInnerInterceptor());
        // 乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 防止全表更新与删除
        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //租户模式
        mybatisPlusInterceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                // 从ThreadLocal获取当前租户ID
                String tenantId = TenantContext.getTenantId();
                return new StringValue(tenantId);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id"; // 数据库中的租户字段名
            }

            @Override
            public boolean ignoreTable(String tableName) {
                // 可配置不需要租户隔离的表
                //System.out.println(tableName+"======"+IgnoredTables.isIgnored(tableName));
                return IgnoredTables.isIgnored(tableName);
                //return false;
            }
        }));

        // 分页插件（放在租户模式下面，不然分页的查询租户不生效）
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return mybatisPlusInterceptor;
    }

    @Bean
    public FieldMetaObjectHandler fieldMetaObjectHandler(){
        return new FieldMetaObjectHandler();
    }
}
