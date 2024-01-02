package com.ss.trainingspringboot.service;

import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.HdrResponse;

import java.util.List;

public interface HdrService {
    List<HdrResponse> findByFirstNameLike(String keyword);
    List<HdrResponse> findAllHdr();
    HdrResponse save(HdrRequest hdrRequest);
    void deleteById(Long id);
}
