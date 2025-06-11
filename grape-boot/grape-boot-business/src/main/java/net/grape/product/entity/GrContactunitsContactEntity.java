package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 往来单位联系人
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Getter
@Setter
@TableName("gr_contactunits_contact")
public class GrContactunitsContactEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    private Long contactunitsId;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 座机
     */
    private String landline;

    /**
     * QQ/微信/e-mail
     */
    private String netContact;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 首要联系人 0：是  1：否
     */
    private Integer primaryPerson;

    /**
     * 状态 0：失效  1：生效
     */
    private Integer state;
}
