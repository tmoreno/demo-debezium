package com.tmoreno.student.domain;

import com.tmoreno.student.infraestructure.OutBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBoxEntity, String> {

}
