package mx.com.goh.security.keyprovider.bean;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

/**
 * Bean for the keys response. Implements properties of {@link ClientKey}
 * class.<br>
 * Version control:<br>
 * - 1.0.0 | 11/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public class KeyProviderBean extends ClientKey implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3414213357703413211L;

    /**
     * Private key.
     */
    private KeyBean privateKey;

    /**
     * Public key.
     */
    private KeyBean publicKey;

    /**
     * Secret key.
     */
    private KeyBean secretKey;

    /**
     * Constructor of KeyProviderBean class.
     */
    public KeyProviderBean() {
        super();
    }
    
    /**
     * Constructor of KeyProviderBean class.<br>
     * Initialize secret key from {@link SecretKey} parameter.
     * 
     * @param secretKey
     *            object, for the get secret.
     */
    public KeyProviderBean(SecretKey secretKey) {
        super();
        if (secretKey != null) {
            this.secretKey = new KeyBean(secretKey.getEncoded(), secretKey.getAlgorithm());
        }
    }

    /**
     * Constructor of KeyProviderBean class.<br>
     * Initialize private key and public key from {@link KeyPair} parameter.
     * 
     * @param keyPair
     *            object type {@link KeyPair} for the get keys.
     */
    public KeyProviderBean(KeyPair keyPair) {
        super();
        if (keyPair != null && keyPair.getPrivate() != null) {
            this.privateKey = new KeyBean(keyPair.getPrivate().getEncoded(), 
                    keyPair.getPrivate().getAlgorithm());
            if (keyPair.getPublic() != null) {
                this.publicKey = new KeyBean(keyPair.getPublic().getEncoded(), 
                        keyPair.getPublic().getAlgorithm());
            }
        }
    }


    /**
     * Constructor of KeyProviderBean class.<br>
     * Initialize private key and public key from {@link KeyPair} parameter and set
     * the Client information.
     * 
     * @param keyPair
     *            object type {@link KeyPair} for the get keys.
     * @param clientKey
     *            object for get the Client information.
     */
    public KeyProviderBean(KeyPair keyPair, ClientKey clientKey) {
        super(getClientkey(clientKey).getIssuer(), getClientkey(clientKey).getServiceName());
        if (keyPair != null && keyPair.getPrivate() != null) {
            this.privateKey = new KeyBean(keyPair.getPrivate().getEncoded(), 
                    keyPair.getPrivate().getAlgorithm());
            if (keyPair.getPublic() != null) {
                this.publicKey = new KeyBean(keyPair.getPublic().getEncoded(), 
                        keyPair.getPublic().getAlgorithm());
            }
        }
    }

    /**
     * Constructor of KeyProviderBean class.<br>
     * Initialize secret key from {@link SecretKey} parameter.
     * 
     * @param secretKey
     *            object, for the get secret.
     * @param clientKey
     *            object for get the Client information.
     */
    public KeyProviderBean(SecretKey secretKey, ClientKey clientKey) {
        super(getClientkey(clientKey).getIssuer(), getClientkey(clientKey).getServiceName());
        if (secretKey != null) {
            this.secretKey = new KeyBean(secretKey.getEncoded(), secretKey.getAlgorithm());
        }
    }
    
    /**
     * Constructor of KeyProviderBean class.
     * 
     * @param publicKey
     *            object, for the get public key.
     * @param clientKey
     *            object for get the Client information.
     */
    public KeyProviderBean(PublicKey publicKey, ClientKey clientKey) {
        super(getClientkey(clientKey).getIssuer(), getClientkey(clientKey).getServiceName());
        if (publicKey != null) {
            this.publicKey = new KeyBean(publicKey.getEncoded(), publicKey.getAlgorithm());
        }
    }

    /**
     * Constructor of KeyProviderBean class.
     * 
     * @param privateKey
     *            object, for the get private key.
     * @param clientKey
     *            object for get the Client information.
     */
    public KeyProviderBean(PrivateKey privateKey, ClientKey clientKey) {
        super(getClientkey(clientKey).getIssuer(), getClientkey(clientKey).getServiceName());
        if (privateKey != null) {
            this.privateKey = new KeyBean(privateKey.getEncoded(), privateKey.getAlgorithm());
        }
    }

    /**
     * Get the {@link ClientKey}.<br>
     * This method helps initialize the parent class with arguments.
     * 
     * @param clientKey
     *            object.
     * @return If clientKey parameter is null return new {@link ClientKey} object,
     *         otherwise the same object of the parameter returns.
     */
    private static ClientKey getClientkey(ClientKey clientKey) {
        if (clientKey == null) {
            return new ClientKey();
        }
        return clientKey;
    }

    /**
     * Get the value of privateKey.
     * 
     * @return the privateKey.
     */
    public KeyBean getPrivateKey() {
        return privateKey;
    }

    /**
     * Set the value of privateKey.
     * 
     * @param privateKey
     *            the privateKey to set
     */
    public void setPrivateKey(KeyBean privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Get the value of publicKey.
     * 
     * @return the publicKey.
     */
    public KeyBean getPublicKey() {
        return publicKey;
    }

    /**
     * Set the value of publicKey.
     * 
     * @param publicKey
     *            the publicKey to set
     */
    public void setPublicKey(KeyBean publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Get the value of secretKey.
     * 
     * @return the secretKey.
     */
    public KeyBean getSecretKey() {
        return secretKey;
    }

    /**
     * Set the value of secretKey.
     * 
     * @param secretKey
     *            the secretKey to set
     */
    public void setSecretKey(KeyBean secretKey) {
        this.secretKey = secretKey;
    }

    protected class KeyBean implements Serializable {
        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = 654448228043055051L;

        /**
         * Encode key text.
         */
        private byte[] encode;

        /**
         * Key algorithm.
         */
        private String algorithm;

        /**
         * Constructor of KeyBean class.
         */
        public KeyBean() {
            super();
        }

        /**
         * Constructor of KeyBean class.
         * 
         * @param encode
         *            key text.
         * @param algorithm
         *            for the key.
         */
        public KeyBean(byte[] encode, String algorithm) {
            super();
            this.encode = encode;
            this.algorithm = algorithm;
        }

        /**
         * Get the value of encode.
         * 
         * @return the encode.
         */
        public byte[] getEncode() {
            return encode;
        }

        /**
         * Set the value of encode.
         * 
         * @param encode
         *            the encode to set
         */
        public void setEncode(byte[] encode) {
            this.encode = encode;
        }

        /**
         * Get the value of algorithm.
         * 
         * @return the algorithm.
         */
        public String getAlgorithm() {
            return algorithm;
        }

        /**
         * Set the value of algorithm.
         * 
         * @param algorithm
         *            the algorithm to set
         */
        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }
}
