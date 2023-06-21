package com.example.hexagonal.adapter.out.persistence.buildcase;

import com.example.hexagonal.domain.buildcase.BuildCase;
import com.example.hexagonal.domain.buildcase.BuildCaseFile;
import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildCaseMapper {

    BuildCase toBuildCase(BuildCaseJpaEntity entity) {
        // 썸네일, 상세 이미지로 각자 분할
        Map<Boolean, List<BuildCaseFile>> fileList = partitioningFile(toBuildCaseFiles(entity));
        List<BuildCaseFile> details = fileList.get(true);
        BuildCaseFile thumbnail = fileList.get(false).get(0);

        return BuildCase.withId(entity.getBuildCaseId(),
                entity.getBuildCaseName(),
                entity.getIsVisible(),
                thumbnail,
                details,
                toBuildCaseTables(entity),
                entity.getCategory().getCategoryId());
    }

    BuildCaseJpaEntity toBuildCaseJpaEntity(BuildCase buildCase, CategoryJpaEntity categoryJpaEntity) {
        return BuildCaseJpaEntity.builder()
                .buildCaseName(buildCase.getBuildCaseName())
                .isVisible(buildCase.getIsVisible())
                .category(categoryJpaEntity)
                .build();
    }

    BuildCaseFile toBuildCaseFile(BuildCaseFileJpaEntity entity) {
        return BuildCaseFile.withId(entity.getFileId(),
                entity.getFilePath(),
                entity.getRealName(),
                entity.getTempName(),
                entity.getExtension(),
                entity.getFileType(),
                entity.getFileSize());
    }

    List<BuildCaseFile> toBuildCaseFiles(BuildCaseJpaEntity entity) {
        List<BuildCaseFileJpaEntity> fileJpaEntities = entity.getBuildCaseFiles();
        return fileJpaEntities.stream()
                .map(file -> toBuildCaseFile(file)).toList();
    }

    BuildCaseFileJpaEntity toBuildCaseFileJpaEntity(BuildCaseFile buildCaseFile, BuildCaseJpaEntity buildCaseJpaEntity) {
        return BuildCaseFileJpaEntity.builder()
                .buildCase(buildCaseJpaEntity)
                .filePath(buildCaseFile.getFilePath())
                .fileType(buildCaseFile.getFileType())
                .fileSize(buildCaseFile.getFileSize())
                .extension(buildCaseFile.getExtension())
                .realName(buildCaseFile.getRealName())
                .tempName(buildCaseFile.getTempName())
                .build();
    }

    BuildCaseTable toBuildCaseTable(BuildCaseTableJpaEntity entity) {
        return BuildCaseTable.withId(entity.getBuildCaseTableId(),
                entity.getTitle(),
                entity.getContent());
    }

    List<BuildCaseTable> toBuildCaseTables(BuildCaseJpaEntity entity) {
        return entity.getBuildCaseTables().stream()
                .map(table -> toBuildCaseTable(table)).toList();
    }

    BuildCaseTableJpaEntity toBuildCaseTableJpaEntity(BuildCaseTable buildCaseTable, BuildCaseJpaEntity buildCaseJpaEntity) {
        return BuildCaseTableJpaEntity.builder()
                .title(buildCaseTable.getTitle())
                .content(buildCaseTable.getContent())
                .buildCase(buildCaseJpaEntity)
                .build();
    }

    Map<Boolean, List<BuildCaseFile>> partitioningFile(List<BuildCaseFile> buildCaseFiles) {
        return buildCaseFiles.stream()
                .collect(Collectors.partitioningBy(BuildCaseFile::isThumbNail));

    }

}
