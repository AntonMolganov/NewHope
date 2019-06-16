package newhope.server.service;

import newhope.server.entity.ShipperEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShippersService {

    List<ShipperEntity> list();
}
