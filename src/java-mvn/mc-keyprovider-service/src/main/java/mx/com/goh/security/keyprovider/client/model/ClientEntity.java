package mx.com.goh.security.keyprovider.client.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.NotNull;
import mx.com.goh.security.keyprovider.repository.EntityBaseMongo;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class for the clients.<br>
 * This class extends {@link EntityBaseMongo}.<br>
 * Version control:<br>
 * - 1.0.0 | 10/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Document(collection = "clients")
@JsonPropertyOrder({ "id", "issuer", "serviceName", "serviceKeyAlias", "servicePwd" })
public class ClientEntity extends EntityBaseMongo {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6732349376463768227L;

    /**
     * Issuer name.
     */
    @NotNull
    private String issuer;

    /**
     * Service name.
     */
    @NotNull
    private String serviceName;

    /**
     * Key alias for the service.
     */
    @NotNull
    private String serviceKeyAlias;

    /**
     * Service password.
     */
    private String servicePwd;

    /**
     * Constructor of ClientEntity class.
     */
    public ClientEntity() {
        super();
    }

    /**
     * Constructor of ClientEntity class.
     * 
     * @param id
     *            of the entity.
     * @param issuer
     *            issuer name.
     * @param serviceName
     *            service name.
     * @param serviceKeyAlias
     *            alias for the key.
     * @param servicePwd
     *            password for the key.
     */
    public ClientEntity(@NotNull Long id, @NotNull String issuer, @NotNull String serviceName,
            @NotNull String serviceKeyAlias, String servicePwd) {
        super(id);
        this.issuer = issuer;
        this.serviceName = serviceName;
        this.serviceKeyAlias = serviceKeyAlias;
        this.servicePwd = servicePwd;
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

    /**
     * Get the value of serviceKeyAlias.
     * 
     * @return the serviceKeyAlias.
     */
    public String getServiceKeyAlias() {
        return serviceKeyAlias;
    }

    /**
     * Set the value of serviceKeyAlias.
     * 
     * @param serviceKeyAlias
     *            the serviceKeyAlias to set
     */
    public void setServiceKeyAlias(String serviceKeyAlias) {
        this.serviceKeyAlias = serviceKeyAlias;
    }

    /**
     * Get the value of servicePwd.
     * 
     * @return the servicePwd.
     */
    public String getServicePwd() {
        return servicePwd;
    }

    /**
     * Set the value of servicePwd.
     * 
     * @param servicePwd
     *            the servicePwd to set
     */
    public void setServicePwd(String servicePwd) {
        this.servicePwd = servicePwd;
    }

}
