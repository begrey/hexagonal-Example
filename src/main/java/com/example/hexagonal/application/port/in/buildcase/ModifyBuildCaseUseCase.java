package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;

import java.io.IOException;

public interface ModifyBuildCaseUseCase {
    void modifyBuildCase(Long buildCaseId, ModifyBuildCaseCommand modifyBuildCaseCommand) throws IOException;
}
