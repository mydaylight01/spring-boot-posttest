package com.ss.trainingspringboot.model.response;

import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.domain.baseentity.BaseEntity;
import lombok.Data;

@Data
public class DtlResponse extends BaseEntity {
    private Long id;
    private String faculty;
    private String major;
    private String university;
    private Long hdrId;
}
