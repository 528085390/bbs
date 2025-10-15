package com.li.bbs.Pojo;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;


    /** 成功响应码 */
    public static final Integer SUCCESS = 200;
    /** 创建成功响应码 */
    public static final Integer CREATED = 201;
    /** 操作（更新，删除）成功且无返回体响应码 */
    public static final Integer NO_CONTENT = 204;
    /** 参数错误响应码 */
    public static final Integer PARAM_ERROR = 400;
    /** 未授权响应码 */
    public static final Integer UNAUTHORIZED = 401;
    /** 禁止访问响应码 */
    public static final Integer FORBIDDEN = 403;
    /** 资源不存在响应码 */
    public static final Integer NOT_FOUND = 404;
    /** 服务器错误响应码 */
    public static final Integer ERROR = 500;
    /**资源重复响应码 */
    public static final Integer RESOURCE_DUPLICATE = 409;




    /**
     * 成功响应(默认200)
     */
    public static <T> Result<T> success(Integer code) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage("操作成功");
        return result;
    }
    public static <T> Result<T> success(){
         return success(SUCCESS);
     }


    /**
     * 成功响应(带数据)
     * @param data 响应数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }


    /**
     * 失败响应
     * @param code 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
