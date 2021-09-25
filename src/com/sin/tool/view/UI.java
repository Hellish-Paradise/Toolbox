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
                        break;
                    default:
                        System.out.println("请输入正确指令");
                        break;
                }
            }
        }
    }
}
