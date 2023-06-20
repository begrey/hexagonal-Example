package com.example.hexagonal.application.port.out.employment;

import com.example.hexagonal.domain.employment.Employment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LoadEmploymentPort {
    Page<Employment> loadEmployments(Pageable pageable);

    Employment loadEmployment(Long employmentId);
}
