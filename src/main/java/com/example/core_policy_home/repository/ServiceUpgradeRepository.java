package com.example.core_policy_home.repository;

import com.example.core_policy_home.model.ServiceUpgrade;
import com.example.core_policy_home.repository.custom.ServiceUpgradeRepositoryCustom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUpgradeRepository extends JpaRepository<ServiceUpgrade, Long>,
    ServiceUpgradeRepositoryCustom {
  @Query("SELECT s FROM ServiceUpgrade s WHERE s.status = 1 AND s.serviceCode = ?1")
  List<ServiceUpgrade> findByServiceCode(String serviceCode);
  @Query(value = "select * from services s where s.code =:code", nativeQuery = true)
  List<ServiceUpgrade> findAllByServiceCode( String code);
  /*
  @author vinhtq
   */
  @Query(value = "select s.service_code from services s where s.id =:id", nativeQuery = true)
  String getServiceCodeById(@Param("id") Long id);
  @Query(value = "select count(*) from services s where s.service_code =:serviceCode", nativeQuery = true)
  Long countServicesAllByServiceCode(String serviceCode);

  @Query(value = "select sv.code from services sv where (:code is null or sv.code like lower(CONCAT('%',:code,'%')))",nativeQuery = true)
  List<String> findAllCode(@Param("code") String code, Pageable pageable);

  @Query(value = "select distinct(sv.name) from services sv where (:name is null or sv.name like lower(CONCAT('%',:name,'%')))",nativeQuery = true)
  List<String> findAllServiceName(String name, Pageable pageable);

  @Query(value = "select distinct(sv.code) from services sv where (:code is null or sv.code like lower( CONCAT('%',:code,'%')))",nativeQuery = true)
  List<String> findAllServiceCode(String code, Pageable pageable);
  @Query(value = "select count(sv.id) from ServiceUpgrade sv where (:id is not null and sv.id <>:id) and sv.name = lower(:name)")
  Long countServiceUpgradeAllByName(String name, Long id);
  boolean existsById(Long id);
  boolean existsByServiceCode(String serviceCode);
}
