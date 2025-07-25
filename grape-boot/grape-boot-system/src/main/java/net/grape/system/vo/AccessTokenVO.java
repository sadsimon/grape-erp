package net.grape.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.grape.framework.common.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * AccessToken
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@Schema(description = "AccessToken")
public class AccessTokenVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "access_token")
    @JsonProperty(value = "access_token")
    private String accessToken;

    @Schema(description = "access_token 过期时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime accessTokenExpire;

}
