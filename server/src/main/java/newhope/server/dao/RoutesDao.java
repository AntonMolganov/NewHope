package newhope.server.dao;

import newhope.server.entity.RouteEntity;
import newhope.server.entity.ShipperEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutesDao extends CrudRepository<RouteEntity, Long> {

    Iterable<RouteEntity> findAllByOrderByTransportTypeIdAscRouteNameAsc();

    Optional<RouteEntity> findByTransportTypeIdAndRouteName(Long transportTypeId, String routeName);
}
