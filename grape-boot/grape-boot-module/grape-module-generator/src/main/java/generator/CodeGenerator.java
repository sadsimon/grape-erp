package generator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;
import java.util.Scanner;

public class CodeGenerator {
    public static void main(String[] args) {

        //已生成过的，重新执行只覆盖entity实体类;手动删除可再次生成
        String table = "gr_product";

        //项目地址
        String dir = "E:/workspace/grape-erp/";

        //模块名称
        //String module = "order";
        //String module = "stock";
        String module = "product";
        //String module = "system";

        //输出地址
        String dir1 = "/grape-boot/grape-boot-business/src/main/";

        //String dir1 = "/grape-boot/grape-boot-system/src/main/";

        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象用于读取控制台输入

        System.out.println("请输入 Y 继续执行程序，输入其他任意字符退出：");

        // 读取用户输入的字符
        String input = scanner.nextLine().trim(); // 使用 nextLine() 读取整行输入，并去除首尾空格

        // 判断输入是否为 Y
        if (!"Y".equalsIgnoreCase(input)) { // 使用 equalsIgnoreCase() 方法忽略大小写
            return;
        }
        System.out.println("正在生成代码......");
       //FastAutoGenerator.create("jdbc:mysql://192.168.2.37:3306/grape?useSSL=false&serverTimezone=UTC", "grape", "WpbyCw2swEm2bYYD")
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/grape?useSSL=false&serverTimezone=UTC", "root", "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("疯狂的老码") // 设置作者
                            /*.outputDir(System.getProperty("user.dir") + "/src/main/java")*/ // 设置输出目录
                            .outputDir(dir+dir1+"java") // 设置输出目录
                            .disableOpenDir(); // 禁止打开输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("net.grape") // 设置父包名
                            .moduleName(module) // 设置模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, dir+dir1+"resources/mapper")); // 设置 Mapper XML 输出路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(table) // 设置需要生成的表名
                            // 配置实体类
                            .entityBuilder()
                            .enableLombok()
                            .enableFileOverride() // 覆盖实体类文件
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)//驼峰
                            .superClass("net.grape.framework.mybatis.entity.BaseEntity") // 设置父类
                            .addSuperEntityColumns("id","tenant_id","creator","updater","create_time", "update_time", "version", "deleted") // 设置父类字段
                            .formatFileName("%sEntity")
                            // 配置 Mapper
                            .mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .superClass("net.grape.framework.mybatis.dao.BaseDao")
                            //.enableFileOverride() // 覆盖 Mapper 文件
                            // 配置 Service
                            .serviceBuilder()
                            .superServiceClass("net.grape.framework.mybatis.service.BaseService")
                            .superServiceImplClass("net.grape.framework.mybatis.service.impl.BaseServiceImpl")
                            //.enableFileOverride() // 覆盖 Service 文件
                            // 配置 Controller
                            .controllerBuilder()
                            .enableRestStyle();
                            //.enableFileOverride(); // 覆盖 Controller 文件
                })
                // 使用 Velocity 模板引擎
                .templateEngine(new VelocityTemplateEngine())
                // 执行生成
                .execute();
        System.out.println("成功......");
    }

}
