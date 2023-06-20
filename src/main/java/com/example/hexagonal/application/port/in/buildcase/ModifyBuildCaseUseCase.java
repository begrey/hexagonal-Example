package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;

public interface ModifyBuildCaseUseCase {
    void modifyBuildCase(Long buildCaseId, ModifyBuildCaseCommand modifyBuildCaseCommand);
}
