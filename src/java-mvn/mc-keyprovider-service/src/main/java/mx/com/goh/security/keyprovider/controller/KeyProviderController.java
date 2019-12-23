package mx.com.goh.security.keyprovider.controller;

import mx.com.goh.gcommons.architect.GCommonArchitect;
import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.gcommons.response.Response;
import mx.com.goh.security.keyprovider.bean.ClientKey;
import mx.com.goh.security.keyprovider.bean.KeyProviderBean;
import mx.com.goh.security.keyprovider.bo.KeyProviderBO;
import mx.com.goh.security.keyprovider.response.KeyProviderResponse;
import mx.com.goh.security.keyprovider.util.KeyProviderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for the key provider endpoint.<br>
 * Version control:<br>
 * - 1.0.0 | 09/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@RestController
@RequestMapping(KeyProviderUtils.REQUEST_PATH_ROOT_KPCONTROLLER)
public class KeyProviderController extends GCommonArchitect {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 8529479540834109363L;

    /**
     * Request parameter for issuer.
     */
    private static final String REQ_PARAM_ISSUER = "issuer";

    /**
     * Request parameter for service name.
     */
    private static final String REQ_PARAM_SERVICE_NAME = "service-name";

    /**
     * Constructor of KeyProviderController class.
     */
    public KeyProviderController() {
        super(KeyProviderController.class);
    }

    @Autowired
    private KeyProviderBO keyProviderBO;

    /**
     * End point for get the public key.
     * 
     * @param issuer
     *            parameter that contains the name of the issuer of the client.
     * @param serviceName
     *            parameter that contains the service name of the client of the
     *            application.
     * @return {@link Response} with the object type {@link KeyProviderBean} that
     *         contains the public key.
     * @throws BusinessException
     *             Error occurred when obtaining the public key.
     */
    @GetMapping(KeyProviderUtils.REQUEST_PATH_PUBLIC_KEY)
    public Response<KeyProviderBean> getPublicKey(
            @RequestParam(value = REQ_PARAM_ISSUER, required = false) String issuer,
            @RequestParam(value = REQ_PARAM_SERVICE_NAME, required = false) String serviceName)
            throws BusinessException {
        validateParameters(issuer, serviceName, KeyProviderUtils.REQUEST_PATH_PUBLIC_KEY);
        info("Getting public key...");
        Response<KeyProviderBean> response = keyProviderBO.getPublicKey(
                new ClientKey(issuer, serviceName));
        return response;
    }

    /**
     * End point for get the private key.
     * 
     * @param issuer
     *            parameter that contains the name of the issuer of the client.
     * @param serviceName
     *            parameter that contains the service name of the client of the
     *            application.
     * @return {@link Response} with the object type {@link KeyProviderBean} that
     *         contains the private key.
     * @throws BusinessException
     *             Error occurred when obtaining the private key.
     */
    @GetMapping(KeyProviderUtils.REQUEST_PATH_PRIVATE_KEY)
    public Response<KeyProviderBean> getPrivateKey(
            @RequestParam(value = REQ_PARAM_ISSUER, required = false) String issuer,
            @RequestParam(value = REQ_PARAM_SERVICE_NAME, required = false) String serviceName)
            throws BusinessException {
        validateParameters(issuer, serviceName, KeyProviderUtils.REQUEST_PATH_PRIVATE_KEY);
        info("Getting private key...");
        return keyProviderBO.getPrivateKey(new ClientKey(issuer, serviceName));
    }

    /**
     * End point for get the secret key.
     * 
     * @param issuer
     *            parameter that contains the name of the issuer of the client.
     * @param serviceName
     *            parameter that contains the service name of the client of the
     *            application.
     * @return {@link Response} with the object type {@link KeyProviderBean} that
     *         contain the secret key.
     * @throws BusinessException
     *             Error occurred when obtaining the secret key.
     */
    @GetMapping(KeyProviderUtils.REQUEST_PATH_SECRET_KEY)
    public Response<KeyProviderBean> getSecretKey(
            @RequestParam(value = REQ_PARAM_ISSUER, required = false) String issuer,
            @RequestParam(value = REQ_PARAM_SERVICE_NAME, required = false) String serviceName)
            throws BusinessException {
        validateParameters(issuer, serviceName, KeyProviderUtils.REQUEST_PATH_SECRET_KEY);
        info("Getting secret key...");
        return keyProviderBO.getSecretKey(new ClientKey(issuer, serviceName));
    }

    /**
     * Validate the parameters.<br>
     * The parameters has not been empty.
     * 
     * @param issuer
     *            parameter that contains the name of the issuer of the client.
     * @param serviceName
     *            parameter that contains the service name of the client of the
     *            application.
     * @param path
     *            Path that receive the parameters.
     * @throws BusinessException
     *             Error generated when the parameters are not valid.
     */
    private void validateParameters(final String issuer, 
            final String serviceName, final String path)
            throws BusinessException {
        info("issuer: " + issuer);
        info("service-name: " + serviceName);
        if (StringUtils.isEmpty(issuer) || StringUtils.isEmpty(serviceName)) {
            error("The parameters could not be found!");

            throw new BusinessException(KeyProviderResponse.Error.PARAMETERS_NOTFOUND,
                    String.format("issuer: [%s], service-name: [%s]", issuer, serviceName),
                    KeyProviderUtils.REQUEST_PATH_PRIVATE_KEY);
        }
    }
}
