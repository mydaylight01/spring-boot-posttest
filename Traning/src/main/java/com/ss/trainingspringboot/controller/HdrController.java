package com.ss.trainingspringboot.controller;

import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.model.response.HdrResponse;
import com.ss.trainingspringboot.service.HdrService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hdr")
public class HdrController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HdrService hdrService;

    @GetMapping("/findByFirstName")
    public List<HdrResponse> findByFirstName(@RequestParam(value = "keyword") String keyword) {
        LOGGER.info("[START][postTestGasHdrList]");
        try {
            LOGGER.info("[END][postTestGasHdrList]");
            return hdrService.findByFirstNameLike(keyword);
        } catch (Exception e) {
            LOGGER.error("[ERROR][postTestGasHdrList] : {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<HdrResponse> findAll(){
        LOGGER.info("[START][findAll]");
        try {
            LOGGER.info("[END][findAll]");
            return hdrService.findAllHdr();
        } catch (Exception e) {
            LOGGER.error("[ERROR][findAll] : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/save")
    public HdrResponse save(@RequestBody HdrRequest hdrRequest){
        LOGGER.info("[START][save]");
        try {
            LOGGER.info("[END][save]");
            return hdrService.save(hdrRequest);
        } catch (Exception e) {
            LOGGER.info("[ERROR][save]");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam(name = "id") Long id) {
        LOGGER.info("[START][deleteById]");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ResponseCode", "0");
        try {
            LOGGER.info("[END][deleteById]");
            hdrService.deleteById(id);
            return new ResponseEntity<String>(new JSONSerializer().deepSerialize("success"), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("[ERROR][deleteAll]");
            Map<String, Object> result = new HashMap<>();
            result.put("ResponseCode", "-1");
            result.put("errMsg", e.getMessage());
            return new ResponseEntity<String>(new JSONSerializer().deepSerialize(result), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
