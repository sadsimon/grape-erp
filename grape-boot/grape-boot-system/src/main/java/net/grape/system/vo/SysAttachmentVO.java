package net.grape.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.grape.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 附件管理
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@Schema(description = "附件管理")
public class SysAttachmentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "附件名称")
    private String name;

    @Schema(description = "附件地址")
    private String url;

    @Schema(description = "附件大小")
    private Long size;

    @Schema(description = "存储平台")
    private String platform;

    @Schema(description = "关联业务code")
    private String businessCode;

    @Schema(description = "创建人姓名")
    private String creatorName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

}