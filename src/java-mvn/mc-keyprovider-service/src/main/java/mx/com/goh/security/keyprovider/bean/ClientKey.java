package mx.com.goh.security.keyprovider.bean;

import java.io.Serializable;

/**
 * Bean for the Client properties.<br>
 * Version control:<br>
 * - 1.0.0 | 11/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public class ClientKey implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7647593520036826746L;

    /**
     * Issuer for the key.
     */
    private String issuer;

    /**
     * Service name.
     */
    private String serviceName;

    /**
     * Constructor of ClientKey class.
     */
    public ClientKey() {
        super();
    }

    /**
     * Constructor of ClientKey class.
     * 
     * @param issuer
     *            issuer client name.
     * @param serviceName
     *            service name for the search the key.
     */
    public ClientKey(String issuer, String serviceName) {
        super();
        this.issuer = issuer;
        this.serviceName = serviceName;
    }

    /**
     * Get the value of issuer.
     * 
     * @return the issuer.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Set the value of issuer.
     * 
     * @param issuer
     *            the issuer to set
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Get the value of serviceName.
     * 
     * @return the serviceName.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Set the value of serviceName.
     * 
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
