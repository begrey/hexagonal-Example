package com.example.hexagonal.application.port.out.employment;

import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.domain.employment.EmploymentFile;

public interface AddEmploymentPort {
    void addEmployment(Employment employment);
}
