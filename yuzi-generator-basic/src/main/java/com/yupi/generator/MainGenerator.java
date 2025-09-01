package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] arges) throws IOException, TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerator(mainTemplateConfig);
    }
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");

        String inputPath = new File(projectPath, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath + File.separator + "yuzi-generator-basic";
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);
        String inputDynamicPath = projectPath + File.separator + "yuzi-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = projectPath + File.separator + "yuzi-generator-basic/acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicPath,outputDynamicPath,model);
    }
}
