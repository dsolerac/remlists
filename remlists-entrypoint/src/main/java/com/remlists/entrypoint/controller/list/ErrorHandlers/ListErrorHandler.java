package com.remlists.entrypoint.controller.list.ErrorHandlers;

import com.remlists.entrypoint.controller.shared.ErrorHandlers.ErrorHandler;
import com.remlists.user.domain.exceptions.EmailAddressAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public final class ListErrorHandler extends ErrorHandler {


    private Logger LOG = LoggerFactory.getLogger(ListErrorHandler.class);


    @ExceptionHandler({EmailAddressAlreadyExistsException.class})
    public ResponseEntity<Object> handleExceptionInAxonBus(Exception ex,
                                                           WebRequest req ) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST); //TODO: Esto hay que verlo, de momento solo he visto que bajo una excepción de
        //      CompletionException vienen las originales mias, pero puede que haya más casos...
        body.put("path", req.getDescription(false));
        body.put("message", ex.getMessage());

        return handleExceptionInternal(ex,

                body,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                req);

    }


}
