package mx.com.goh.security.keyprovider.handler;

import mx.com.goh.gcommons.exception.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler class, this class is used to handle errors in this
 * application.<br>
 * Version control:<br>
 * - 1.0.0 | 13/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Log object for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * {@link BusinessException} handler.<br>
     * This method handle the business errors.
     * 
     * @param ex
     *            {@link BusinessException} object.
     * @param request
     * 
     * @return Response of error for the user.
     */
    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<Object> businessException(BusinessException ex, WebRequest request) {
        ex.getExceptionResponse().getError().setPath(request.getContextPath());
        LOGGER.error(ex);
        return handleExceptionInternal(ex, ex.getExceptionResponse(), 
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * {@link RuntimeException} handler.<br>
     * This method handle the runtime exceptions.
     * 
     * @param ex
     *            {@link RuntimeException} object.
     * @param request
     *            {@link WebRequest} object.
     * @return Response of error for the user.
     */
    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> runtimeExceptionHandler(RuntimeException ex, 
            WebRequest request) {
        BusinessException business = new BusinessException(ex);
        LOGGER.error(ex);
        return handleExceptionInternal(ex, business.getExceptionResponse(), 
                new HttpHeaders(), HttpStatus.CONFLICT,
                request);
    }
}
