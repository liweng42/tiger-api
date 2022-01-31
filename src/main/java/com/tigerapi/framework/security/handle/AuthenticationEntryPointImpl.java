package com.tigerapi.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tigerapi.common.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.tigerapi.common.constant.HttpStatus;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.utils.ServletUtils;
import com.tigerapi.common.utils.StringUtils;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JsonUtils.toJSONString(AjaxResult.error(code, msg)));
    }
}
