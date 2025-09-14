package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] arges) throws IOException, TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        //整个项目的根路径
        File file = new File(projectPath).getParentFile();
        //输出路径
        String inputPath = new File(file, "yuzi-generator.bat-demo-projects/acm-template").getAbsolutePath();
        String outputPath = file.getAbsolutePath()+ File.separator + "yuzi-generator.bat-basic";
        //生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);
        //生成动态文件
        String inputDynamicPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicPath,outputDynamicPath,model);
    }
}
