package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.ModifyBuildCaseCommand;
import com.example.hexagonal.application.port.in.buildcase.ModifyBuildCaseUseCase;
import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ModifyBuildCaseService implements ModifyBuildCaseUseCase {
    @Override
    public void modifyBuildCase(Long buildCaseId, ModifyBuildCaseCommand modifyBuildCaseCommand) {

    }
}
