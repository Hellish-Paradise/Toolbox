package com.sin.tool.controller;

/**
 * @Date 2021/9/6   9:56
 */
public interface ISignature {
    /**
     * 进行私钥与公钥文件的生成
     * @return
     */
    default boolean digital(){
        return false;
    }

    /**
     * 进行私钥加密
     * @return
     */
    default boolean digitalKeys(){
        return false;
    }

    /**
     * 进行签名验证
     * @return
     */
    default boolean digitalKeyp(){
        return false;
    }
}
