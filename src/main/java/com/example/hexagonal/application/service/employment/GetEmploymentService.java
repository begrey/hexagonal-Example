package com.example.hexagonal.application.service.employment;

import com.example.hexagonal.application.port.in.employment.LoadEmploymentUseCase;
import com.example.hexagonal.application.port.out.employment.LoadEmploymentPort;
import com.example.hexagonal.domain.employment.Employment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class GetEmploymentService implements LoadEmploymentUseCase {

    private final LoadEmploymentPort loadEmploymentPort;

    @Override
    public Page<Employment> loadEmployments(Pageable pageable) {
        return loadEmploymentPort.loadEmployments(pageable);
    }

    @Override
    public Employment loadEmployment(Long employmentId) {
        return loadEmploymentPort.loadEmployment(employmentId);
    }
}
