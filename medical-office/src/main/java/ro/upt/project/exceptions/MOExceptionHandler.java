package ro.upt.project.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MOExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public String handle(Model model, Exception exception) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }
}
