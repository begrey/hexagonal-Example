package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.application.port.in.buildcase.AddBuildCaseCommand;
import com.example.hexagonal.application.port.in.buildcase.AddBuildCaseUseCase;
import com.example.hexagonal.application.port.out.buildCase.AddBuildCasePort;
import com.example.hexagonal.domain.buildcase.BuildCase;
import com.example.hexagonal.domain.buildcase.BuildCaseFile;
import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import com.example.hexagonal.domain.employment.EmploymentFile;
import com.example.hexagonal.global.enums.FileType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
class AddBuildCaseService implements AddBuildCaseUseCase {

    private final AddBuildCasePort addBuildCasePort;
    @Value("${file.uploadDir}")
    private String filePath;
    @Override
    public void addBuildCase(AddBuildCaseCommand addBuildCaseCommand) throws IOException {
        BuildCase buildCase = BuildCase.withoutId(
                addBuildCaseCommand.getBuildCaseName(),
                addBuildCaseCommand.getIsVisible(),
                toBuildCaseFile(addBuildCaseCommand.getThumbnail(), FileType.THUMBNAIL),
                toBuildCaseDetailFile(addBuildCaseCommand.getBuildCaseFile()),
                toBuildCaseTable(addBuildCaseCommand.getBuildCaseTable()),
                addBuildCaseCommand.getCategory()
        );
        addBuildCasePort.addBuildCase(buildCase);
    }

    public BuildCaseFile toBuildCaseFile(MultipartFile file, FileType fileType) throws IOException {
        if (file == null) return null;
        String uploadPath = uploadFile(file);
        BuildCaseFile buildCaseFile = BuildCaseFile.withoutId(
                uploadPath,
                file.getOriginalFilename(),
                UUID.randomUUID().toString(),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                fileType,
                file.getSize());
        return buildCaseFile;
    }
    public List<BuildCaseTable> toBuildCaseTable(List<BuildCaseTable> buildCaseTableDtos) {
        if (ObjectUtils.isEmpty(buildCaseTableDtos)) return null;
        return buildCaseTableDtos.stream()
                .map(table -> BuildCaseTable.withoutId(table.getTitle(), table.getContent())).toList();
    }

    public List<BuildCaseFile> toBuildCaseDetailFile(List<MultipartFile> detailFiles) throws IOException {
        if (ObjectUtils.isEmpty(detailFiles)) return null;
        List<BuildCaseFile> buildCaseFiles = new ArrayList<>();
        for(MultipartFile file : detailFiles) {
            buildCaseFiles.add(toBuildCaseFile(file, FileType.DETAIL));
        }
        return buildCaseFiles;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        final Path fileUploadPath = Paths.get(filePath).toAbsolutePath().normalize().resolve(fileName);
        Files.copy(file.getInputStream(), fileUploadPath, StandardCopyOption.REPLACE_EXISTING);
        return fileUploadPath.toString();
    }
}
