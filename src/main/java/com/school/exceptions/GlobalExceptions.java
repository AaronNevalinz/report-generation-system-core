package com.school.exceptions;

import com.school.utils.OperationReturnObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public OperationReturnObject handleIllegalArgumentException(IllegalArgumentException exception) {
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setReturnCodeAndMessage(400, exception.getMessage());
        return operationReturnObject;
    }
}
