package com.example.hexagonal.application.service.buildcase;

import com.example.hexagonal.adapter.in.web.buildcase.BuildCaseTableDto;
import com.example.hexagonal.application.port.in.buildcase.ModifyBuildCaseCommand;
import com.example.hexagonal.application.port.in.buildcase.ModifyBuildCaseUseCase;
import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;
import com.example.hexagonal.application.port.out.buildCase.DeleteBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.ModifyBuildCasePort;
import com.example.hexagonal.domain.buildcase.BuildCase;
import com.example.hexagonal.domain.buildcase.BuildCaseFile;
import com.example.hexagonal.domain.buildcase.BuildCaseTable;
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
public class ModifyBuildCaseService implements ModifyBuildCaseUseCase {

    @Value("${file.uploadDir}")
    private String filePath;

    private final ModifyBuildCasePort modifyBuildCasePort;
    private final AddBuildCaseService addBuildCaseService;

    @Override
    public void modifyBuildCase(Long buildCaseId, ModifyBuildCaseCommand modifyBuildCaseCommand) throws IOException {
        BuildCase buildCase = BuildCase.withoutId(
                modifyBuildCaseCommand.getBuildCaseName(),
                modifyBuildCaseCommand.getIsVisible(),
                toThumbnailFile(modifyBuildCaseCommand.getThumbnail()),
                toDetailFiles(modifyBuildCaseCommand.getBuildCaseFile()),
                dtoToBuildCaseTables(modifyBuildCaseCommand.getBuildCaseTable()),
                modifyBuildCaseCommand.getCategory()
        );
        modifyBuildCasePort.modifyBuildCase(buildCaseId, buildCase);
    }

    public List<BuildCaseTable> dtoToBuildCaseTables(List<BuildCaseTableDto> tableDtos) {
        if (ObjectUtils.isEmpty(tableDtos)) return null;
        return tableDtos.stream()
                .map(table -> BuildCaseTable.withoutId(table.getTitle(), table.getContent())).toList();
    }

    public BuildCaseFile toThumbnailFile(MultipartFile file) throws IOException {
        if (file == null) return null;
        return addBuildCaseService.toBuildCaseFile(file, FileType.THUMBNAIL);
    }

    public List<BuildCaseFile> toDetailFiles(List<MultipartFile> detailFiles) throws IOException {
        return addBuildCaseService.toBuildCaseDetailFile(detailFiles);
    }


}
