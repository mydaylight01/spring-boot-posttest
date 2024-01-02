package com.ss.trainingspringboot.service.Mapper;

import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.HdrResponse;
import org.springframework.stereotype.Service;

@Service
public interface HdrMapper {

    PostTestGasHdr sourceToEntity(HdrRequest hdrRequest);
    HdrResponse convertToResponse(PostTestGasHdr postTestGasHdr);
}
