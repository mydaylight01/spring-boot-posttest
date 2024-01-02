package com.ss.trainingspringboot.repository;

import com.ss.trainingspringboot.domain.PostTestGasHdr;
import com.ss.trainingspringboot.model.response.DtlResponse;
import com.ss.trainingspringboot.model.response.HdrResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HdrRepo extends JpaRepository<PostTestGasHdr, Long> {
    List<PostTestGasHdr> findByFirstNameLike(String keyword);
}
