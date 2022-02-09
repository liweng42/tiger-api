package com.tigerapi.framework.handler;

import com.tigerapi.common.constant.HttpStatus;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.exception.ServiceException;
import com.tigerapi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName GlobalExceptionController
 * @Description
 * @Author jerry
 * @Since 2022/2/9
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return StringUtils.isNotNull(code) ? AjaxResult.error(code, e.getMessage()) : AjaxResult.error(e.getMessage());
    }

    /**
     * 无效参数异常，Srping的Assert.isTrue等方法会抛出
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public AjaxResult handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer code = -1;
        return StringUtils.isNotNull(code) ? AjaxResult.error(code, e.getMessage()) : AjaxResult.error(e.getMessage());
    }



    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
//        log.error(e.getMessage(), e);
//        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        StringBuffer sb = new StringBuffer();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        for (int i = 0; i < fieldErrors.size(); i++) {
//            sb.append(i + 1).append(".").append(fieldErrors.get(i).getDefaultMessage()).append(";");
//        }
        for (FieldError fieldError: fieldErrors) {
            sb.append(fieldError.getDefaultMessage()).append(";");
        }
        log.warn("请求参数错误: {}", sb.toString());
        return AjaxResult.error(sb.toString());
//        return R.fail("", REnum.PARAMETER_ERROR.getStatus(), sb.toString());
    }

}
