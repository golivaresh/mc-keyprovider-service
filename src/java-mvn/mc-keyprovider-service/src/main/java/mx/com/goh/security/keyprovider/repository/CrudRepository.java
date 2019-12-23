package mx.com.goh.security.keyprovider.repository;

import java.util.List;
import java.util.Optional;
import mx.com.goh.gcommons.exception.BusinessException;

/**
 * Interface for the basic CRUD in mongo database.<br>
 * Version control:<br>
 * - 1.0.0 | 12/06/2019 | Gustavo Olivares Hernández
 * 
 * @author golivaresh
 * @version 1.0.0
 * @param <T>
 *            extends {@link EntityBaseMongo}.
 */
public interface CrudRepository<T extends EntityBaseMongo> {
    /**
     * Find entity by Id.
     * 
     * @param id
     *            to find the entity.
     * @return {@link Optional} T extends {@link EntityBaseMongo}.
     * @throws BusinessException
     *             Error occurred when searching for the entity.
     */
    Optional<T> findById(final long id) throws BusinessException;

    /**
     * Get a {@link List} of entities.
     * 
     * @return {@link Optional} List of entities type T.
     * @throws BusinessException
     *             Error occurred when getting the entities.
     */
    Optional<List<T>> getAll() throws BusinessException;

    /**
     * Save the object in the mongo db.
     * 
     * @param object
     *            to save.
     * @return {@link Optional} object saved.
     * @throws BusinessException
     *             Error occurred when saving the object.
     */
    Optional<T> save(T object) throws BusinessException;

    /**
     * Update the object in the mongo db.
     * 
     * @param object
     *            to update.
     * @return true if the object has been saved correctly, false otherwise.
     * @throws BusinessException
     *             Error occurred when updating the object.
     */
    boolean update(T object) throws BusinessException;

    /**
     * Delete from the database the entity that belongs to the id.
     * 
     * @param id
     *            id of the entity to be eliminate.
     * @return true if the object has been deleted correctly, false otherwise.
     * @throws BusinessException
     *             Error occurred when updating the object.
     */
    boolean delete(final long id) throws BusinessException;
}
