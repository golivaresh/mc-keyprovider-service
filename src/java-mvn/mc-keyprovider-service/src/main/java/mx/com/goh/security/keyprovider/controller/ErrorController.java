package mx.com.goh.security.keyprovider.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import mx.com.goh.gcommons.response.BusinessError;
import mx.com.goh.gcommons.response.CommonResponse.Error;
import mx.com.goh.gcommons.response.Response;
import mx.com.goh.gcommons.response.factory.ResponseFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for the control of the errors.<br>
 * Version control:<br>
 * - 1.0.0 | 17/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    /**
     * Constructor of ErrorController class.
     * 
     * @param errorAttributes
     *            Attributes for error.
     */
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * Method for control the errors.
     * 
     * @param request
     *            {@link HttpServletRequest} object for obtain data of the reequest.
     * @return {@link ResponseEntity} with the error details.
     */
    @RequestMapping
    protected ResponseEntity<Object> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Response<HashMap<String, Object>> response = 
                ResponseFactory.getFactory().getResponse(Error.ERROR);
        response.setError(new BusinessError(status.getReasonPhrase(), request.getPathInfo()));
        response.setObject((HashMap<String, Object>) getErrorAttributes(request, true));
        return new ResponseEntity<>(response, status);
    }

    /* (non-Javadoc)
     * @see org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return null;
    }    
}
