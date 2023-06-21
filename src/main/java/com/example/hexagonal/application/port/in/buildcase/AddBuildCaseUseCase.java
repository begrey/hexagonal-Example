package com.example.hexagonal.application.port.in.buildcase;

import java.io.IOException;

public interface AddBuildCaseUseCase {
    void addBuildCase(AddBuildCaseCommand addBuildCaseCommand) throws IOException;
}
