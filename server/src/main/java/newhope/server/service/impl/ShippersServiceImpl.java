package newhope.server.service.impl;

import newhope.server.dao.ShipperDao;
import newhope.server.entity.ShipperEntity;
import newhope.server.service.ShippersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ShippersServiceImpl implements ShippersService {

    @Autowired
    ShipperDao dao;

    @Override
    public List<ShipperEntity> list() {
        List<ShipperEntity> resultList = new LinkedList<>();
        dao.findAllByOrderByIdDesc().forEach(resultList::add);
        return resultList;
    }
}
