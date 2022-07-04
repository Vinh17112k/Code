package com.example.core_policy_home.repository;

import com.example.core_policy_home.model.MappingProcessService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MappingProcessServiceRepository extends JpaRepository<MappingProcessService,Long> {
    @Query(value = "delete from mapping_process_service where service_id =:serviceId",nativeQuery = true)
    void deleteAllByServiceId(@Param("serviceId") Long serviceId);
    @Query(value = "delete from mapping_process_service where process_id =:processId",nativeQuery = true)
    void deleteAllByProcessId(@Param("processId") Long processId);
    @Query(value = "select * from mapping_process_service mps where mps.service_id =:serviceId",nativeQuery = true)
    List<Optional<MappingProcessService>> getMPSByServiceId(@Param("serviceId") Long serviceId);
    @Override
    <S extends MappingProcessService> List<S> saveAll(Iterable<S> iterable);
    boolean existsByServiceId(Long serviceId);
}
