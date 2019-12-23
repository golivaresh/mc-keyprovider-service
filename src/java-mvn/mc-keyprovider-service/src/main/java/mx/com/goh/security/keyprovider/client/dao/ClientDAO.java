package mx.com.goh.security.keyprovider.client.dao;

import java.util.Optional;
import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.security.keyprovider.client.model.ClientEntity;
import mx.com.goh.security.keyprovider.repository.CrudRepository;

/**
 * DAO class for the operations of {@link ClientEntity} class.<br>
 * This class extends the base methods of the {@link CrudRepository} class. <br>
 * Version control:<br>
 * - 1.0.0 | 10/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
public interface ClientDAO extends CrudRepository<ClientEntity> {

    /**
     * Get the client searched by issuer property.
     * 
     * @param issuer
     *            Issuer name from client.
     * @return {@link Optional} with {@link ClientEntity} object.
     * @throws BusinessException
     *             Error occurred when searching for the client.
     */
    Optional<ClientEntity> findByIssuer(final String issuer) throws BusinessException;
}
