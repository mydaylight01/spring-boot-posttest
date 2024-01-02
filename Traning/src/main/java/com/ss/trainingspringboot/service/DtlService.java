package com.ss.trainingspringboot.service;

import com.ss.trainingspringboot.model.request.DtlRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;

import java.util.List;

public interface DtlService {
    List<DtlResponse> findAllDtl();
    DtlResponse save(DtlRequest dtlRequest);
    void deleteDtlById(Long id);
    void deleteAll();
}
