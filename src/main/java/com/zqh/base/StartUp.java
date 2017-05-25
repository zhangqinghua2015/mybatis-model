package com.zqh.base;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class StartUp {
    public static void main(String[] args) throws URISyntaxException {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            //直接获取generatorConfig.xml的文件路径 根据具体情况查看
            File configFile = new File(ClassLoader.getSystemResource("generatorConfig.xml").toURI());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            if (warnings.isEmpty()) {
                System.out.println("\nGENERATE SUCCESS!PLEASE REFRESH!");
            } else {
                System.out.println("\nGENERATE FAIL: ");
                for(String err : warnings) {
                    System.out.println(err);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
}
