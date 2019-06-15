package newhope.server.service.impl;

import newhope.server.dao.ShipperDao;
import newhope.server.entity.ShipperEntity;
import newhope.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    ShipperDao shipperDao;

    @Override
    public List<ShipperEntity> test() {
        List<ShipperEntity> resultList = new LinkedList<>();
        shipperDao.findAll().forEach(resultList::add);
        return resultList;
    }
}
