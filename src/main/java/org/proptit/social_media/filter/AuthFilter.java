package org.proptit.social_media.filter;


import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proptit.social_media.base.BaseResponse;
import org.proptit.social_media.base.Status;
import org.proptit.social_media.exeption.NotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthFilter implements Filter {

    private Gson gson;

    @Override
    public void init(FilterConfig filterConfig) {
        gson = new Gson();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        HttpServletResponse rp = (HttpServletResponse) servletResponse;
        String url = rq.getRequestURI();
        if (url.startsWith("/api/auth")) {
            filterChain.doFilter(rq, rp);
            return;
        } else if (url.startsWith("/api")) {
            String accessToken = rq.getHeader("Authorization");
            if (accessToken == null) {
                rp.setStatus(401);
                rp.getWriter()
                  .write(gson.toJson(BaseResponse.error(new Status("unauthorized", "Chưa đăng nhập"))));
                return;
            }
            filterChain.doFilter(rq, rp);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
