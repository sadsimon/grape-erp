package net.grape;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author 阿沐  babamu@126.com
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi systemApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape.system"};
        return GroupedOpenApi.builder()
                .group("1")
                .displayName("System API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi quartzApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape.quartz"};
        return GroupedOpenApi.builder()
                .group("2")
                .displayName("Quartz API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi monitorApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape.monitor"};
        return GroupedOpenApi.builder()
                .group("3")
                .displayName("Monitor API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape.member"};
        return GroupedOpenApi.builder()
                .group("4")
                .displayName("Member API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi iotApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape.iot"};
        return GroupedOpenApi.builder()
                .group("5")
                .displayName("Iot API")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"net.grape"};
        return GroupedOpenApi.builder()
                .group("99")
                .displayName("Other API")
                .pathsToMatch(paths)
                .packagesToExclude(
                        "net.grape.system", "net.grape.quartz", "net.grape.monitor", "net.grape.member", "net.grape.iot",
                        "net.grape.generator"
                )
                .packagesToScan(packagedToMatch)
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();

        return new OpenAPI().info(new Info()
                .title("Grape API")
                .description("Grape API")
                .contact(contact)
                .version("3.x")
                .termsOfService("https://Grape.net")
        );
    }

}