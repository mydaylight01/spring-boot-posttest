package com.ss.trainingspringboot.service.Mapper;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.model.request.DtlRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import org.springframework.stereotype.Service;

@Service
public interface DtlMapper {
    PostTestGasDtl sourceToEntity(DtlRequest dtlRequest);
    DtlResponse convertToResponse(PostTestGasDtl postTestGasDtl);
}
