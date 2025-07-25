package net.grape.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@Schema(description = "文件上传")
public class SysFileUploadVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件地址")
    private String url;

    @Schema(description = "文件大小")
    private Long size;

    @Schema(description = "存储平台")
    private String platform;
}
