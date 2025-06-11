package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.utils.TreeNode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "往来单位分类")
public class ContactunitsCategoryVO extends TreeNode<ContactunitsCategoryVO> implements Serializable {

    @Schema(description = "类别名称")
    @NotBlank(message = "类别名称不能为空")
    private String categoryName;

    @Schema(description = "上级类别名称")
    private String parentName;
}
