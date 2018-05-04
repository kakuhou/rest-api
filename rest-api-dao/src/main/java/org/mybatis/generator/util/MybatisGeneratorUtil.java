/**   
 * Copyright © 2018 猪八戒. All rights reserved.
 * 
 * @Title: MybatisGeneratorUtil.java 
 * @Prject: java-jinrong-p2p-dao
 * @Package: com.jinrong.p2p.util 
 * @author: guopeng   
 * @date: 2018年1月27日 下午4:14:02 
 * @version: V1.0   
 */
package org.mybatis.generator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * mybatis代码生成工具
 * @author: guopeng
 * @date: 2018年1月27日
 * @title  mybatis代码生成工具
 */
public class MybatisGeneratorUtil {

	public static void generate(String... xmllist) throws Throwable {
		if (null == xmllist) {
            return;
        }

        for (int i = 0; i < xmllist.length; i++) {
            List<String> warnings = new ArrayList<>();
            String path = new File("./src/main/resources/").getAbsolutePath() + File.separatorChar + xmllist[i];
            System.out.println(path);
            File configFile = new File(path);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        }

        System.out.println("生成结束");
	}
}