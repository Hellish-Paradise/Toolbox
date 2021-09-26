package com.sin.tool.controller.impl;

import com.sin.tool.controller.ICheck;
import com.sin.tool.controller.config.controllerLogs;
import com.sin.tool.model.modelLogs;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Date 2021/9/5   21:12
 */
public class CheckImpl implements ICheck {
    // 查看指定日期日志
    @Override
    public boolean seeAssign() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        final String path="logs/data/journal.log";
        File file = new File(path);
        HashMap<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Toolbox$>>>#日志的时间@");
        String time = scanner.next();
        new controllerLogs(time).start();
        try{
            if (file.exists()){
                FileInputStream fn = new FileInputStream(path);
                ObjectInputStream oin=new ObjectInputStream(fn);
                while (fn.available()>0){//代表文件中还有内容
                    modelLogs o = (modelLogs) oin.readObject();
                    map.put(o.getDate(), o.getInfo());
                }
                System.out.println("日期:"+time+"-"+"输入信息:"+map.get(time));
                oin.close();
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("获取指定日志所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
                return true;
            }else {
                System.out.println("文件不存在");
                return false;
            }
        }catch (Exception e){
            System.out.println("请重启程序删除日志");
        }
        return false;
    }

    // 查看日志
    @Override
    public boolean see(){
        long startTime = System.currentTimeMillis(); //获取开始时间
        final String path="logs/data/journal.log";
        File file = new File(path);
        try {
            if (file.exists()){
                FileInputStream fn = new FileInputStream(path);
                ObjectInputStream oin=new ObjectInputStream(fn);
                while (fn.available()>0){//代表文件中还有内容
                    modelLogs o = (modelLogs) oin.readObject();
                    System.out.println(o);
                }
                oin.close();
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("获取全部日志所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
                return true;
            }else {
                System.out.println("文件不存在");
                return false;
            }
        }catch (Exception e){
            System.out.println("请重启程序删除日志");
        }
        return false;
    }

    /**
     * 删除日志
     *
     * @return
     */
    @Override
    public boolean delete() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        final String path="logs/data/journal.log";
        File file = new File(path);
        if (file.exists()){
            boolean b = file.delete();
            if (b){
                System.out.println("删除日志成功");
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("删除日志所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
                return true;
            }else {
                System.out.println("删除日志失败");
                return false;
            }
        }else {
            System.out.println("删除日志不存在");
            return false;
        }
    }
}
