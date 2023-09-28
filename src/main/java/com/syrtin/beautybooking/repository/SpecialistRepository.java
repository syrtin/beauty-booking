package com.syrtin.beautybooking.repository;

import com.syrtin.beautybooking.model.Specialist;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends CrudRepository<Specialist, Long> {

    @Query("SELECT s.* FROM specialist s JOIN procedure_specialist sp ON s.id = sp.specialist_id WHERE sp.procedure_id = :procedureId")
    List<Specialist> findAllByProcedure(@Param("procedureId") Long procedureId);

}
