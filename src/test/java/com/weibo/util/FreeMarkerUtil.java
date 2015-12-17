package com.weibo.util;


import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.io.Writer;  
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
public class FreeMarkerUtil {  
    /** 
     * 获取指定目录下的模板文件 
     * @param name       模板文件的名称 
     * @param pathPrefix 模板文件的目录 
     */  
    public Template getTemplate(String name) throws IOException{  
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23); //通过FreeMarker的Configuration对象可以读取ftl文件  
        cfg.setDirectoryForTemplateLoading(new File("D:\\javaworkspace\\SimpleWeibo\\WebRoot")); //设置模板文件的目录  
        cfg.setDefaultEncoding("UTF-8");       //Set the default charset of the template files  
        Template temp = cfg.getTemplate(name); //在模板文件目录中寻找名为"name"的模板文件  
        return temp; //此时FreeMarker就会到类路径下的"pathPrefix"文件夹中寻找名为"name"的模板文件  
    }  
      
    /** 
     * 根据模板文件输出内容到控制台 
     * @param name       模板文件的名称 
     * @param pathPrefix 模板文件的目录 
     * @param rootMap    模板的数据模型 
     */  
    public void print(String name, Map<String,Object> rootMap) throws TemplateException, IOException{  
        this.getTemplate(name).process(rootMap, new PrintWriter(System.out));  
    }  
      
    /** 
     * 根据模板文件输出内容到指定的文件中 
     * @param name       模板文件的名称 
     * @param pathPrefix 模板文件的目录 
     * @param rootMap    模板的数据模型 
     * @param file       内容的输出文件 
     */  
    public void printFile(String name, Map<String,Object> rootMap, File file) throws TemplateException, IOException{  
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));  
        this.getTemplate(name).process(rootMap, out); //将模板文件内容以UTF-8编码输出到相应的流中  
        if(null != out){  
            out.close();  
        }  
    }  
}  