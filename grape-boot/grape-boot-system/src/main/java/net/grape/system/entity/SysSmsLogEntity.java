package net.grape.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信日志
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
@TableName("sys_sms_log")
public class SysSmsLogEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 平台ID
     */
    private Long platformId;

    /**
     * 平台类型
     */
    private Integer platform;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：失败   1：成功
     */
    private Integer status;

    /**
     * 参数
     */
    private String params;

    /**
     * 异常信息
     */
    private String error;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}