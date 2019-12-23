package mx.com.goh.security.keyprovider.keystore.dao;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Optional;

import javax.crypto.SecretKey;

import mx.com.goh.gcommons.exception.BusinessException;

/**
 * Class for the access to keystore, and obtain the necessary keys.<br>
 * Version control:<br>
 * - 1.0.0 | 12/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public interface KeyStoreDAO {

    /**
     * Load the key store for the get entries.
     * 
     * @throws BusinessException
     *             Business error for the load key store.
     */
    void loadKeyStore() throws BusinessException;

    /**
     * Get the private key from key pair entry.
     * 
     * @param alias
     *            for the key pair entry.
     * @param passwordBase64
     *            password for the private key encode in {@link Base64}.
     * @return {@link Optional} with {@link PrivateKey} obtained from key store.
     * @throws BusinessException
     *             Business error obtaining the private key.
     */
    Optional<PrivateKey> getPrivateKey(String alias, String passwordBase64) 
            throws BusinessException;

    /**
     * Get the public key from key pair entry.
     * 
     * @param alias
     *            for the key pair entry.
     * @return {@link Optional} with {@link PublicKey} obtained from key store.
     * @throws BusinessException
     *             Business error obtaining the public key.
     */
    Optional<PublicKey> getPublicKey(String alias) throws BusinessException;

    /**
     * Get the certificate from key pair entry.
     * 
     * @param alias
     *            for the key pair entry.
     * @return {@link Optional} with {@link Certificate} obtained from key store.
     * @throws BusinessException
     *             Business error obtaining the certificate.
     */
    Optional<Certificate> getCertificate(String alias) throws BusinessException;

    /**
     * Get the secret key from key store.
     * 
     * @param alias
     *            for the secret entry.
     * @return {@link Optional} with {@link SecretKey} obtained from key store.
     * @throws BusinessException
     *             Business error obtaining the secret key.
     */
    Optional<SecretKey> getSecretKey(String alias) throws BusinessException;

    /**
     * Get aliases from key store.
     * 
     * @return {@link Optional} with {@link Enumeration} from {@link String}
     *         obtained from key store.
     * @throws BusinessException
     *             Business error obtaining the aliases list.
     */
    Optional<Enumeration<String>> getAliases() throws BusinessException;
}
