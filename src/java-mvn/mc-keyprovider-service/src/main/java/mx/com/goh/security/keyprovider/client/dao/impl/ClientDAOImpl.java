package mx.com.goh.security.keyprovider.client.dao.impl;

import java.util.Optional;
import mx.com.goh.gcommons.exception.BusinessException;
import mx.com.goh.security.keyprovider.client.dao.ClientDAO;
import mx.com.goh.security.keyprovider.client.model.ClientEntity;
import mx.com.goh.security.keyprovider.repository.CrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation class that implements {@link ClientDAO} for the operations
 * of the {@link ClientEntity}.<br>
 * Version control:<br>
 * - 1.0.0 | 10/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 */
@Component
@Repository
public class ClientDAOImpl extends CrudRepositoryImpl<ClientEntity> implements ClientDAO {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -4762302530127095199L;

    /**
     * Mongo Operations object.
     */
    private final MongoOperations mongoOperations;

    @Autowired
    public ClientDAOImpl(MongoOperations mongoOperations) {
        super(ClientEntity.class, mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.client.dao.ClientDAO#findByIssuer(java.lang.
     * String)
     */
    @Override
    public Optional<ClientEntity> findByIssuer(String issuer) throws BusinessException {
        info("Finding client by ussuer... ");
        ClientEntity client = mongoOperations.findOne(new Query(
                Criteria.where("issuer").is(issuer)),
                ClientEntity.class);
        info("Has the Customer information been obteined? " + (client != null));
        return Optional.ofNullable(client);
    }

}
