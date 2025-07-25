package net.grape.framework.common.xss;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.HttpContextUtils;
import org.springframework.util.PathMatcher;

import java.io.IOException;

/**
 * xss json过滤
 *
 * @author 1161095164@qq.com
 *
 */
@AllArgsConstructor
public class XssFilterJsonDeserializer extends JsonDeserializer<String> {
    private final XssProperties properties;
    private final PathMatcher pathMatcher;

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();
        if (StrUtil.isBlank(value)) {
            return value;
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request == null) {
            return value;
        }

        // 判断该URI是否放行
        boolean flag = properties.getExcludeUrls().stream().anyMatch(excludeUrl -> pathMatcher.match(excludeUrl, request.getServletPath()));
        if (flag) {
            return value;
        }

        return XssUtils.filter(value);
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }
}
