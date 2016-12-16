package com.icompete.mvc.form;


import com.icompete.dto.EventDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EventDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventDTO eventDTO = (EventDTO) target;
        if (eventDTO.getSport()== null) return;
        if(eventDTO.getStartDate() == null) return;
        if(eventDTO.getEndDate() == null) return;
        if(eventDTO.getStartDate().compareTo(eventDTO.getEndDate()) > 0) return;
    }
}
