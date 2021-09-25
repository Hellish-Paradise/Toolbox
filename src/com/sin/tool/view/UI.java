package com.sin.tool.view;

import com.sin.tool.controller.config.controllerLogs;
import com.sin.tool.controller.impl.CheckImpl;
import com.sin.tool.controller.impl.SafeImpl;
import com.sin.tool.controller.impl.SignatureImpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @Date 2021/9/2   14:38
 */
public class UI {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println("---------欢迎使用Toolbox---------请输入help获取Toolbox使用帮助---------");
        Scanner scanner = new Scanner(System.in);
        String s="y";
        SafeImpl safe = new SafeImpl();
        CheckImpl check = new CheckImpl();
        SignatureImpl signature = new SignatureImpl();
        while ("y".equals(s)){
            System.out.print("Toolbox$>>>");
            String nextLine = scanner.nextLine();
            new controllerLogs(nextLine).start();
            if (nextLine!=null){
                switch (nextLine){
                    case "exit":
                        s="n";
                        break;
                    case "hashsafe":
                        safe.safeHash();
                        break;
                    case "logs -s":
                        check.see();
                        break;
                    case "logs -sa":
                        check.seeAssign();
                        break;
                    case "logs -d":
                        check.delete();
                        break;
                    case "key -c":
                        signature.digital();
                        break;
                    case "key -s":
                        signature.digitalKeys();
                        break;
                    case "key -p":
                        signature.digitalKeyp();
                        break;
                    case "pwd":
                        safe.safePassword();
                        break;
                    case "aessafe -e":
                        safe.safeEncryption();
                        break;
                    case "aessafe -d":
                        safe.safeDecryption();
                        break;
                    case "help":
                        System.out.format("%-30s","exit");
                        System.out.format("%s\n", "退出Toolbox");
                        System.out.format("%-30s","hashsafe");
                        System.out.format("%s\n","进行文件内容Hash加密");
                        System.out.format("%-30s","key -c");
                        System.out.format("%s\n","创建公钥与私钥");
                        System.out.format("%-30s","key -s");
                        System.out.format("%s\n","使用私钥对文件内容进行签名");
                        System.out.format("%-30s","key -p");
                        System.out.format("%s\n","使用公钥对文件内容进行签名验证");
                        System.out.format("%-30s","pwd");
                        System.out.format("%s\n","随机密码生成");
                        System.out.format("%-30s","aessafe -e");
                        System.out.format("%s\n","进行AES加密");
                        System.out.format("%-30s","aessafe -d");
                        System.out.format("%s\n","进行AES解密");
                        System.out.format("%-30s","logs -s");
                        System.out.format("%s\n","查看全部日志");
                        System.out.format("%-30s","logs -sa");
                        System.out.format("%s\n","查看指定日期日志");
                        System.out.format("%-30s","logs -d");
                        System.out.format("%s\n","删除日志");
                        break;
                    default:
                        System.out.println("请输入正确指令");
                        break;
                }
            }
        }
    }
}
