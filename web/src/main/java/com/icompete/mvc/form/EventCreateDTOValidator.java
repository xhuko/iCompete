package com.icompete.mvc.form;


import com.icompete.dto.EventDTO;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EventDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventDTO eventDTO = (EventDTO) target;
        if(eventDTO.getStartDate() == null) return;
        if(eventDTO.getEndDate() == null) return;
        if(eventDTO.getStartDate().compareTo(eventDTO.getEndDate()) > 0) return;
    }
}
