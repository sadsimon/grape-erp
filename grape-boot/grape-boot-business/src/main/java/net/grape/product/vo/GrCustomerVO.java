package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "客户")
public class GrCustomerVO {

    @Schema(description = "主键id")
    private Long id;

    /**
     * 客户名称
     */
    @Schema(description = "客户名称")
    private String customerName;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    private String contactName;

    /**
     * 联系人手机号
     */
    @Schema(description = "联系人手机号")
    private String contactMobile;

    /**
     * 联系人QQ/微信/e-mail
     */
    @Schema(description = "联系人QQ/微信/e-mail")
    private String netContact;
    /**
     * 开户银行
     */
    @Schema(description = "开户银行")
    private String depositBank;

    /**
     * 银行账户
     */
    @Schema(description = "银行账户")
    private String bankNumber;

    /**
     * 纳税人识别号
     */
    @Schema(description = "纳税人识别号")
    private String taxpayerIdNum;

    /**
     * 期初预收款
     */
    @Schema(description = "期初预收款")
    private BigDecimal initialAdvancePayment;

    /**
     * 增值税率%
     */
    @Schema(description = "增值税率%")
    private Double rate;

    /**
     * 期初应收
     */
    @Schema(description = "期初应收")
    private BigDecimal initialPayable;

    /**
     * 省
     */
    @Schema(description = "省")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    private String city;

    /**
     * 区
     */
    @Schema(description = "区")
    private String district;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String address;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 状态 0：失效  1：生效
     */
    @Schema(description = "状态 0：失效  1：生效")
    private Integer state;
}
