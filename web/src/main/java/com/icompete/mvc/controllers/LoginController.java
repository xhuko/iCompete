package com.icompete.mvc.controllers;

import com.icompete.dto.UserAuthenticateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.UserType;
import com.icompete.facade.UserFacade;
import com.icompete.mvc.helper.HelperFunctions;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        //Recirect home if user is already logged in
        if(HelperFunctions.getLogedInUser(request) != null){
            return "redirect:/home/home";
        }
        return "login/login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){

        HttpSession session = request.getSession(false);
      
        if (session.getAttribute("authenticatedUser") != null ){  
            session.invalidate();
        }
        redirectAttributes.addFlashAttribute("alert_success", "You were successfully loged out");
        return "redirect:" + uriBuilder.path("/").toUriString();
    }
    
    @RequestMapping(value = "/trylogin", method = RequestMethod.POST)
    public String tryLogin(@RequestParam String logname, @RequestParam String password, 
            HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) throws IOException {

        if (logname == null || logname.isEmpty() || password == null || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("alert_danger", "User with this credentials doesn't exist");
            return "redirect:/Login/trylogin";
        }

        //get Spring context and UserFacade from it
        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(UserFacade.class);
        UserDTO matchingUser = userFacade.getUsersByUserName(logname);
        if (matchingUser == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "User with this credentials doesn't exist");
            return "redirect:/Login/trylogin";
        }

        request.getSession().setAttribute("authenticatedUser", matchingUser);

        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setUserId(matchingUser.getId());
        userAuthenticateDTO.setPassword(password);
        
        return "redirect:/home/home";
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
}
