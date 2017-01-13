package com.icompete.mvc.security;

import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.UserType;
import com.icompete.facade.UserFacade;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebFilter(urlPatterns = {"/event/new", "/event/newRegistration","/event/*"})
public class ProtectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        String logname = creds[0];
        String password = creds[1];

        //get Spring context and UserFacade from it
        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(UserFacade.class);
        UserDTO matchingUser = userFacade.getUsersByUserName(logname);
        if (matchingUser == null) {
            response401(response);
            return;
        }
        
        r.setAttribute("authenticatedUser", matchingUser);
        
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setUserId(matchingUser.getId());
        userAuthenticateDTO.setPassword(password);

        if (!userCanAccess(r, matchingUser)) {
            response401(response);
            return;
        }
        
        
        
        chain.doFilter(request, response);
    }

    private boolean userCanAccess(ServletRequest request, UserDTO user) {
        String url = "";
        if (request instanceof HttpServletRequest) {
            url = ((HttpServletRequest) request).getRequestURL().toString();
        }

        if (url.endsWith("/event/new")) {
            if(user.getUserType().equals(UserType.ADMIN)){
                return true;
            }else{
                return false;
            }
        } else if (url.endsWith("/event/newRegistration")) {
            if(user.getUserType().equals(UserType.ADMIN) || user.getUserType().equals(UserType.SPORTSMAN)){
                return true;
            }else{
                return false;
            }
        } else {
            return true;
        }

    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type username and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> You do not have access to this page</body></html>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
