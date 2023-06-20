package com.example.hexagonal.application.port.out.employment;

import com.example.hexagonal.domain.employment.Employment;

public interface ModifyEmploymentPort {
    void modifyEmployment(Long employmentId, Employment employment);
}
