package webflux.application;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BookRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRequest book = (BookRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
    }
}
