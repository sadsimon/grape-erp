package net.grape.framework.mybatis.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据范围
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@AllArgsConstructor
public class DataScope {
    private String sqlFilter;

}