package net.grape.framework.common.constant;

/**
 * 常量
 *
 * @author 1161095164@qq.com
 *  
 */
public interface Constant {
    /**
     * 根节点标识
     */
    Long ROOT = 0L;
    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";
    /**
     * 超级管理员
     */
    Integer SUPER_ADMIN = 1;
    /**
     * 禁用
     */
    Integer DISABLE = 0;
    /**
     * 启用
     */
    Integer ENABLE = 1;
    /**
     * 失败
     */
    Integer FAIL = 0;
    /**
     * 成功
     */
    Integer SUCCESS = 1;
    /**
     * OK
     */
    String OK = "OK";

    /**
     * pgsql的driver
     */
    String PGSQL_DRIVER = "org.postgresql.Driver";

    /**
     * 未删除
     */
    Integer NOT_DELETE = 0;

    /**
     * 删除
     */
    Integer DELETE = 1;



}