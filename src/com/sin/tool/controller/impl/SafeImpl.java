package com.sin.tool.controller.impl;

import com.sin.tool.controller.ISafe;
import com.sin.tool.controller.config.controllerLogs;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

/**
 * @Date 2021/9/5   16:39
 */
public class SafeImpl implements ISafe {
    Scanner scanner = new Scanner(System.in);
    // 进行文件内容哈希加密
    @Override
    public boolean safeHash() throws NoSuchAlgorithmException, IOException {
        System.out.print("Toolbox$>>>#文件绝对路径@");
        String next = scanner.next();
        new controllerLogs(next).start();
        File file = new File(next);
        System.out.print("Toolbox$>>>#哈希加密算法(SHA-1|SHA-224|SHA-256|SHA-384|SHA-512|MD5)@");
        String nextH = scanner.next();
        new controllerLogs(nextH).start();
        System.out.print("Toolbox$>>>#指定加密后文件内容位数@");
        int nextB = scanner.nextInt();
        new controllerLogs(String.valueOf(nextB)).start();
        System.out.print("Toolbox$>>>#输出文件的路径及名字@");
        String nextT = scanner.next();
        new controllerLogs(nextT).start();
        File fileT = new File(nextT);
        if (file.exists()) {
            long startTime = System.currentTimeMillis(); //获取开始时间
            BufferedReader in = new BufferedReader(new FileReader(file));
            String text;
            String toText;
            // 创建一个MessageDigest实例:
                MessageDigest md = MessageDigest.getInstance(nextH);
                while ((text = in.readLine()) != null){
                    // 反复调用update输入数据:
                    md.update(text.getBytes(StandardCharsets.UTF_8));
                }
            byte[] result = md.digest(); // 加密后内容
            if (result!=null){
                in.close();
            } else {
                in.close();
                System.out.println("文件没有内容");
                return false;
            }
                toText = new BigInteger(1,result).toString(nextB);
            boolean newFile = fileT.createNewFile();
            if (newFile) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileT));
                out.write(toText);
                out.close();
            }else {
                System.out.println("加密失败");
                return false;
            }
            System.out.println("加密文件输出路径:"+fileT.getAbsolutePath());
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("加密所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
            return true;
        }else {
            System.out.println("文件不存在");
        }
        return false;
    }

    /**
     * 随机密码生成
     *
     * @return
     */
    @Override
    public boolean safePassword() {
        System.out.print("Toolbox$>>>#密码长度(最小为4)@");
        int rdI = scanner.nextInt();
        new controllerLogs(String.valueOf(rdI)).start();
        long startTime = System.currentTimeMillis(); //获取开始时间
        final int r = 4;
        int rd;
        if (rdI<=4){
            rd=r;
        }else {
            rd=rdI;
        }
        String Srt="qwertyuioplkjhgfdsazxcvbnm";
        String SRt=Srt.toUpperCase();
        String SrT="~!@#$%^&*()-_=/*-+|\\`·:;'<>,.";
        SecureRandom sr = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < r; i++) {
            int anI = sr.nextInt(4);
            switch (anI){
                case 0:
                    for (int a = 0; a < rd/r; a++) {
                        int anInt = sr.nextInt(10);
                        builder.append(anInt);
                    }
                    break;
                case 1:
                    for (int b = 0; b < rd/r; b++) {
                        int nextInt = sr.nextInt(Srt.length());
                        builder.append(Srt.charAt(nextInt));
                    }
                    break;
                case 2:
                    for (int c = 0; c < rd/r; c++) {
                        int nextInt = sr.nextInt(SRt.length());
                        builder.append(SRt.charAt(nextInt));
                    }
                    break;
                case 3:
                    for (int d = 0; d < rd/r; d++) {
                        int nextInt = sr.nextInt(SrT.length());
                        builder.append(SrT.charAt(nextInt));
                    }
                    break;
            }
        }
        if ((rd%r)!=0) {
            int anI = sr.nextInt(4);
            switch (anI) {
                case 0:
                    for (int a = 0; a < rd % r; a++) {
                        int anInt = sr.nextInt(10);
                        builder.append(anInt);
                    }
                    break;
                case 1:
                    for (int b = 0; b < rd % r; b++) {
                        int nextInt = sr.nextInt(Srt.length());
                        builder.append(Srt.charAt(nextInt));
                    }
                    break;
                case 2:
                    for (int c = 0; c < rd % r; c++) {
                        int nextInt = sr.nextInt(SRt.length());
                        builder.append(SRt.charAt(nextInt));
                    }
                    break;
                case 3:
                    for (int d = 0; d < rd % r; d++) {
                        int nextInt = sr.nextInt(SrT.length());
                        builder.append(SrT.charAt(nextInt));
                    }
                    break;
            }
        }
        if (builder!=null){
            System.out.println("生成的密码为:"+builder);
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("生成密码所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
            return true;
        }else {
            System.out.println("密码生成失败");
            return false;
        }
    }

    /**
     * 对数据进行CBC模式的AES加密
     *
     * @return
     */
    @Override
    public boolean safeEncryption() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        boolean cbc = Cbc.cbcE();
        if (cbc){
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("加密所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
        }else {
            System.out.println("加密失败");
            return false;
        }
        return false;
    }

    /**
     * 对数据进行CBC模式的AES解密
     *
     * @return
     */
    @Override
    public boolean safeDecryption() {
        long startTime = System.currentTimeMillis(); //获取开始时间
        boolean cbc = Cbc.cbcD();
        if (cbc){
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("加密所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
        }else {
            System.out.println("加密失败");
            return false;
        }
        return false;
    }
}
class Cbc{
    static Scanner scanner = new Scanner(System.in);
    public static boolean cbcE(){
        try {
            System.out.print("Toolbox$>>>#文件绝对路径@");
            String nextSrc = scanner.next();
            new controllerLogs(nextSrc).start();
            System.out.print("Toolbox$>>>#密码@");
            String nextPwd = scanner.next();
            new controllerLogs(nextPwd).start();
            System.out.print("Toolbox$>>>#输出文件的路径及名字@");
            String nextT = scanner.next();
            new controllerLogs(nextT).start();
            File srcFile= new File(nextSrc);
            BufferedReader inSrc = new BufferedReader(new FileReader(srcFile));
            String textSrc;
            StringBuilder builderSrc = new StringBuilder();
            while ((textSrc=inSrc.readLine())!=null){
                builderSrc.append(textSrc);
            }
            if (builderSrc!=null){
                inSrc.close();
            } else {
                inSrc.close();
                System.out.println("文件没有内容");
                return false;
            }
            // 加密:
            // 创建一个MessageDigest实例:
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 反复调用update输入数据:
            md.update(nextPwd.getBytes(StandardCharsets.UTF_8));
            byte[] key = md.digest();
//            byte[] key = s.getBytes(StandardCharsets.UTF_8);
            byte[] data = String.valueOf(builderSrc).getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = encrypt(key, data);
//            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
            File fileT = new File(nextT);
            boolean newFile = fileT.createNewFile();
            if (newFile) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileT));
                out.write(Base64.getEncoder().encodeToString(encrypted));
                out.close();
            }else {
                System.out.println("加密失败");
                return false;
            }
            System.out.println("加密文件输出路径:"+fileT.getAbsolutePath());
            return true;
        }catch (Exception e){
            System.out.println("加密错误");
        }
        return true;
    }
    public static boolean cbcD(){
        try {
            System.out.print("Toolbox$>>>#文件绝对路径@");
            String nextSrc = scanner.next();
            new controllerLogs(nextSrc).start();
            System.out.print("Toolbox$>>>#密码@");
            String nextPwd = scanner.next();
            new controllerLogs(nextPwd).start();
            System.out.print("Toolbox$>>>#输出文件的路径及名字@");
            String nextT = scanner.next();
            new controllerLogs(nextT).start();
            File srcFile= new File(nextSrc);
            BufferedReader inSrc = new BufferedReader(new FileReader(srcFile));
            String textSrc;
            StringBuilder builderSrc = new StringBuilder();
            while ((textSrc=inSrc.readLine())!=null){
                builderSrc.append(textSrc);
            }
            if (builderSrc!=null){
                inSrc.close();
            } else {
                inSrc.close();
                System.out.println("文件没有内容");
                return false;
            }
            // 解密:
            byte[] bytes = Base64.getDecoder().decode(String.valueOf(builderSrc));
            // 创建一个MessageDigest实例:
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 反复调用update输入数据:
            md.update(nextPwd.getBytes(StandardCharsets.UTF_8));
            byte[] key = md.digest();
            byte[] decrypted = decrypt(key,bytes);
            File fileT = new File(nextT);
            boolean newFile = fileT.createNewFile();
            if (newFile) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileT));
                out.write(new String(decrypted, StandardCharsets.UTF_8));
                out.close();
            }else {
                System.out.println("解密失败");
                return false;
            }
            System.out.println("解密文件输出路径:"+fileT.getAbsolutePath());
            return true;
//            System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
        }catch (Exception e){
            System.out.println("解密错误");
            e.printStackTrace();
        }
        return true;
    }
    // 加密:
    public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        // CBC模式需要生成一个16 bytes的initialization vector:
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        byte[] data = cipher.doFinal(input);
        // IV不需要保密，把IV和密文一起返回:
        return join(iv, data);
    }
    public static byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }
    // 解密:
    public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        // 把input分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, 16);
        System.arraycopy(input, 16, data, 0, data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(data);
    }
}