package mx.com.goh.security.keyprovider.bo;

import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.gcommons.response.Response;
import mx.com.goh.security.keyprovider.bean.ClientKey;
import mx.com.goh.security.keyprovider.bean.KeyProviderBean;

/**
 * BO class for the business logic of key provider service.<br>
 * Version control:<br>
 * - 1.0.0 | 11/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public interface KeyProviderBO {
    
    /**
     * Get the public key for client.
     * 
     * @param clientKey
     *            client bean for search the public key.
     * @return with the requested public key.
     * @throws BusinessException
     *             Error when searched the public key for the client.
     */
    Response<KeyProviderBean> getPublicKey(ClientKey clientKey) throws BusinessException;

    /**
     * Get the private key for client.
     * 
     * @param clientKey
     *            client bean for search the private key.
     * @return with the requested private key.
     * @throws BusinessException
     *             Error when searched the private key for the client.
     */
    Response<KeyProviderBean> getPrivateKey(ClientKey clientKey) throws BusinessException;

    /**
     * Get the secret key for client.
     * 
     * @param clientKey
     *            client bean for search the secret key.
     * @return with the requested key.
     * @throws BusinessException
     *             Error when searched the secret key for the client.
     */
    Response<KeyProviderBean> getSecretKey(ClientKey clientKey) throws BusinessException;
}
