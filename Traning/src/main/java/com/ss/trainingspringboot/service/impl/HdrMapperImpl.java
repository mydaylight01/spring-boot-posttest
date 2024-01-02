package com.ss.trainingspringboot.service.impl;

import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.HdrResponse;
import com.ss.trainingspringboot.repository.HdrRepo;
import com.ss.trainingspringboot.service.Mapper.HdrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class HdrMapperImpl implements HdrMapper {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HdrRepo hdrRepo;

    @Override
    public PostTestGasHdr sourceToEntity(HdrRequest hdrRequest) {
        PostTestGasHdr postTestGasHdr = new PostTestGasHdr();

        if (hdrRequest.getId() != null) {
            Optional<PostTestGasHdr> postTestGasHdrOptional = hdrRepo.findById(hdrRequest.getId());
            if(postTestGasHdrOptional.isPresent()) {
                postTestGasHdr = postTestGasHdrOptional.get();
            }
        }
        else {
            postTestGasHdr.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        }
        postTestGasHdr.setLastUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        postTestGasHdr.setFirstName(hdrRequest.getFirstName());
        postTestGasHdr.setLastName(hdrRequest.getLastName());
        postTestGasHdr.setNickName(hdrRequest.getNickName());
        postTestGasHdr.setStatus(hdrRequest.getStatus());
        postTestGasHdr.setTeam(hdrRequest.getTeam());
        postTestGasHdr.setVersion(1);

        return postTestGasHdr;
    }

    @Override
    public HdrResponse convertToResponse(PostTestGasHdr postTestGasHdr) {
        HdrResponse hdrResponse = new HdrResponse();
        try {
            hdrResponse.setId(postTestGasHdr.getId());
            hdrResponse.setCreatedBy(postTestGasHdr.getCreatedBy());
            hdrResponse.setCreatedDate(postTestGasHdr.getCreatedDate());
            hdrResponse.setLastUpdatePgm(postTestGasHdr.getLastUpdatePgm());
            hdrResponse.setLastUpdatedBy(postTestGasHdr.getLastUpdatedBy());
            hdrResponse.setLastUpdatedDate(postTestGasHdr.getLastUpdatedDate());
            hdrResponse.setVersion(postTestGasHdr.getVersion());
            hdrResponse.setFirstName(postTestGasHdr.getFirstName());
            hdrResponse.setLastName(postTestGasHdr.getLastName());
            hdrResponse.setStatus(postTestGasHdr.getStatus());
            hdrResponse.setTeam(postTestGasHdr.getTeam());

            return hdrResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
