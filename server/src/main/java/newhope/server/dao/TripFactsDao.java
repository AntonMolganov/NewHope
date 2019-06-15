package newhope.server.dao;

import newhope.server.entity.TripFactEntity;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TripFactsDao extends CrudRepository<TripFactEntity, Long> {

    Iterable<TripFactEntity> findAllByOrderByDateDesc();

    @Procedure(name = "P_CREATE")
    void addAssessment(@Param("i_client_id") Long clientId,
                    @Param("i_route_id") Long routeId,
                    @Param("i_shipper_id") Long shipperId,
                    @Param("i_d_trip_date") Date tripDate,
                    @Param("i_i_compliance_level") Integer complLvl,
                    @Param("i_s_compliance_comment") String complCmnt,
                    @Param("i_i_free_space_level") Integer spaceLvl,
                    @Param("i_s_free_space_comment") String spaceCmnt,
                    @Param("i_i_tech_state_level") Integer techLvl,
                    @Param("i_s_tech_state_comment") String techCmnt,
                    @Param("i_i_law_violence_level") Integer lawLvl,
                    @Param("i_s_law_violence_comment") String lawCmnt,
                    @Param("i_i_service_level") Integer serviceLvl,
                    @Param("i_s_service_comment") String serviceCmnt
                    );
}
