package net.grape.order.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "单据查询")
public class DocumentQuery extends Query {

    private Long contactunitsId;

    private Long userId;

    private List<String> types;

    /**
     * 模块（sale、purchase、settlement、stock）
     */
    private String module;

    private List<String> documentTimes;

    private String documentCode;
}
