package net.grape.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "结算账户")
public class GrSettlementAccountVO  implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 账户编号
     */
    private String accountCode;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 期初余额
     */
    private BigDecimal openingBalance;

    /**
     * 账户类型  0：现金  1：银行存款
     */
    private String accountType;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
