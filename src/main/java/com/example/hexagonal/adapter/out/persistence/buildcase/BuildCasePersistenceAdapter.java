package com.example.hexagonal.adapter.out.persistence.buildcase;

import com.example.hexagonal.application.port.out.buildCase.AddBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.DeleteBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.LoadBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.ModifyBuildCasePort;
import com.example.hexagonal.domain.buildcase.BuildCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class BuildCasePersistenceAdapter implements LoadBuildCasePort, ModifyBuildCasePort, DeleteBuildCasePort, AddBuildCasePort {
    @Override
    public void addBuildCase(BuildCase buildCase) {

    }

    @Override
    public void deleteBuildCase(Long buildCaseId) {

    }

    @Override
    public Page<BuildCase> loadBuildCases(Pageable pageable) {
        return null;
    }

    @Override
    public BuildCase loadBuildCase(Long buildCaseId) {
        return null;
    }
}
