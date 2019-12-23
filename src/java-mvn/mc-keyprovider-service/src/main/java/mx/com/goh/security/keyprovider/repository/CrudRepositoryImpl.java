package mx.com.goh.security.keyprovider.repository;

import java.util.List;
import java.util.Optional;
import mx.com.goh.gcommons.architect.GCommonArchitect;
import mx.com.goh.gcommons.exception.BusinessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * CRUD class that implements {@link CrudRepository} for the basic CRUD in mongo
 * database. <br>
 * Version control:<br>
 * - 1.0.0 | 12/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 * @param <T>
 *            extends {@link EntityBaseMongo}.
 */
public class CrudRepositoryImpl<T extends EntityBaseMongo> extends GCommonArchitect 
      implements CrudRepository<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7390550338602546522L;

    /**
     * Instance of Mongo Operations.
     */
    private final MongoOperations mongoOperations;

    /**
     * Class object for the databases entity.
     */
    private final Class<T> clazz;

    /**
     * Constructor of CrudRepositoryImpl.
     * 
     * @param clazz
     *            Object for the class on which the database operations are
     *            performed.
     * @param mongoOperations
     *            object for the access to mongo database.
     */
    public CrudRepositoryImpl(final Class<T> clazz, final MongoOperations mongoOperations) {
        super(CrudRepository.class);
        if (mongoOperations == null) {
            throw new IllegalArgumentException("The parameter [mongoOperations] is required!");
        }
        this.mongoOperations = mongoOperations;
        this.clazz = clazz;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.com.goh.security.keyprovider.repository.CrudRepository#findById(long)
     */
    @Override
    public Optional<T> findById(long id) throws BusinessException {
        T t = this.mongoOperations.findOne(new Query(Criteria.where("id").is(id)), this.clazz);
        return Optional.ofNullable(t);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.com.goh.security.keyprovider.repository.CrudRepository#getAll()
     */
    @Override
    public Optional<List<T>> getAll() throws BusinessException {
        List<T> list = this.mongoOperations.findAll(this.clazz);
        return Optional.ofNullable(list);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.repository.CrudRepository#save(mx.com.goh.
     * security.keyprovider.repository.EntityBaseMongo)
     */
    @Override
    public Optional<T> save(T object) throws BusinessException {
        this.mongoOperations.save(object);
        return this.findById(object.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.com.goh.security.keyprovider.repository.CrudRepository#update(mx.com.goh.
     * security.keyprovider.repository.EntityBaseMongo)
     */
    @Override
    public boolean update(T object) throws BusinessException {
        return this.save(object).isPresent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.com.goh.security.keyprovider.repository.CrudRepository#delete(long)
     */
    @Override
    public boolean delete(long id) throws BusinessException {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("id").is(id)), this.clazz);
        return !(this.findById(id).isPresent());
    }
}
