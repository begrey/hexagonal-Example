package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.AddBuildCaseCommand;
import com.example.hexagonal.application.port.in.buildcase.AddBuildCaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AddBuildCaseService implements AddBuildCaseUseCase {
    @Override
    public void addBuildCase(AddBuildCaseCommand addBuildCaseCommand) {

    }
}
