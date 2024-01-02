package com.ss.trainingspringboot.service.impl;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.request.DtlRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.repository.DtlRepo;
import com.ss.trainingspringboot.repository.HdrRepo;
import com.ss.trainingspringboot.service.Mapper.DtlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class DtlMapperImpl implements DtlMapper {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DtlRepo dtlRepo;

    @Autowired
    private  HdrRepo hdrRepo;

    @Override
    public PostTestGasDtl sourceToEntity(DtlRequest dtlRequest) {
        PostTestGasDtl postTestGasDtl = new PostTestGasDtl();

        if (dtlRequest.getId() != null) {
            Optional<PostTestGasDtl> postTestGasDtlOptional = dtlRepo.findById(dtlRequest.getId());
            if(postTestGasDtlOptional.isPresent()) {
                postTestGasDtl = postTestGasDtlOptional.get();
            }
        } else {
            postTestGasDtl.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        }
        Optional<PostTestGasHdr> postTestGasHdrOptional = hdrRepo.findById(dtlRequest.getHdrId());
        if(postTestGasHdrOptional.isPresent()){
            postTestGasDtl.setPostTestGasHdr(postTestGasHdrOptional.get());
        } else return null;

        postTestGasDtl.setLastUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        postTestGasDtl.setFaculty(dtlRequest.getFaculty());
        postTestGasDtl.setMajor(dtlRequest.getMajor());
        postTestGasDtl.setUniversity(dtlRequest.getUniversity());
        postTestGasDtl.setVersion(dtlRequest.getVersion());

        return postTestGasDtl;
    }

    @Override
    public DtlResponse convertToResponse(PostTestGasDtl postTestGasDtl) {
        DtlResponse dtlResponse = new DtlResponse();
        try {
            dtlResponse.setId(postTestGasDtl.getId());
            dtlResponse.setCreatedBy(postTestGasDtl.getCreatedBy());
            dtlResponse.setCreatedDate(postTestGasDtl.getCreatedDate());
            dtlResponse.setLastUpdatePgm(postTestGasDtl.getLastUpdatePgm());
            dtlResponse.setLastUpdatedBy(postTestGasDtl.getLastUpdatedBy());
            dtlResponse.setLastUpdatedDate(postTestGasDtl.getLastUpdatedDate());
            dtlResponse.setVersion(postTestGasDtl.getVersion());
            dtlResponse.setMajor(postTestGasDtl.getMajor());
            dtlResponse.setUniversity(postTestGasDtl.getUniversity());
            dtlResponse.setHdrId(postTestGasDtl.getPostTestGasHdr().getId());
            return dtlResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
