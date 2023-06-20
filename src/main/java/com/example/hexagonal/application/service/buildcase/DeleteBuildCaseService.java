package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.DeleteBuildCaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DeleteBuildCaseService implements DeleteBuildCaseUseCase {
    @Override
    public void deleteBuildCase(Long buildCaseId) {

    }
}
