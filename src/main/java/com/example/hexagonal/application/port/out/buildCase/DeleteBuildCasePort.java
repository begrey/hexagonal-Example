package com.example.hexagonal.application.port.out.buildCase;

import com.example.hexagonal.adapter.out.persistence.buildcase.BuildCaseJpaEntity;

public interface DeleteBuildCasePort {
    void deleteBuildCase(Long buildCaseId);

    void deleteBuildCaseFile(Long buildCaseId);
    void deleteBuildCaseTable(Long buildCaseId);


}
