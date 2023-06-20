package com.example.hexagonal.application.port.in.employment;

import com.example.hexagonal.domain.employment.EmploymentFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ModifyEmploymentUseCase {
    void modifyEmployment(Long employmentId, ModifyEmploymentCommand modifyEmploymentCommand) throws IOException;
    EmploymentFile modifyEmploymentFile(MultipartFile file) throws IOException;
}
