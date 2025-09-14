package com.yupi.cli.command;
import cn.hutool.core.util.ReflectUtil;
import picocli.CommandLine.Command;
import com.yupi.model.MainTemplateConfig;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import cn.hutool.core.io.FileUtil;

@Command(name ="config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    public void run() {
        //实现Config命令的逻辑
        System.out.println("查看参数信息");
        //获取MainTemplateConfig类的所有字段 ,反射工具类
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        //遍历打印每个字段的信息
        for(Field field : fields){
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("---");
        }
    }
}
