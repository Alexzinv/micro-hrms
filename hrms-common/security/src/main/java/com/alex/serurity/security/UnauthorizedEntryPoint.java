package com.alex.serurity.security;

import com.alex.util.ResponseUtil;
import com.alex.util.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未授权的统一处理方式
 * @author _
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {

        ResponseUtil.out(response, R.err().message("未授权"));
    }
}
