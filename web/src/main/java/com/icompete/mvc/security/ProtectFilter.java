package com.icompete.mvc.security;

import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.UserType;
import com.icompete.facade.UserFacade;
import java.io.File;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@WebFilter(urlPatterns = {"/event/new", "/event/newRegistration", "/event/*", "/sport/new", "/sport/delete", "/sport/edit"})
public class ProtectFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, 
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        HttpSession session = request.getSession(false);
        
        UserDTO loggedInUser = session != null ? (UserDTO)session.getAttribute("authenticatedUser") : null;
        
        boolean loggedIn = loggedInUser != null;
       
        if (!loggedIn) {
            response.sendRedirect(request.getContextPath() + "/login/login");
            return;
        }
        
        
        //Redirect to home if user doesn't have the required permission to access this directory
        if(!userCanAccess(r, loggedInUser)){
            response.sendRedirect(request.getContextPath() + "/home/home");
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
            if (user.getUserType().equals(UserType.ADMIN)) {
                return true;
            } else {
                return false;
            }
        } else if (url.endsWith("/event/newRegistration")) {
            if (user.getUserType().equals(UserType.ADMIN) || user.getUserType().equals(UserType.SPORTSMAN)) {
                return true;
            } else {
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
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {

    }

}
