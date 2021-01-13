package com.tools.st.config;

import org.apache.catalina.AccessLog;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TomcatLogValve extends ValveBase implements AccessLog {
    private boolean requestAttributesEnabled;

    private static Logger logger = LoggerFactory.getLogger("com.ymc.mes");

    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void log(Request request, Response response, long time) {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(DATETIME_FORMATTER.format(LocalDateTime.now())).append(']')
                .append(" [").append(time).append(']')
                .append(' ').append(response.getStatus())
                .append(' ').append(request.getMethod())
                .append(' ').append(request.getRequestURI());
//        if (logger.isDebugEnabled()) {
        sb.append('?');
        request.getParameterMap().entrySet().stream().forEach(e -> {
            sb.append(e.getKey()).append("=").append(StringUtils.join(e.getValue())).append("&");
        });
//        }
        logger.info(sb.toString());
    }

    @Override
    public void setRequestAttributesEnabled(boolean requestAttributesEnabled) {
        this.requestAttributesEnabled = requestAttributesEnabled;
    }

    @Override
    public boolean getRequestAttributesEnabled() {
        return requestAttributesEnabled;
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        getNext().invoke(request, response);
    }
}
