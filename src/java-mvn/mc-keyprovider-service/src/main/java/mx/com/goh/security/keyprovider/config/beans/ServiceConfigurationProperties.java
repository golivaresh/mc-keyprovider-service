package mx.com.goh.security.keyprovider.config.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure the properties file from yaml file.<br>
 * Version control:<br>
 * - 1.0.0 | 11/01/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "service.config")
public class ServiceConfigurationProperties {

    /**
     * Information for issuer.
     */
    private String issuerInfo;
    
    /**
     * Path/name for the key store location.
     */
    private String keystoreName;
    
    /**
     * Password for the key store.<br>
     * Decode in Base64.
     */
    private String keystorePwd;
    
    /**
     * Key store type PKCS12 or JKS.
     */
    private String keystoreType;

    /**
     * Get the value of issuerInfo.
     * 
     * @return the issuerInfo.
     */
    public String getIssuerInfo() {
        return issuerInfo;
    }

    /**
     * Set the value of issuerInfo.
     * 
     * @param issuerInfo
     *            the issuerInfo to set
     */
    public void setIssuerInfo(String issuerInfo) {
        this.issuerInfo = issuerInfo;
    }

    /**
     * Get the value of keystoreName.
     * 
     * @return the keystoreName.
     */
    public String getKeystoreName() {
        return keystoreName;
    }

    /**
     * Set the value of keystoreName.
     * 
     * @param keystoreName
     *            the keystoreName to set
     */
    public void setKeystoreName(String keystoreName) {
        this.keystoreName = keystoreName;
    }

    /**
     * Get the value of keystorePwd.
     * 
     * @return the keystorePwd.
     */
    public String getKeystorePwd() {
        return keystorePwd;
    }

    /**
     * Set the value of keystorePwd.
     * 
     * @param keystorePwd
     *            the keystorePwd to set
     */
    public void setKeystorePwd(String keystorePwd) {
        this.keystorePwd = keystorePwd;
    }

    /**
     * Get the value of keystoreType.
     * 
     * @return the keystoreType.
     */
    public String getKeystoreType() {
        return keystoreType;
    }

    /**
     * Set the value of keystoreType.
     * 
     * @param keystoreType
     *            the keystoreType to set
     */
    public void setKeystoreType(String keystoreType) {
        this.keystoreType = keystoreType;
    }
}
