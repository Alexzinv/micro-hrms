package com.alex.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author _Alexzinv_
 * @date 2022/1/13
 * @description 旧用户被踢出后处理方法
 */
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>(5);
        map.put("code", 20001);
        map.put("message", "已在另一台机器登录，您被迫下线" + sessionInformationExpiredEvent
                .getSessionInformation().getLastRequest());

        String json = objectMapper.writeValueAsString(map);
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write(json);
    }
}
