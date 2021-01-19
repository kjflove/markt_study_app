package markt_study;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author jean
 * Abstract class for service of JpaRepository
 * @param <T>
 * @param <Long>
 */
public abstract class AbstractService<T extends AbstractModel<Long>, Long extends Serializable> {

    private static final int PAGE_SIZE = 5;
    protected abstract JpaRepository<T, Long> getRepository();

    public Page<T> getList(Integer pageNumber) {
        PageRequest pageRequest =
                 PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");

        return getRepository().findAll(pageRequest);
    }
    
    public List<T> getAll() {
       
        return getRepository().findAll();
       
    }


    public T save(T entity) {
        return getRepository().save(entity);
    }

    public T get(Long id) {
    	Optional<T> optionalEntity =  getRepository().findById(id);
    	T entity = optionalEntity.get();
    	 
       
        
        return entity;
    }

    public void delete(Long id) {
        try {
            getRepository().deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }

    public void update(T entity) {
        T getEntity = getRepository().findById(entity.getId()).get();
        getRepository().save(entity);
    }
    
   
}