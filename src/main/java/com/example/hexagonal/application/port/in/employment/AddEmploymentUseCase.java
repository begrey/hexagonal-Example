package com.example.hexagonal.application.port.in.employment;

import com.example.hexagonal.domain.employment.EmploymentFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AddEmploymentUseCase {
    void addEmployment(AddEmploymentCommand addEmploymentCommand) throws IOException;

    EmploymentFile saveEmploymentFile(MultipartFile multipartFile) throws IOException;

}
