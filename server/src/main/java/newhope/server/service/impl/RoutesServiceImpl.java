package newhope.server.service.impl;

import newhope.server.dao.RoutesDao;
import newhope.server.dao.ShipperDao;
import newhope.server.entity.RouteEntity;
import newhope.server.entity.ShipperEntity;
import newhope.server.service.RoutesService;
import newhope.server.service.ShippersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    RoutesDao dao;

    @Override
    public List<RouteEntity> list() {
        List<RouteEntity> resultList = new LinkedList<>();
        dao.findAllByOrderByTransportTypeIdAscRouteNameAsc().forEach(resultList::add);
        return resultList;
    }

}
