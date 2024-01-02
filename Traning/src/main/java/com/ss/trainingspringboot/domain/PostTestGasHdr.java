package com.ss.trainingspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ss.trainingspringboot.domain.baseentity.BaseEntity;
import com.ss.trainingspringboot.model.master.StatusMaster;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "GAS_POSTTEST_HDR")
public class PostTestGasHdr extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_post_hdr_id")
    @SequenceGenerator(name = "g_post_hdr_id", sequenceName = "G_POST_HDR_ID", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String status;
    private String team;

    @OneToMany (cascade = {CascadeType.ALL}, mappedBy = "postTestGasHdr")
    private List<PostTestGasDtl> postTestGasDtl;
}
