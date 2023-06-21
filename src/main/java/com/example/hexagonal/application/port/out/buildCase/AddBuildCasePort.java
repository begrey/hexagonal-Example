package com.example.hexagonal.application.port.out.buildCase;

import com.example.hexagonal.adapter.out.persistence.buildcase.BuildCaseJpaEntity;
import com.example.hexagonal.domain.buildcase.BuildCase;

public interface AddBuildCasePort {
    void addBuildCase(BuildCase buildCase);
}
