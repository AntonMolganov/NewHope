package newhope.server.dao;

import newhope.server.entity.ShipperEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperDao extends CrudRepository<ShipperEntity, Long> {
}
