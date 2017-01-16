package com.icompete.mvc.controllers;

import com.icompete.dto.UserCreateDTO;
import com.icompete.dto.UserDTO;
import com.icompete.enums.UserType;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.UserFacade;
import com.icompete.mvc.form.UserCreateDTOValidator;
import com.icompete.mvc.helper.HelperFunctions;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Xhulio
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("eventCreate")
public class UserController {

    @Inject
    private UserFacade userFacade;

    @ModelAttribute("userTypes")
    public UserType[] sports() {
        return UserType.values();
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof UserCreateDTO) {
            binder.addValidators(new UserCreateDTOValidator());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping("/show")
    public String show(Model model, HttpServletRequest request) {

        Collection<UserDTO> allUsers = userFacade.getAllUsers();

        model.addAttribute("allUsers", allUsers);

        return "user/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        UserCreateDTO userCreate = new UserCreateDTO();
        model.addAttribute("userCreate", userCreate);
        model.addAttribute("nowDate", new Date());
        return "user/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("userCreate") UserCreateDTO user, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "/user/new";
        }
        userFacade.createUser(user);

        return "redirect:/user/show";

    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam("userId") long userId, HttpServletRequest request) {

        try {
            UserDTO authenticatedUser = HelperFunctions.getLogedInUser(request);

            if (authenticatedUser.getUserType().equals(UserType.ADMIN)) {
                UserDTO user = userFacade.getUserById(userId);

                if (user != null) {
                    userFacade.deleteUser(user);
                    return "{\"success\":true}";
                }
            }
        } catch (EntityNotFoundException ex) {
            return "{\"success\":false}";
        }
        return "{\"success\":false}";

    }


}
