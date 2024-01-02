package com.ss.trainingspringboot.controller;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.model.request.DtlRequest;
import com.ss.trainingspringboot.model.request.HdrRequest;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.model.response.HdrResponse;
import com.ss.trainingspringboot.repository.custom.DtlRepoCustom;
import com.ss.trainingspringboot.service.DtlService;
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
@RequestMapping("/api/dtl")
public class DtlController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DtlService dtlService;

    @GetMapping("/findAll")
    public List<DtlResponse> findAll(){
        LOGGER.info("[START][findAll]");
        try {
            LOGGER.info("[END][findAll]");
            return dtlService.findAllDtl();
        } catch (Exception e) {
            LOGGER.error("[ERROR][findAll] : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/save")
    public DtlResponse save(@RequestBody DtlRequest dtlRequest){
        LOGGER.info("[START][save]");
        try {
            return dtlService.save(dtlRequest);
        } catch (Exception e) {
            LOGGER.info("[ERROR][save]");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/deleteDtl")
    public ResponseEntity<String> deleteDtlById(@RequestParam(name = "id")Long id) {
        LOGGER.info("[START][deleteId]");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ResponseCode", "0");
        try {
            dtlService.deleteDtlById(id);
            LOGGER.info("[END][deleteId]");
            return new ResponseEntity<String>(new JSONSerializer().deepSerialize("success"), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("[ERROR][deleteId]");
            Map<String, Object> result = new HashMap<>();
            result.put("ResponseCode", "-1");
            result.put("errMsg", e.getMessage());
            return new ResponseEntity<String>(new JSONSerializer().deepSerialize(result), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        LOGGER.info("[START][deleteAll]");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ResponseCode", "0");
        try {
            dtlService.deleteAll();
            LOGGER.info("[END][deleteAll]");
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
