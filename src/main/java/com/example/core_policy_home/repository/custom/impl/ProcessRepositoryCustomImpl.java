package com.example.core_policy_home.repository.custom.impl;

import com.example.core_policy_home.domain.dto.ParamDTO;
import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import com.example.core_policy_home.repository.custom.ProcessRepositoryCustom;
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
public class ProcessRepositoryCustomImpl implements ProcessRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    @Override
    public Page<SearchProcessResponse> findCustom(SearchProcessRequest searchDTO,Pageable pageable) {
        long count = countFilterUser(searchDTO);
        if (count > 0) {
            List<SearchProcessResponse> searchProcessResponses = queryFilterUser(searchDTO, pageable);
            return new PageImpl<>(searchProcessResponses, pageable, count);
        }
        return new PageImpl<>(new ArrayList<>(), pageable, count);
    }

    private List<SearchProcessResponse> queryFilterUser(SearchProcessRequest searchDTO,Pageable pageable) {
        List<ParamDTO> paramDTOS = new ArrayList<>();
        StringBuilder queryString = new StringBuilder();
        queryString.append(" select "
                + "       p.id,"
                + "       p.name as name,"
                + "       p.code as processCode,"
                + "       p.status,"
                + "       p.created_at,"
                + "       p.updated_at");
        queryString.append(" from process p ");
        queryString.append("  where p.is_deleted = 0 and 1=1  ");
        whereFilterUser(searchDTO, queryString,paramDTOS );

        if(pageable.getSort().stream().count() > 0) {
            queryString.append(" order by ");
            String sorted = pageable.getSort().stream().map(sort -> sort.getProperty()+" " + sort.getDirection().name()).collect(Collectors.joining(","));
            queryString.append(sorted);

        } else {
            queryString.append(" order by p.updated_at desc ");
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
        List<SearchProcessResponse> data = new ArrayList<>();
        int i;
        for (Object[] row : rows) {
            i = 0;
            SearchProcessResponse process = new SearchProcessResponse();
            process.setId(DataUtils.parseToLong(row[i++]));
            process.setName(DataUtils.parseToString(row[i++]));
            process.setProcessCode(DataUtils.parseToString(row[i++]));
            process.setStatus(DataUtils.parseToInteger(row[i++]));
            process.setCreatedAt(DataUtils.parseToLocalDateTime(row[i++]));
            process.setUpdatedAt(DataUtils.parseToLocalDateTime(row[i]));
            data.add(process);
        }
        return data;
    }


    private long countFilterUser(SearchProcessRequest searchDTO) {
        List<ParamDTO> paramDTOS = new ArrayList<>();
        StringBuilder queryString = new StringBuilder();
        queryString.append("select count(*) ");
        queryString.append(" from process p ");
        queryString.append("  where 1=1  ");
        whereFilterUser(searchDTO, queryString, paramDTOS);
        Query countQuery = em.createNativeQuery(queryString.toString());

        if(paramDTOS.size() > 0) {
            for(ParamDTO paramDTO : paramDTOS) {
                countQuery.setParameter(paramDTO.getKey(), paramDTO.getValue());
            }
        }
        return ((BigInteger)countQuery.getSingleResult()).longValue();
    }

    private void whereFilterUser(SearchProcessRequest searchDTO, StringBuilder queryString, List<ParamDTO> paramDTOS) {
        if (!DataUtils.isNullOrEmptyStr(searchDTO.getName())) {
            queryString.append("  AND lower(p.name) LIKE concat('%', :name, '%') ");
            paramDTOS.add(
                ParamDTO.builder().key("name").value(searchDTO.getName().trim().toLowerCase()).build());
        }
        if (!DataUtils.isNullOrEmptyStr(searchDTO.getProcessCode())) {
            queryString.append("  AND lower(p.code) LIKE concat('%', :code, '%')  ");
            paramDTOS.add(ParamDTO.builder().key("code").value(searchDTO.getProcessCode().trim().toLowerCase()).build());
        }

        if (!DataUtils.isNull(searchDTO.getStatus())) {
            queryString.append("  AND p.status =:status ");
            paramDTOS.add(ParamDTO.builder().key("status").value(searchDTO.getStatus()).build());
        }
    }
}
