package com.example.hexagonal.application.service.employment;

import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;
import com.example.hexagonal.application.port.in.employment.ModifyEmploymentUseCase;
import com.example.hexagonal.application.port.out.employment.ModifyEmploymentPort;
import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.domain.employment.EmploymentFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
class ModifyEmploymentService implements ModifyEmploymentUseCase {
    private final ModifyEmploymentPort modifyEmploymentPort;
    @Value("${file.uploadDir}")
    private String filePath;
    @Override
    public void modifyEmployment(Long employmentId, ModifyEmploymentCommand modifyEmploymentCommand) throws IOException {
        EmploymentFile employmentFile = modifyEmploymentFile(modifyEmploymentCommand.getEmploymentFile());
        Employment employment = Employment.withoutId(modifyEmploymentCommand.getPosition(),
                modifyEmploymentCommand.getOccupation(),
                modifyEmploymentCommand.getAssignedTask(),
                modifyEmploymentCommand.getQualification(),
                modifyEmploymentCommand.getIsVisible(),
                modifyEmploymentCommand.getRecruitStartDatetime(),
                modifyEmploymentCommand.getRecruitEndDatetime(),
                employmentFile);
        modifyEmploymentPort.modifyEmployment(employmentId, employment);
    }

    @Override
    public EmploymentFile modifyEmploymentFile(MultipartFile file) throws IOException {
        if (ObjectUtils.isEmpty(file)) return null;

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
