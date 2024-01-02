package com.ss.trainingspringboot.service.impl;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.model.request.DtlRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.repository.DtlRepo;
import com.ss.trainingspringboot.repository.custom.DtlRepoCustom;
import com.ss.trainingspringboot.service.DtlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DtlServiceImpl implements DtlService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DtlRepo dtlRepo;

    @Autowired
    private DtlRepoCustom dtlRepoCustom;

    @Autowired
    private DtlMapperImpl dtlMapperImpl;

    @Override
    @Transactional
    public List<DtlResponse> findAllDtl() {
        LOGGER.info("[START][findAllDtl]");
        List<DtlResponse> dtlResponses = new ArrayList<>();
        try {
            List<PostTestGasDtl> postTestGasDtls = dtlRepo.findAll();
            DtlResponse dtlResponse;
            for (PostTestGasDtl p_postTestGasDtl : postTestGasDtls) {
                dtlResponse = dtlMapperImpl.convertToResponse(p_postTestGasDtl);
                dtlResponses.add(dtlResponse);
            }
            LOGGER.info("[END][findAllDtl]");
            return dtlResponses;
        } catch (Exception e) {
            LOGGER.error("[ERROR][findAllDtl] : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public DtlResponse save(DtlRequest dtlRequest) {
        LOGGER.info("[START][save]");
        try {
            PostTestGasDtl postTestGasDtl = dtlMapperImpl.sourceToEntity(dtlRequest);
            postTestGasDtl = dtlRepo.save(postTestGasDtl);
            DtlResponse dtlResponse = dtlMapperImpl.convertToResponse(postTestGasDtl);

            LOGGER.info("[END][save]");
            return dtlResponse;
        } catch (Exception e) {
            LOGGER.info("[ERROR][save] = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteDtlById(Long id) {
        LOGGER.info(("[START][deleteDtl]"));
        try {
            Optional<PostTestGasDtl> postTestGasDtlOptional = dtlRepo.findById(id);
            if(postTestGasDtlOptional.isPresent()){
                LOGGER.info(("[IF][postTestGasDtlOptional.isPresent()]"));
                dtlRepo.delete(postTestGasDtlOptional.get());
            }
            LOGGER.info(("[END][deleteDtl]"));
        } catch (Exception e) {
            LOGGER.info("[ERROR][deleteDtl] = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        LOGGER.info(("[START][deleteAll]"));
        try {
            List<PostTestGasDtl> postTestGasDtls = dtlRepo.findAll();
            dtlRepo.deleteAll(postTestGasDtls);
            LOGGER.info(("[END][deleteAll]"));
        } catch (Exception e) {
            LOGGER.info("[ERROR][deleteAll] = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
