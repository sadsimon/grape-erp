package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "往来单位列表")
public class ContactunitsListVO implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 编号
     */
    @Schema(description = "编号")
    private String contactunitsCode;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String contactunitsName;

    /**
     * 供应商类别id
     */
    @Schema(description = "供应商类别id")
    private Long categoryId;

    /**
     * 供应商类别名称
     */
    @Schema(description = "供应商类别名称")
    private String categoryName;

    /**
     * 业务员id
     */
    /*@Schema(description = "业务员id")
    private Long salesmanId;*/

    /**
     * 首要联系人
     */
    @Schema(description = "首要联系人")
    private Long contactId;

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
     * 期初预付款
     */
    @Schema(description = "期初预付款")
    private BigDecimal initialAdvancePayment;

    /**
     * 增值税率%
     */
    @Schema(description = "增值税率%")
    private Double rate;

    /**
     * 期初应付
     */
    private BigDecimal initialPayable;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    private String contactName;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * 座机
     */
    @Schema(description = "座机")
    private String landline;

    /**
     * QQ/微信/e-mail
     */
    @Schema(description = "QQ/微信/e-mail")
    private String netContact;

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
     * 首要联系人 0：是  1：否
     */
    @Schema(description = "首要联系人 0：是  1：否")
    private Integer primaryPerson;

    /**
     * 类型：0：供应商；1：客户
     */
    @Schema(description = "类型：0：供应商；1：客户")
    private String type;
}
