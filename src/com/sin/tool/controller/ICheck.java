package com.sin.tool.controller;

/**
 * @Date 2021/9/5   21:09
 */
public interface ICheck {
    // 查看日志
    default boolean see() {
        return false;
    }
    // 查看指定日期日志
    default boolean seeAssign() {
        return false;
    }

    /**
     * 删除日志
     * @return
     */
    default boolean delete(){
        return false;
    }
}
