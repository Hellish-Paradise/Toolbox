package com.sin.tool.controller.config;

import com.sin.tool.model.modelLogs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2021/9/2   17:37
 */
public class controllerLogs extends Thread{
    private String s;
    public controllerLogs(String s) {
        this.s=s;
    }
    @Override
    public void run() {
        synchronized ("lock"){
            // 获取当前时间:
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd~E~HH:mm:ss:ms");
            modelLogs logs = new modelLogs(sdf.format(date),s);
            final String path= "logs/data/journal.log";
            File file = new File(path);
            if (file.exists()){
                try {
                    FileOutputStream fos=new FileOutputStream(file,true);
                    ObjectOutputStream oos=new ObjectOutputStream(fos);
                    long pos;
                    pos=fos.getChannel().position()-4;//追加的时候去掉头部aced 0005
                    fos.getChannel().truncate(pos);
                    oos.writeObject(logs);
                    oos.close();
//                System.out.println("追加成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    boolean newFile = file.createNewFile();
                    if (newFile){
                        FileOutputStream fos=new FileOutputStream(file);
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(logs);
                        oos.close();
//                    System.out.println("首次对象序列化成功");
                    }else {
                        System.out.println("首次对象序列化失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
