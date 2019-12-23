package mx.com.goh.security.keyprovider.keystore.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.Optional;
import javax.crypto.SecretKey;
import mx.com.goh.gcommons.architect.GCommonArchitect;
import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.security.keyprovider.config.beans.ServiceConfigurationProperties;
import mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO;
import mx.com.goh.security.keyprovider.util.KeyProviderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Class that implements {@link KeyStoreDAO} for the access the keystore.<br>
 * Version control:<br>
 * - 1.0.0 | 12/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Component
public final class KeyStoreDAOImpl extends GCommonArchitect implements KeyStoreDAO {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6828418853126112687L;

    /**
     * Configuration properties.
     */
    @Autowired
    private ServiceConfigurationProperties keyProviderConfigProperties;

    /**
     * {@link KeyStore} object.
     */
    private KeyStore keyStore;

    /**
     * Constructor of KeyStoreDAOImpl class.
     */
    public KeyStoreDAOImpl() {
        super(KeyStoreDAOImpl.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#loadKeyStore()
     */
    @Override
    public void loadKeyStore() throws BusinessException {
        info("Loading keystore...");
        char[] pwdArray = KeyProviderUtils.decodeBase64String(
                keyProviderConfigProperties.getKeystorePwd())
                .toCharArray();
        try (InputStream is = new FileInputStream(keyProviderConfigProperties.getKeystoreName())) {
            info("Loading keystore...");
            info("Getting keystore instance...");
            keyStore = KeyStore.getInstance(keyProviderConfigProperties.getKeystoreType());
            keyStore.load(is, pwdArray);
            info("Keystore is loaded correctly!");
        } catch (CertificateException | KeyStoreException 
                | NoSuchAlgorithmException | IOException e) {
            error("An error occurred while loading the keystore!" + e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#getPrivateKey(java.
     * lang.String, java.lang.String)
     */
    @Override
    public Optional<PrivateKey> getPrivateKey(String alias, String passwordBase64) 
            throws BusinessException {
        try {
            info("Finding private key: " + alias);
            String pwd = KeyProviderUtils.decodeBase64String(passwordBase64);
            return Optional.of((PrivateKey) keyStore.getKey(alias, pwd.toCharArray()));
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            error("An error occurred while obtaining the private key!" + e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#getPublicKey(java.
     * lang.String)
     */
    @Override
    public Optional<PublicKey> getPublicKey(String alias) throws BusinessException {
        try {
            info("Getting public key of: " + alias);
            Certificate cert = keyStore.getCertificate(alias);
            if (cert != null) {
                info("Public key obtained!");
                return Optional.of(cert.getPublicKey());
            }
            info("Can not obtain the public key!");
            return Optional.empty();
        } catch (KeyStoreException e) {
            error("An error occurred while obtaining the public key!" + e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#getCertificate(java.
     * lang.String)
     */
    @Override
    public Optional<Certificate> getCertificate(String alias) throws BusinessException {
        try {
            info("Getting certificate of: " + alias);
            return Optional.of(keyStore.getCertificate(
                    StringUtils.defaultString(alias, StringUtils.EMPTY)));
        } catch (KeyStoreException e) {
            error("An error occurred while obtaining the certificate!" + e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#getSecretKey(java.
     * lang.String)
     */
    @Override
    public Optional<SecretKey> getSecretKey(String alias) throws BusinessException {
        try {
            info("Getting secret key of: " + alias);
            String pwd = KeyProviderUtils.decodeBase64String(
                    keyProviderConfigProperties.getKeystorePwd());
            return Optional.of((SecretKey) keyStore.getKey(alias, pwd.toCharArray()));
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            error("An error occurred while obtaining the secret key!" + e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.com.goh.security.keyprovider.keystore.dao.KeyStoreDAO#getAliases()
     */
    @Override
    public Optional<Enumeration<String>> getAliases() throws BusinessException {
        try {
            info("Getting aliasesof the keystore...");
            if (keyStore != null) {
                return Optional.of(keyStore.aliases());
            }
            info("Aliases not found!");
            return Optional.empty();
        } catch (KeyStoreException e) {
            error("An error occurred while the aliases where obtained" + e);
            throw new BusinessException(e);
        }
    }
}
