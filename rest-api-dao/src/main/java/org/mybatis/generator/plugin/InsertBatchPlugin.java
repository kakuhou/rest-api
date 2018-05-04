package org.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
/**
 * @author guopeng
 * 2016年6月4日
 * */
public class InsertBatchPlugin extends PluginAdapter{

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
    	interfaze.addMethod(generateInsertBatch(method,
                introspectedTable));
        return true;
    }
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable){
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名  
		XmlElement parentElement = document.getRootElement();
		XmlElement insertBatchElement = new XmlElement("insert");
		insertBatchElement.addAttribute(new Attribute("id", "insertBatch"));
		insertBatchElement.addAttribute(new Attribute("parameterType", "java.util.List"));
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		StringBuffer tableColumn = new StringBuffer();
		StringBuffer javaType = new StringBuffer();
		for (int i = 0; i < columns.size(); i++) {
			IntrospectedColumn column = columns.get(i);
			if (i != 0) {
				tableColumn.append(",");
				javaType.append(",");
			}
			tableColumn.append(column.getActualColumnName());
			javaType.append("#{item."+ column.getJavaProperty() +",jdbcType="+ column.getJdbcTypeName() +"}");
		}
		insertBatchElement.addElement(
	                new TextElement(
	                "insert into " + tableName + " ("+ tableColumn.toString() +") values "
	                + " <foreach collection=\"records\" item=\"item\" index=\"index\" separator=\",\"> " + 
	                "( "+ javaType.toString() +" )  </foreach>"
	                ));
		parentElement.addElement(insertBatchElement);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
	
	private Method generateInsertBatch(Method method, IntrospectedTable introspectedTable) {
        
        Method m = new Method("insertBatch");
        
        m.setVisibility(method.getVisibility());
        
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());

        m.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.List<"+introspectedTable.getBaseRecordType() + ">"), "records", "@Param(\"records\")"));

        context.getCommentGenerator().addGeneralMethodComment(m,
                introspectedTable);
        return m;
    }
}
