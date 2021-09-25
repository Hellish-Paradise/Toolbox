package com.sin.tool.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @Date 2021/9/5   16:36
 */
public interface ISafe {
    // 进行文件内容哈希加密
    default boolean safeHash() throws NoSuchAlgorithmException, IOException {
        return false;
    }

    /**
     * 随机密码生成
     * @return
     */
    default boolean safePassword(){
        return false;
    }

    /**
     * 对数据进行CBC模式的AES加密
     * @return
     */
    default boolean safeEncryption(){
        return false;
    }

    /**
     * 对数据进行CBC模式的AES解密
     * @return
     */
    default boolean safeDecryption(){
        return false;
    }
}
