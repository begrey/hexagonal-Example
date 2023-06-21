package com.example.hexagonal.application.port.out.buildCase;

import com.example.hexagonal.domain.buildcase.BuildCase;

public interface ModifyBuildCasePort {
    void modifyBuildCase(Long buildCaseId, BuildCase buildCase);
}
