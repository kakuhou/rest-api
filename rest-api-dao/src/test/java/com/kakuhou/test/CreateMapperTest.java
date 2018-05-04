package com.kakuhou.test;

import org.mybatis.generator.util.MybatisGeneratorUtil;

public class CreateMapperTest {
	
	public static void main(String[] args) throws Throwable {
        MybatisGeneratorUtil.generate("rest.xml");
    }
}
