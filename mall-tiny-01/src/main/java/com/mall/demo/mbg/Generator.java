package com.mall.demo.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于生产MBG的代码
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        //MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        //读取MBG配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration configuration = cp.parseConfiguration(is);
        is.close();
        //创建 MBG
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //执行生成代码
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);
        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
