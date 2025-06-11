package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "仓库")
public class GrStoreVO implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "仓库主键")
    private Long id;

    /**
     * 仓库编号
     */
    @Schema(description = "仓库编号")
    private String storeNumber;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String storeName;

    /**
     * 仓管人员id
     */
    @Schema(description = "仓管人员id")
    private Long userId;

    /**
     * 仓管人姓名
     */
    @Schema(description = "仓管人姓名")
    private String username;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;
}
