package com.ss.trainingspringboot.domain;

import com.ss.trainingspringboot.domain.baseentity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "GAS_POSTTEST_DTL")
public class PostTestGasDtl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_post_dtl_id")
    @SequenceGenerator(name = "g_post_dtl_id", sequenceName = "G_POST_DTL_ID", allocationSize = 1)
    private Long id;
    private String faculty;
    private String major;
    private String university;

    @ManyToOne
    @JoinColumn(name = "HDR")
    private PostTestGasHdr postTestGasHdr;
}