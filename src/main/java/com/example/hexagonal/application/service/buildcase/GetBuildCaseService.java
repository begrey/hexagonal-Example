package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.LoadBuildCaseUseCase;
import com.example.hexagonal.domain.buildcase.BuildCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class GetBuildCaseService implements LoadBuildCaseUseCase {
    @Override
    public Page<BuildCase> getBuildCases(Pageable pageable) {
        return null;
    }

    @Override
    public BuildCase getBuildCase(Long buildCaseId) {
        return null;
    }
}
