package com.icompete.mvc.helper;

import com.icompete.dto.UserDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xhulio
 */
public class HelperFunctions {
    /**
     * Get the current logged in user.
     * @param request Http servlet request
     * @return Logged in user. If no user is logged in this value is null
     */
    public static UserDTO getLogedInUser(HttpServletRequest request){
        HttpSession session = request != null ? request.getSession(false) : null;
        UserDTO loggedInUser = session != null ? (UserDTO)session.getAttribute("authenticatedUser") : null;
        
        return loggedInUser;
        
    }
    
    /**
     * Check if a request is an ajax request
     * @param request Http servlet request
     * @return True if is ajax request else return false
     */
    public static boolean isAjax(HttpServletRequest request) {
    String requestedWithHeader = request.getHeader("X-Requested-With");
    return "XMLHttpRequest".equals(requestedWithHeader);
}

}
