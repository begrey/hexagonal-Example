package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.LoadBuildCaseUseCase;
import com.example.hexagonal.application.port.out.buildCase.LoadBuildCasePort;
import com.example.hexagonal.domain.buildcase.BuildCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class GetBuildCaseService implements LoadBuildCaseUseCase {

    private final LoadBuildCasePort loadBuildCasePort;
    @Override
    public Page<BuildCase> getBuildCases(Pageable pageable) {
        return loadBuildCasePort.loadBuildCases(pageable);
    }

    @Override
    public BuildCase getBuildCase(Long buildCaseId) {
        return loadBuildCasePort.loadBuildCase(buildCaseId);
    }
}
