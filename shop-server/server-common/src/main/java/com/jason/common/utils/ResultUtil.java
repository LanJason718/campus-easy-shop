package com.jason.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil<T> {

    private Integer code;
    private String msg;
    private T data;

    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;
    private static final int UNAUTHORIZED_CODE = 401;

    /**
     * 成功的返回结果
     *
     * @param msg  成功信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> success(String msg, T data) {
        return new ResultUtil<>(SUCCESS_CODE, msg, data);
    }

    /**
     * 成功的返回结果
     *
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> success(T data) {
        return success("操作成功", data);
    }

    /**
     * 失败的返回结果
     *
     * @param msg  错误信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> error(String msg, T data) {
        return new ResultUtil<>(ERROR_CODE, msg, data);
    }

    /**
     * 失败的返回结果
     *
     * @param msg 错误信息
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> error(String msg) {
        return error(msg, null);
    }

    /**
     * 认证失败的返回结果
     *
     * @param msg  失败信息
     * @param data 返回的值
     * @param <T>  返回值的泛型
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> unAuth(String msg, T data) {
        return new ResultUtil<>(UNAUTHORIZED_CODE, msg, data);
    }

    /**
     * 认证失败的返回结果
     *
     * @param msg 失败信息
     * @return 返回Result对象
     */
    public static <T> ResultUtil<T> unAuth(String msg) {
        return unAuth(msg, null);
    }
}
