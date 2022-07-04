package com.example.core_policy_home.repository.custom;

import com.example.core_policy_home.domain.dto.process.SearchProcessRequest;
import com.example.core_policy_home.domain.dto.process.SearchProcessResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProcessRepositoryCustom {
    Page<SearchProcessResponse> findCustom(SearchProcessRequest searchReq, Pageable pageable);
}
