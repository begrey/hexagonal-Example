package com.example.hexagonal.application.port.out.buildCase;

import com.example.hexagonal.domain.buildcase.BuildCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadBuildCasePort {
    Page<BuildCase> loadBuildCases(Pageable pageable);

    BuildCase loadBuildCase(Long buildCaseId);
}
