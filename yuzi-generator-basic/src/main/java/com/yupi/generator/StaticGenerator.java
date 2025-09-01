package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void main(String[] arges) {
        //获取这个项目的根目录
        String projectPath = System.getProperty("user.dir");
        File projectFile = new File(projectPath);
        //输入路径
        String inputPath = new File(projectFile,"yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        //输出路径
        String outputPath =projectPath;
        copyFilesByHutool(inputPath,outputPath);
    }

    /**
     * 拷贝文件（Hutool实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath,outputPath,true);
    }

    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File input = new File(inputPath);
        File output = new File(outputPath);
        try{
            copyFileByRecursive(input,output);
        }
        catch(Exception e){
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 文件A => 目录B ,则文件A放到目录B下面
     * 文件A => 文件B ,则文件A覆盖文件B
     * 目录A => 目录B ,则目录A放到目录B下面
     * @param inputFile
     * @param outputFile
     * @throws Exception
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        //区分是文件还是目录
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            String destOutputFilePath = destOutputFile.getAbsolutePath();
            System.out.println(destOutputFilePath);
            //如果是目录，则创建目录
            if(destOutputFile.exists())
            {
                destOutputFile.mkdirs();
            }
            //获取目录下面的所有文件和子目录
            File[] files = inputFile.listFiles();
            //无子文件，则直接结束
            if(ArrayUtil.isEmpty(files)){
                return ;
            }
            for(File file : files) {
                copyFileByRecursive(file, destOutputFile);

            }

        }
        else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
