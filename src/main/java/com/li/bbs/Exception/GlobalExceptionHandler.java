package com.li.bbs.Exception;

import com.li.bbs.Pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("服务器异常", e);
        return Result.error(500, "服务器异常: " + e.getMessage());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return Result.error(500, "服务器内部错误");
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常", e);
        return Result.error(Result.PARAM_ERROR, "请求参数错误: " + e.getMessage());
    }

    /**
     * 处理数据库异常
     */
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        log.error("数据库异常", e);
        return Result.error(Result.ERROR, "数据访问异常: " + e.getMessage());
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常", e);
        return Result.error(Result.ERROR, "操作失败: " + e.getMessage());
    }

    /**
     * 处理资源未找到异常
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Result<String> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("资源未找到异常", e);
        return Result.error(Result.NOT_FOUND, "资源未找到: " + e.getMessage());
    }

    /**
     * 处理资源重复异常
     */
    @ExceptionHandler(ResourceDuplicateException.class)
    public Result<String> handleResourceDuplicateException(ResourceDuplicateException e) {
        log.error("资源重复异常", e);
        return Result.error(Result.RESOURCE_DUPLICATE, "资源已存在: " + e.getMessage());
    }

    /**
     * 处理无权访问异常
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> handleBadCredentialsException(BadCredentialsException e) {
        log.error("无权访问异常", e);
        return Result.error(Result.UNAUTHORIZED, "无权访问: " + e.getMessage());
    }

    /**
     * 处理参数验证异常
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorMsg.append(error.getDefaultMessage()).append("; ");
        });
        return Result.error(Result.PARAM_ERROR, errorMsg.toString());
    }
}
