package study.board.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class WebAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("WebAccessDeniedHandler ::: ");
        // 권한이 없는 경우 페이지 이동 시 사용
        response.sendRedirect("/error/error403");
        // 권한이 없는 경우 에러코드 반환 시 사용
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
