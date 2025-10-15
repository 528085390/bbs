package com.li.bbs.Config.Advice;

import com.li.bbs.Pojo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应拦截器，用于统一处理Result返回值
 */
@RestControllerAdvice
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要处理该响应
     * @param returnType 控制器方法的返回类型
     * @param converterType 使用的HttpMessageConverter类型
     * @return true表示需要处理，false表示不需要处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 只处理返回类型为Result的方法
        return Result.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * 在响应体写入之前进行处理
     * @param body 响应体内容
     * @param returnType 控制器方法的返回类型
     * @param selectedContentType 选择的内容类型
     * @param selectedConverterType 选择的转换器类型
     * @param request 请求对象
     * @param response 响应对象
     * @return 处理后的响应体
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 确保body是Result类型
        if (body instanceof Result) {
            Result result = (Result) body;
            // 将Result中的状态码设置到HTTP响应中
            response.setStatusCode(org.springframework.http.HttpStatus.valueOf(result.getCode()));
        }
        return body;
    }
}
