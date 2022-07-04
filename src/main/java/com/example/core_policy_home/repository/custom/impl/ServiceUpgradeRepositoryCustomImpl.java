package com.example.core_policy_home.repository.custom.impl;

import com.example.core_policy_home.domain.dto.ParamDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchRequestDTO;
import com.example.core_policy_home.domain.dto.service.ServiceUpgradeSearchResponseDTO;
import com.example.core_policy_home.repository.custom.ServiceUpgradeRepositoryCustom;
import com.example.core_policy_home.util.DataUtils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author vinhtq
 * search process v1
 */
@Repository
@Data
@RequiredArgsConstructor
public class ServiceUpgradeRepositoryCustomImpl implements ServiceUpgradeRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    @Override
    public Page<ServiceUpgradeSearchResponseDTO> search(ServiceUpgradeSearchRequestDTO searchDTO, Pageable pageable) {
        long count = countFilterService(searchDTO);
        if (count > 0) {
            List<ServiceUpgradeSearchResponseDTO> serviceUpgradeSearchResponseDTOS = queryFilterService(searchDTO, pageable);
            return new PageImpl<>(serviceUpgradeSearchResponseDTOS, pageable, count);
        }
        return new PageImpl<>(new ArrayList<>(), pageable, count);
    }

    private List<ServiceUpgradeSearchResponseDTO> queryFilterService(ServiceUpgradeSearchRequestDTO searchDTO,Pageable pageable) {
        List<ParamDTO> paramDTOS = new ArrayList<>();
        StringBuilder queryString = new StringBuilder();
        queryString.append(" select "
                + "       s.id,"
                + "       s.name as serviceName,"
                + "       s.code as serviceCode,"
                + "       group_concat(p.code separator ',') as processCode,"
                + "       s.status,"
                + "       s.created_at,"
                + "       s.updated_at");
        queryString.append(" from service_upgrade s LEFT JOIN mapping_process_service mps  ON s.id = mps.service_id LEFT join process p on p.id = mps.process_id ");
        queryString.append("  where s.is_deleted = 0 and 1=1 ");
        whereFilterService(searchDTO, queryString,paramDTOS );
//        queryString.append(" group by s.id");
        if(pageable.getSort().stream().count() > 0) {
            queryString.append(" order by ");
            String sorted = pageable.getSort().stream().map(sort -> sort.getProperty()+" " + sort.getDirection().name()).collect(Collectors.joining(","));
            queryString.append(sorted);

        } else {
            queryString.append(" order by s.updated_at desc ");
        }

        Query selectQuery = em.createNativeQuery(queryString.toString());

        if(paramDTOS.size() > 0) {
            for(ParamDTO paramDTO : paramDTOS) {
                selectQuery.setParameter(paramDTO.getKey(), paramDTO.getValue());
            }
        }

        if(!pageable.isUnpaged()){
            int size = pageable.getPageSize();
            int page = pageable.getPageNumber();
            selectQuery.setFirstResult(page * size);
            selectQuery.setMaxResults(size);
        }
        List<Object[]> rows = selectQuery.getResultList();
        List<ServiceUpgradeSearchResponseDTO> data = new ArrayList<>();
        int i;
        for (Object[] row : rows) {
            i = 0;
            ServiceUpgradeSearchResponseDTO sevice = new ServiceUpgradeSearchResponseDTO();
            sevice.setId(DataUtils.parseToLong(row[i++]));
            sevice.setName(DataUtils.parseToString(row[i++]));
            sevice.setServiceCode(DataUtils.parseToString(row[i++]));
            sevice.setProcessCode(DataUtils.safeToString(row[i++]));
            sevice.setStatus(DataUtils.parseToInteger(row[i++]));
            sevice.setCreatedAt(DataUtils.parseToLocalDateTime(row[i++]));
            sevice.setUpdatedAt(DataUtils.parseToLocalDateTime(row[i]));
            data.add(sevice);
        }
        return data;
    }


    private long countFilterService(ServiceUpgradeSearchRequestDTO searchDTO) {
        List<ParamDTO> paramDTOS = new ArrayList<>();
        StringBuilder queryString = new StringBuilder();
        queryString.append("select count(*) ");
        queryString.append(" from (select s.id from service_upgrade s LEFT JOIN mapping_process_service mps  ON s.id = mps.service_id LEFT join process p on p.id = mps.process_id");
        queryString.append("  where s.is_deleted=0  ");
        whereFilterService(searchDTO, queryString, paramDTOS);
        queryString.append(" ) a");
        Query countQuery = em.createNativeQuery(queryString.toString());

        if(paramDTOS.size() > 0) {
            for(ParamDTO paramDTO : paramDTOS) {
                countQuery.setParameter(paramDTO.getKey(), paramDTO.getValue());
            }
        }
        return ((BigInteger)countQuery.getSingleResult()).longValue();
    }

    private void whereFilterService(ServiceUpgradeSearchRequestDTO searchDTO, StringBuilder queryString, List<ParamDTO> paramDTOS) {
        if (!DataUtils.isNullOrEmptyStr(searchDTO.getName())) {
            queryString.append("  AND lower(s.name) LIKE concat('%', :name, '%') ");
            paramDTOS.add(ParamDTO.builder().key("name").value(searchDTO.getName().trim().toLowerCase()).build());
        }
        if (!DataUtils.isNullOrEmptyStr(searchDTO.getServiceCode())) {
            queryString.append("  AND lower(s.code) LIKE concat('%', :code, '%') ");
            paramDTOS.add(ParamDTO.builder().key("code").value(searchDTO.getServiceCode().trim().toLowerCase()).build());
        }
        List<String> list = searchDTO.getProcessCode();
        System.out.println(list);
        if (!DataUtils.isNull(list)&&list.size()>0) {
            queryString.append("  AND p.code in :list  ");
            paramDTOS.add(ParamDTO.builder().key("list").value(list).build());
        }

        if (!DataUtils.isNull(searchDTO.getStatus())&&DataUtils.isOUTSTATUS(searchDTO.getStatus())) {
            queryString.append("  AND s.status =:status ");
            paramDTOS.add(ParamDTO.builder().key("status").value(searchDTO.getStatus()).build());
        }
        queryString.append(" group by s.id");
    }
}
