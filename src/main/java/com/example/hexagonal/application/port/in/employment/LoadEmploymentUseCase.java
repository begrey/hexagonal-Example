package com.example.hexagonal.application.port.in.employment;

import com.example.hexagonal.domain.employment.Employment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadEmploymentUseCase {
    Page<Employment> loadEmployments(Pageable pageable);

    Employment loadEmployment(Long employmentId);
}
