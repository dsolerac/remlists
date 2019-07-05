package com.remlists.entrypoint.controller.shared.ErrorHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;


public class ErrorHandler extends ResponseEntityExceptionHandler {


    private Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncontrolledException(Exception ex, WebRequest req) {

        LOG.warn("Uncontrolled  Error", ex);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", req.getDescription(false));
        body.put("method", ((ServletWebRequest)req).getRequest().getMethod());

        return handleExceptionInternal(ex,
                body,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                req);

    }


    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest req) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", req.getDescription(false));
        body.put("method", ((ServletWebRequest)req).getRequest().getMethod());


        return handleExceptionInternal(ex,
                ex.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                req);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest req) {


        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("error_count", ex.getBindingResult().getFieldErrorCount());
        body.put("message", ex.getMessage());
        body.put("path", req.getDescription(false));
        body.put("method", ((ServletWebRequest)req).getRequest().getMethod());


        //Get all errors
        Map<String, Object> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));


        body.put("fields", errors);


//        return new ResponseEntity<>(body, headers, status);

        return handleExceptionInternal(ex,
                body,
                new HttpHeaders(),
                status,
                req);

    }


    @ExceptionHandler({CompletionException.class})
    public ResponseEntity<Object> handleExceptionInAxonBus(Exception ex,
                                                           WebRequest req ) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.NOT_FOUND); //TODO: Esto hay que verlo, de momento solo he visto que bajo una excepción de
        //      CompletionException vienen las originales mias, pero puede que haya más casos...
        body.put("path", req.getDescription(false));
        body.put("message", ex.getMessage());

        return handleExceptionInternal(ex,

                body,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                req);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest req) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("status", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", req.getDescription(false));
        body.put("method", ((ServletWebRequest)req).getRequest().getMethod());



        return handleExceptionInternal(ex,
                ex.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                req);

    }



/*
*

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

* */


}
