package top.ddgotxdy.dal.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


/**
 * @author: ddgo
 * @description: 逆向工程文件
 */
public class MyBatisPlusGenerator {

    private static final String URL = "jdbc:mysql://182.92.216.59:3306/myblog?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1314520ASD@";
    public static final String OUTPUT_DIR = "G:/My_code_project_tmp/blog-springboot/src/main/java";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("ddgo") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ddgotxdy"); // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
//                                    "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    builder
                            .addTablePrefix("tb_")
                            .entityBuilder()
                            .enableLombok();
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
