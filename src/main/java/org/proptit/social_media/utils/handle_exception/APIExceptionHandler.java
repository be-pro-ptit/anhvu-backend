package org.proptit.social_media.utils.handle_exception;

import org.proptit.social_media.base.BaseResponse;
import org.proptit.social_media.base.Status;
import org.proptit.social_media.exeption.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public BaseResponse handleNotFoundException(NotFoundException e) {
        return BaseResponse.error(new Status("404", e.getMessage()));
    }
}
