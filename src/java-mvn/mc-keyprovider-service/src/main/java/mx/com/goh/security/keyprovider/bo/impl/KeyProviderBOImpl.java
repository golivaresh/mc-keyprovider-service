package mx.com.goh.security.keyprovider.bo.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;
import javax.crypto.SecretKey;
import mx.com.goh.gcommons.architect.GCommonArchitect;
import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.gcommons.response.CommonResponse.Success;
import mx.com.goh.gcommons.response.Response;
import mx.com.goh.security.keyprovider.bean.ClientKey;
import mx.com.goh.security.keyprovider.bean.KeyProviderBean;
import mx.com.goh.security.keyprovider.bo.KeyProviderBO;
import mx.com.goh.security.keyprovider.client.dao.ClientDAO;
import mx.com.goh.security.keyprovider.client.model.ClientEntity;
import mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO;
import mx.com.goh.security.keyprovider.response.KeyProviderResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation BO class of {@link KeyProviderBO} for the business logic of
 * key provider service.<br>
 * Version control:<br>
 * - 1.0.0 | 11/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Component
public final class KeyProviderBOImpl extends GCommonArchitect implements KeyProviderBO {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7783608760438122634L;

    /**
     * Object DAO for Client operations.
     */
    @Autowired
    private ClientDAO clientDAO;

    /**
     * Object DAO for Key store operations.
     */
    @Autowired
    private KeyStoreDAO keyStoreDAO;

    /**
     * Constructor of KeyProviderBOImpl class.
     */
    public KeyProviderBOImpl() {
        super(KeyProviderBOImpl.class);
    }

    /* (non-Javadoc)
     * @see mx.com.goh.security.keyprovider.bo.KeyProviderBO#getPublicKey(
     * mx.com.goh.security.keyprovider.bean.ClientKey)
     */
    @Override
    public Response<KeyProviderBean> getPublicKey(ClientKey clientKey) throws BusinessException {
        ClientEntity clientEntity = validateClientRequest(clientKey);
        keyStoreDAO.loadKeyStore();

        info("Getting public key");
        PublicKey publicKey = keyStoreDAO.getPublicKey(clientEntity.getServiceKeyAlias()).get();
        info("Public key obtained!");

        return getResponse(Success.SUCCESS, new KeyProviderBean(publicKey, clientKey));
    }

    /* (non-Javadoc)
     * @see mx.com.goh.security.keyprovider.bo.KeyProviderBO#getPrivateKey(
     * mx.com.goh.security.keyprovider.bean.ClientKey)
     */
    @Override
    public Response<KeyProviderBean> getPrivateKey(ClientKey clientKey) throws BusinessException {
        ClientEntity clientEntity = validateClientRequest(clientKey);
        keyStoreDAO.loadKeyStore();
        
        info("Getting private key");
        PrivateKey privateKey = keyStoreDAO.getPrivateKey(
                clientEntity.getServiceKeyAlias(), clientEntity.getServicePwd()).get();
        info("Private key obtained!");

        return getResponse(Success.SUCCESS, new KeyProviderBean(privateKey, clientKey));
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.bo.KeyProviderBO#getSecretKey(mx.com.goh.
     * security.keyprovider.bean.ClientKey)
     */
    @Override
    public Response<KeyProviderBean> getSecretKey(ClientKey clientKey) throws BusinessException {
        ClientEntity clientEntity = validateClientRequest(clientKey);
        keyStoreDAO.loadKeyStore();

        info("Getting secret key");
        SecretKey secretKey = keyStoreDAO.getSecretKey(clientEntity.getServiceKeyAlias()).get();
        info("Secret key obtained!");

        return getResponse(Success.SUCCESS, new KeyProviderBean(secretKey, clientKey));
    }
    
    /**
     * Validate the Client parameter.<br>
     * Find the "issuer" and "service name" in the data base.
     * 
     * @param clientKey
     *            object with the information of the client.
     * @return {@link ClientEntity} object if the values are correct.
     * @throws BusinessException
     *             Error if the validation is not satisfactory.
     */
    private ClientEntity validateClientRequest(final ClientKey clientKey) throws BusinessException {
        if (clientKey == null || StringUtils.isEmpty(clientKey.getIssuer())) {
            error("Client is required");
            throw new BusinessException(KeyProviderResponse.Error.CLIENT_REQUIRED);
        }

        info("Finding client...: " + clientKey.getIssuer());
        Optional<ClientEntity> client = clientDAO.findByIssuer(clientKey.getIssuer());

        if (client.isPresent()) {
            info(String.format("Client [%s] found!", client.get().getIssuer()));
            ClientEntity clientEntity = client.get();

            if (clientEntity.getServiceName() != null && clientEntity.getServiceName() != null
                    && clientEntity.getServiceName().equals(clientKey.getServiceName())) {
                return clientEntity;
            }
            error("Service not found!");
            throw new BusinessException(KeyProviderResponse.Error.SERVICE_NOT_FOUND);
        }
        error("Client not found!");
        throw new BusinessException(KeyProviderResponse.Error.CLIENT_NOT_FOUND);
    }

}
