package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.domain.buildcase.BuildCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadBuildCaseUseCase {
    Page<BuildCase> getBuildCases(Pageable pageable);

    BuildCase getBuildCase(Long buildCaseId);
}
