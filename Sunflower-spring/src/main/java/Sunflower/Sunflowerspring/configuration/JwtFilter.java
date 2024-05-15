package Sunflower.Sunflowerspring.configuration;

import Sunflower.Sunflowerspring.dto.ApiReturnDto;
import Sunflower.Sunflowerspring.service.UserService;
import Sunflower.Sunflowerspring.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization={}" , authorization);

        //토큰 안보내면 막아버림
        if (authorization.equals("authorization")) {

        } else {
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                log.error("authentication을 잘못 보냈습니다.");
                filterChain.doFilter(request,response);
                //sendErrorResponse(response, 0, "missing");
                return;
            }

            ApiReturnDto apiReturnDto = new ApiReturnDto();

            //토큰 추출하기
            String token = authorization.split(" ")[1];

            //토큰이 시간초과됐는지 체크하기
            if (JwtTokenUtil.isExpired(token, key)) {
                log.error("토큰이 만료됐습니다.");
                filterChain.doFilter(request,response);
                sendErrorResponse(response, 0, "expired");

            }

            //발급받은 토큰에서 userName을 꺼냄
            String id = JwtTokenUtil.getUserId(token, key);
            log.info("token: {}", token);
            log.info("key: {}", key);
            log.info("id: {}", id);


            // 권한 부여
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(id,null, List.of(new SimpleGrantedAuthority("USER")));

            // Detail을 넣어.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
                //sendErrorResponse(response, 0, "access");

        }

    }
    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized status
        response.setContentType("application/json");

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status);
        errorResponse.put("message", message);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
