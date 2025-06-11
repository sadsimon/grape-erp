package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 计量单位
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Data
@Schema(description = "计量单位")
public class GrUnitSelectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "计量单位主键")
    private Long id;

    /**
     * 基本单位名称
     */
    @Schema(description = "基本单位名称")
    private String name;

    /**
     * 单位类型 0：主单位  1：副单位
     */
    @Schema(description = "单位类型 0：基本单位  1：副单位")
    private Integer type;

    /**
     * 多单位名称
     */
    @Schema(description = "多单位名称")
    private String manyName;

    /**
     * 上级基本单位id
     */
    @Schema(description = "上级基本单位id")
    private Long pid;

    /**
     * 上级基本单位的容量
     */
    @Schema(description = "上级基本单位的容量")
    private Integer capacity;

    /**
     * 单单位：1；多单位：2
     */
    @Schema(description = "单单位：1；多单位：2")
    private Integer model;
    
}
