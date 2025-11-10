package ru.lounge.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import ru.lounge.dto.ErrorDto;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handeEntityNotFoundException(EntityNotFoundException ex) {
        ErrorDto response = new ErrorDto("not-found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDto> handeValidationException(ValidationException ex) {
        ErrorDto response = new ErrorDto("validation-error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handeNullPointerException(Exception ex) {
        return new ModelAndView("error",
                "errorText", "Something went wrong...");
    }
}
