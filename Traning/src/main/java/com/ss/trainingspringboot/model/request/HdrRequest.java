package com.ss.trainingspringboot.model.request;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import com.ss.trainingspringboot.domain.baseentity.BaseEntity;
import com.ss.trainingspringboot.model.master.StatusMaster;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class HdrRequest extends BaseEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String status;
    private String team;
    private List<PostTestGasDtl> postTestGasDtl;
}