package com.ss.trainingspringboot.repository;

import com.ss.trainingspringboot.domain.PostTestGasDtl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DtlRepo extends JpaRepository<PostTestGasDtl, Long> {
}
