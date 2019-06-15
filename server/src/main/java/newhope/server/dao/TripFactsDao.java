package newhope.server.dao;

import newhope.server.entity.TripFactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripFactsDao extends CrudRepository<TripFactEntity, Long>, TripFactsCustomDao {

    Iterable<TripFactEntity> findAllByOrderByDateDesc();

}
