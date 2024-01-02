package com.ss.trainingspringboot.service.impl;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.model.response.HdrResponse;
import com.ss.trainingspringboot.repository.DtlRepo;
import com.ss.trainingspringboot.repository.HdrRepo;
import com.ss.trainingspringboot.repository.custom.HdrRepoCustom;
import com.ss.trainingspringboot.service.HdrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HdrServiceImpl implements HdrService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HdrRepo hdrRepo;
    @Autowired
    private DtlRepo dtlRepo;
    @Autowired
    private HdrRepoCustom hdrRepoCustom;
    @Autowired
    private HdrMapperImpl hdrMapperImpl;

    @Override
    @Transactional
    public List<HdrResponse> findByFirstNameLike(String keyword) {
        LOGGER.info("[START][findByFirstNameLike]");
        List<HdrResponse> hdrResponses = new ArrayList<>();
        try {
            List<PostTestGasHdr> postTestGasHdr = hdrRepo.findByFirstNameLike(keyword);
            HdrResponse hdrResponse;
            for(PostTestGasHdr p_postTestGasHdr : postTestGasHdr){
                hdrResponse = hdrMapperImpl.convertToResponse(p_postTestGasHdr);
                hdrResponses.add(hdrResponse);
            }
            LOGGER.info("[END][findByFirstNameLike]");
            return hdrResponses;
        } catch(Exception e) {
            LOGGER.info("[ERROR][findByFirstNameLike] {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<HdrResponse> findAllHdr() {
        LOGGER.info("[START][findAllHdr]");
        List<HdrResponse> hdrResponses = new ArrayList<>();
        try {
            List<PostTestGasHdr> postTestGasHdrs = hdrRepo.findAll();
            HdrResponse hdrResponse;
            for (PostTestGasHdr p_postTestGasHdr : postTestGasHdrs) {
                hdrResponse = hdrMapperImpl.convertToResponse(p_postTestGasHdr);
                hdrResponses.add(hdrResponse);
            }
            LOGGER.info("[END][findAllHdr]");
            return hdrResponses;
        } catch (Exception e) {
            LOGGER.error("[ERROR][findAllHdr] : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public HdrResponse save(HdrRequest hdrRequest) {
        LOGGER.info("[START][save]");
        try {
            PostTestGasHdr postTestGasHdr = hdrMapperImpl.sourceToEntity(hdrRequest);
            postTestGasHdr = hdrRepo.save(postTestGasHdr);
            HdrResponse hdrResponse = hdrMapperImpl.convertToResponse(postTestGasHdr);

            LOGGER.info("[END][save]");
            return hdrResponse;
        } catch (Exception e) {
            LOGGER.info("[ERROR][save] = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        LOGGER.info("[START][deleteById]");
        try {
            Optional<PostTestGasHdr> postTestGasHdrOptional = hdrRepo.findById(id);
            if(postTestGasHdrOptional.isPresent()){
                LOGGER.info("[IF][postTestGasHdrOptional.isPresent()]");

                LOGGER.info("[FINDING DTLS]");
                List<PostTestGasDtl> postTestGasDtls = postTestGasHdrOptional.get().getPostTestGasDtl();

                LOGGER.info("[DELETE ALL DTLS]");
                dtlRepo.deleteAll(postTestGasDtls);

                LOGGER.info("[FLUSHING DELETE]");
                dtlRepo.flush();
                LOGGER.info("[FLUSHING COMPLETED");

                LOGGER.info("[DELETE HDR]");
                hdrRepo.delete(postTestGasHdrOptional.get());
            }
            LOGGER.info("[END][deleteById]");
        } catch (Exception e) {
            LOGGER.info("[ERROR][deleteById] = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
