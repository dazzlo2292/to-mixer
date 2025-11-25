package ru.lounge.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lounge.dto.ErrorDto;


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

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorDto> handeBusinessLogicException(BusinessLogicException ex) {
        ErrorDto response = new ErrorDto("business-logic-error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDto> handeNullPointerException(Exception ex) {
//        ErrorDto response = new ErrorDto("server-error", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }
}
