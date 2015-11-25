package no.westerdals.westbook.rest;

import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.responses.ResultResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static no.westerdals.westbook.responses.ResultResponse.newErrorResult;

@ControllerAdvice
public class RestExceptionHandler
{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResultResponse handleException(Exception e, HttpServletRequest req) throws Exception
    {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        ResultResponse result = newErrorResult(MessageConstant.UNMAPPED_ERROR, UUID.randomUUID().toString());
        System.out.println("An exception occurred with the id " + result.getExtra() + " while accessing " + req.getRequestURI());
        e.printStackTrace();
        return result;
    }
}
