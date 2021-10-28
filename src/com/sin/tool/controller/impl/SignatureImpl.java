package com.sin.tool.controller.impl;

import com.sin.tool.controller.ISignature;
import com.sin.tool.controller.config.controllerLogs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * @Date 2021/9/6   9:57
 */
public class SignatureImpl implements ISignature {
    Scanner scanner = new Scanner(System.in);

    /**
     * 进行签名验证
     *
     * @return
     */
    @Override
    public boolean digitalKeyp() {
        try {
            System.out.print("Toolbox$>>>#公钥文件绝对路径@");
            String nextKeyp = scanner.next();
            new controllerLogs(nextKeyp).start();
            System.out.print("Toolbox$>>>#进行签名验证的文件绝对路径@");
            String nextKeypMessage = scanner.next();
            new controllerLogs(nextKeypMessage).start();
            System.out.print("Toolbox$>>>#签名文件绝对路径@");
            String messageKeyp = scanner.next();
            new controllerLogs(messageKeyp).start();
            long startTime = System.currentTimeMillis(); //获取开始时间
            File keypFile= new File(messageKeyp);
            BufferedReader inKeyp = new BufferedReader(new FileReader(keypFile));
            String textKeyp;
            StringBuilder builderKeyp = new StringBuilder();
            while ((textKeyp=inKeyp.readLine())!=null){
                builderKeyp.append(textKeyp);
            }
            if (builderKeyp!=null){
                inKeyp.close();
            } else {
                inKeyp.close();
                System.out.println("文件没有内容");
                return false;
            }
            String[] split = String.valueOf(builderKeyp).split("~");
            byte[] bytes = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bytes[i]= Byte.parseByte(split[i]);
            }
            File fileM = new File(nextKeypMessage);
            BufferedReader inM = new BufferedReader(new FileReader(fileM));
            String textM;
            StringBuilder builderM = new StringBuilder();
            while ((textM=inM.readLine())!=null){
                builderM.append(textM);
            }
            if (builderM!=null){
                inM.close();
            } else {
                inM.close();
                System.out.println("文件没有内容");
                return false;
            }
            byte[] message = String.valueOf(builderM).getBytes(StandardCharsets.UTF_8);
            File file = new File(nextKeyp);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String text;
            StringBuilder builder = new StringBuilder();
            if ((text=in.readLine()) != null) {
                builder.append(text);
                in.close();
            } else {
                in.close();
                System.out.println("文件没有内容");
                return false;
            }
            byte[] keyBytesp=Base64.getDecoder().decode(String.valueOf(builder));
            X509EncodedKeySpec keySpecp = new X509EncodedKeySpec(keyBytesp);
            KeyFactory keyFactoryp = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactoryp.generatePublic(keySpecp);
            // 用公钥验证:
            Signature v = Signature.getInstance("SHA512withRSA");
            v.initVerify(publicKey);
            v.update(message);
            boolean valid = v.verify(bytes);
            if (valid){
                System.out.println("本人签名文件-通过");
            }else {
                System.out.println("非本人签名文件-未通过");
            }
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("签名判断所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 进行私钥加密
     *
     * @return
     */
    @Override
    public boolean digitalKeys() {
        try{
            System.out.print("Toolbox$>>>#私钥文件绝对路径@");
            String nextKeys = scanner.next();
            new controllerLogs(nextKeys).start();
            System.out.print("Toolbox$>>>#进行签名的文件绝对路径@");
            String nextKeysMessage = scanner.next();
            new controllerLogs(nextKeysMessage).start();
            System.out.print("Toolbox$>>>#私钥签名后文件绝对路径@");
            String messageKeys = scanner.next();
            new controllerLogs(messageKeys).start();
            long startTime = System.currentTimeMillis(); //获取开始时间
            File fileM = new File(nextKeysMessage);
            BufferedReader inM = new BufferedReader(new FileReader(fileM));
            String textM;
            StringBuilder builderM = new StringBuilder();
            while ((textM=inM.readLine())!=null){
                    builderM.append(textM);
            }
            if (builderM!=null){
                inM.close();
            } else {
                inM.close();
                System.out.println("文件没有内容");
                return false;
            }

            // 待签名的消息:
            byte[] message = String.valueOf(builderM).getBytes(StandardCharsets.UTF_8);

            File file = new File(nextKeys);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String text;
            StringBuilder builder = new StringBuilder();
            if ((text=in.readLine()) != null) {
                builder.append(text);
                in.close();
            } else {
                in.close();
                System.out.println("文件没有内容");
                return false;
            }
            byte[] decodeKeys = Base64.getDecoder().decode(String.valueOf(builder));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKeys);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            // 用私钥签名:
            Signature s = Signature.getInstance("SHA512withRSA");
            s.initSign(privateKey);
            s.update(message);
            byte[] signed = s.sign();
            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 0; i < signed.length; i++) {
                messageBuilder.append(signed[i]).append("~");
            }
            File messageFileKeys = new File(messageKeys);
            boolean newFiles = messageFileKeys.createNewFile();
            if (newFiles) {
                BufferedWriter out = new BufferedWriter(new FileWriter(messageFileKeys));
                out.write(String.valueOf(messageBuilder));
                out.close();
                System.out.println("生成私钥签名后文件绝对路径:"+messageFileKeys.getAbsolutePath());
            }else {
                System.out.println("私钥签名后文件生成失败");
                return false;
            }
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("生成私钥签名后文件所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
        }catch (Exception e){
            System.out.println("生成签名文件出错");
        }
        return false;
    }

    /**
     * 进行私钥与公钥文件的生成
     *
     * @return
     */
    @Override
    public boolean digital() {
        try{
            System.out.print("Toolbox$>>>#私钥的文件名及路径@");
            String nextKeys = scanner.next();
            new controllerLogs(nextKeys).start();
            System.out.print("Toolbox$>>>#公钥的文件名及路径@");
            String nextKeyp = scanner.next();
            new controllerLogs(nextKeyp).start();
            long startTime = System.currentTimeMillis(); //获取开始时间
            // 生成RSA公钥/私钥:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024, new SecureRandom());
            KeyPair kp = kpGen.generateKeyPair();
            PrivateKey sk = kp.getPrivate();
//            System.out.println("私"+sk);
            File fileKeys = new File(nextKeys);
            boolean newFiles = fileKeys.createNewFile();
            if (newFiles) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileKeys));
                String encodeKeys = Base64.getEncoder().encodeToString(sk.getEncoded());
                out.write(encodeKeys);
                out.close();
                System.out.println("私钥文件绝对路径:"+fileKeys.getAbsolutePath());
            }else {
                System.out.println("私钥文件生成失败");
                return false;
            }
            PublicKey pk = kp.getPublic();
//            System.out.println("公"+pk);
            File fileKeyp = new File(nextKeyp);
            boolean newFilep = fileKeyp.createNewFile();
            if (newFilep) {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileKeyp));
                String encodeKeyp = Base64.getEncoder().encodeToString(pk.getEncoded());
                out.write(encodeKeyp);
                out.close();
                System.out.println("公钥文件绝对路径:"+fileKeyp.getAbsolutePath());
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("私公密钥生成所用时间:" + (endTime - startTime) + "ms"); //输出程序运行时间
            }else {
                System.out.println("公钥文件生成失败");
                return false;
            }
        }catch (Exception e){
            System.out.println("生成密钥出错");
        }
        return false;
    }
}
