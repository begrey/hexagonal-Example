package com.example.hexagonal.application.service.employment;

import com.example.hexagonal.application.port.in.employment.DeleteEmploymentUseCase;
import com.example.hexagonal.application.port.out.employment.DeleteEmploymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class DeleteEmploymentService implements DeleteEmploymentUseCase {
    private final DeleteEmploymentPort deleteEmploymentPort;
    @Override
    public void deleteEmployment(Long employmentId) {
        deleteEmploymentPort.deleteEmployment(employmentId);
    }
}
