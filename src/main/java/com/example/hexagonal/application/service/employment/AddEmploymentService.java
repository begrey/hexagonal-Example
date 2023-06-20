package com.example.hexagonal.application.service.employment;

import com.example.hexagonal.application.port.in.employment.AddEmploymentCommand;
import com.example.hexagonal.application.port.in.employment.AddEmploymentUseCase;
import com.example.hexagonal.application.port.out.employment.AddEmploymentPort;
import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.domain.employment.EmploymentFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
class AddEmploymentService implements AddEmploymentUseCase {

    private final AddEmploymentPort addEmploymentPort;
    @Value("${file.uploadDir}")
    private String filePath;
    @Override
    public void addEmployment(AddEmploymentCommand addEmploymentCommand) throws IOException {

        EmploymentFile employmentFile = saveEmploymentFile(addEmploymentCommand.getEmploymentFile());
        Employment employment = Employment.withoutId(addEmploymentCommand.getPosition(),
                addEmploymentCommand.getOccupation(),
                addEmploymentCommand.getAssignedTask(),
                addEmploymentCommand.getQualification(),
                addEmploymentCommand.getIsVisible(),
                addEmploymentCommand.getRecruitStartDatetime(),
                addEmploymentCommand.getRecruitEndDatetime(),
                employmentFile);
        addEmploymentPort.addEmployment(employment);
    }

    @Override
    public EmploymentFile saveEmploymentFile(MultipartFile file) throws IOException {
        String fileUploadPath = uploadFile(file);
        EmploymentFile employmentFile = EmploymentFile.withoutId(
                fileUploadPath,
                file.getOriginalFilename(),
                UUID.randomUUID().toString(),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                file.getSize());
        return employmentFile;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        final Path fileUploadPath = Paths.get(filePath).toAbsolutePath().normalize().resolve(fileName);
        Files.copy(file.getInputStream(), fileUploadPath, StandardCopyOption.REPLACE_EXISTING);
        return fileUploadPath.toString();
    }
}
