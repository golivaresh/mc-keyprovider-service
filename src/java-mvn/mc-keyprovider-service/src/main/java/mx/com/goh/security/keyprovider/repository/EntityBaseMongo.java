package mx.com.goh.security.keyprovider.repository;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Base entity class for the basic operations in mongo database.<br>
 * Control version:<br>
 * - 1.0.0 | 12/06/2019 | Gustavo Olivares Hernandez
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public class EntityBaseMongo implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5126742356223879777L;

    @NotNull
    private Long id;

    /**
     * Constructor of EntityBase class.
     */
    public EntityBaseMongo() {
        super();
    }

    /**
     * Constructor of EntityBase class.
     * 
     * @param id id for the entity.
     */
    public EntityBaseMongo(@NotNull Long id) {
        super();
        this.id = id;
    }

    /**
     * Get the value of id.
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
