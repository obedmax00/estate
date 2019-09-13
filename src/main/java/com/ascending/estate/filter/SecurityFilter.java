
        package com.ascending.estate.filter;
        import com.ascending.estate.util.JwtUtil;
        import io.jsonwebtoken.Claims;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String AUTH_URI = "/auth";
    @Override
    public void init(FilterConfig filterConfig) {
        // TODO Auto-generated method stub
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        logger.debug(String.format("%s | %s",req.getMethod(),req.getRequestURI()));
        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(request, response);
        else ((HttpServletResponse)response).sendError(statusCode);
    }
    public void destroy() {
        // TODO Auto-generated method stub
    }
    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        String verb = req.getMethod();
        if (uri.equalsIgnoreCase(AUTH_URI + "/agent") || uri.equalsIgnoreCase(AUTH_URI + "/customer") ) {
            return HttpServletResponse.SC_ACCEPTED;
        }
        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;
            Claims claims = JwtUtil.decodeJwtToken(token);
            String allowedResources = "/";
            switch(verb) {
                case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
                case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
                case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
                case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
            }
            logger.debug(allowedResources);
            for (String s : allowedResources.split(",")) {

                if(s.trim().startsWith("not") && uri.trim().toLowerCase().startsWith(s.trim().toLowerCase().substring(3))){
                    break;
                }
                logger.debug(s);
                logger.debug(uri);
                if (!s.trim().startsWith("/") ) continue;
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }
            logger.debug(String.format("Verb: %s, allowed resources: %s", verb, allowedResources));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return statusCode;
    }
}