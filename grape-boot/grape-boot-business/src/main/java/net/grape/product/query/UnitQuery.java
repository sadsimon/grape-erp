package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "计量单位")
public class UnitQuery {

    @Schema(description = "单单位：1；多单位：2")
    private int model;
}
