package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "供应商详情")
public class ContactunitsVO implements Serializable {

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
     * 类别
     */
    @Schema(description = "类别")
    private Long categoryId;

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
     * 期初预收款
     */
    @Schema(description = "期初预收款")
    private BigDecimal initAdvanceIn;

    /**
     * 期初预付款
     */
    @Schema(description = "期初预付款")
    private BigDecimal initAdvanceOut;

    /**
     * 累计预收款
     */
    @Schema(description = "累计预收款")
    private BigDecimal advanceIn;

    /**
     * 累计预付款
     */
    @Schema(description = "累计预付款")
    private BigDecimal advanceOut;

    /**
     * 增值税率%
     */
    @Schema(description = "增值税率%")
    private Double rate;

    /**
     * 期初应付
     */
    @Schema(description = "期初应付")
    private BigDecimal initNeedPay;

    /**
     * 累计应付
     */
    @Schema(description = "累计应付")
    private BigDecimal needPay;

    /**
     * 期初应收
     */
    @Schema(description = "期初应收")
    private BigDecimal initNeedGet;

    /**
     * 累计应收
     */
    @Schema(description = "累计应收")
    private BigDecimal needGet;

    /**
     * 累计预付款
     */
    @Schema(description = "累计预付款")
    private BigDecimal payable;

    /**
     * 类型：0：供应商；1：客户
     */
    @Schema(description = "类型：0：供应商；1：客户")
    private String type;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;

    @Schema(description = "往来单位联系人列表")
    private List<GrContactunitsContact> contactList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "联系人")
    public static class GrContactunitsContact {
        /**
         * 主键
         */
        @Schema(description = "主键")
        private Long id;

        /**
         * 往来单位id
         */
        @Schema(description = "往来单位id")
        private Long contactunitsId;

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
         * 状态 0：生效  1：失效
         */
        @Schema(description = "状态 0：生效  1：失效")
        private Integer state;
    }
}
