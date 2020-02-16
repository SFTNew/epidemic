package com.wataxi.epidemic.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;

/**
 * @author jlbs1
 */
public class StaticUtils {
    private static final String TEMPLATE_PREFIX = "static/tempFiles/";
    private static final String TEMPLATE_SUFFIX = ".html";

    /**
     * 生成静态HTML文件
     * @param name  生成文件名称
     * @param context 参数
     * @throws Exception
     */
    public static void execHTML(String name,Context context) throws Exception {
        String exePath = "D:/"+name+".html";
        try{
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix(TEMPLATE_PREFIX);
            resolver.setSuffix(TEMPLATE_SUFFIX);
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);

            FileWriter writer = new FileWriter(exePath);
            templateEngine.process("template",context,writer);

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
