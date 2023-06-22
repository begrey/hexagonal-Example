package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.DeleteBuildCaseUseCase;
import com.example.hexagonal.application.port.out.buildCase.DeleteBuildCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class DeleteBuildCaseService implements DeleteBuildCaseUseCase {

    private final DeleteBuildCasePort deleteBuildCasePort;
    @Override
    public void deleteBuildCase(Long buildCaseId) {
        deleteBuildCasePort.deleteBuildCaseFile(buildCaseId);
        deleteBuildCasePort.deleteBuildCaseTable(buildCaseId);
        deleteBuildCasePort.deleteBuildCase(buildCaseId);
    }
}
