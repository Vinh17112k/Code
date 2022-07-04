package com.example.core_policy_home.repository;
/**
 * @author vinhtq
 * process repository
 */

import com.example.core_policy_home.model.Process;
import com.example.core_policy_home.repository.custom.ProcessRepositoryCustom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProcessRepository extends JpaRepository<Process,Long>, ProcessRepositoryCustom {
  @Query(value = "select count(sv.id) from Process sv where (:id is not null and sv.id <>:id) and sv.name = lower(:name)")
  Long countProcessAllByName(String name, Long id);
  Long countProcessAllByProcessCode(String processCode);
  boolean existsByProcessCode(String name);
  boolean existsById(Long id);
  boolean existsByName(String name);
  @Query(value = "select p.name from Process p where p.id =:processId")
  String getName(@Param("processId") Long processId);
  @Query(value = "select p.processCode from Process p where p.id =:processId")
  String getProcessCode(@Param("processId") Long processId);
  @Query("SELECT p FROM Process p WHERE p.status = 1 ORDER BY p.processCode")
  List<Process> findAllCustom();

  List<Process> findAllByProcessCodeAndIdIsNot(String processCode, long id);
  List<Process> findAllByNameAndIdIsNot(String name, long id);
  @Query(value = "select distinct(p.code) from process p where (:code is null or p.code like lower(CONCAT('%',:code,'%')))",nativeQuery = true)
  List<String> findAllProcessCode(String code, Pageable pageable);
  @Query(value = "select * from process p where (:code is null or p.code like lower(CONCAT('%',:code,'%')))",nativeQuery = true)
  List<Process> findAllProcessCodeAll(String code, Pageable pageable);
  @Query(value = "select distinct(p.name) from process p where (:name is null or p.name like lower(CONCAT('%',:name,'%')))",nativeQuery = true)
  List<String> findAllProcessName(String name, Pageable pageable);
  @Query(value = "select * from process p  inner JOIN ( mapping_process_service mps inner join service_upgrade s on s.id =mps.service_id) ON p.id = mps.process_id  where s.id =:serviceId",nativeQuery = true)
  List<Process> findAllProcessOfService(@Param("serviceId") Long serviceId );

}
