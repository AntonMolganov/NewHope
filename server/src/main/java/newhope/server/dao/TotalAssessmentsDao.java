package newhope.server.dao;

import newhope.server.entity.TotalAssessmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalAssessmentsDao extends CrudRepository<TotalAssessmentEntity, Long> {

    Iterable<TotalAssessmentEntity> findAllByOrderByPositionAsc();
}
