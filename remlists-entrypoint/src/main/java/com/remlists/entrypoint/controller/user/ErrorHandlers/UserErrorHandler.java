package com.remlists.entrypoint.controller.user.ErrorHandlers;

import com.remlists.entrypoint.controller.shared.ErrorHandlers.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public final class UserErrorHandler extends ErrorHandler {


    private Logger LOG = LoggerFactory.getLogger(UserErrorHandler.class);






}
