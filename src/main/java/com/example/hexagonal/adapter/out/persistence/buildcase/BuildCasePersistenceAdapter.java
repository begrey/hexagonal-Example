package com.example.hexagonal.adapter.out.persistence.buildcase;

import com.example.hexagonal.application.port.out.buildCase.AddBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.DeleteBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.LoadBuildCasePort;
import com.example.hexagonal.application.port.out.buildCase.ModifyBuildCasePort;
import com.example.hexagonal.domain.buildcase.BuildCase;
import com.example.hexagonal.domain.category.Category;
import com.example.hexagonal.global.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Component
class BuildCasePersistenceAdapter implements LoadBuildCasePort, ModifyBuildCasePort, DeleteBuildCasePort, AddBuildCasePort {

    private final BuildCaseRepository buildCaseRepository;
    private final BuildCaseFileRepository buildCaseFileRepository;
    private final BuildCaseTableRepository buildCaseTableRepository;
    private final CategoryRepository categoryRepository;
    private final BuildCaseMapper buildCaseMapper;
    @Transactional
    @Override
    public void addBuildCase(BuildCase buildCase) {
        // 구축사례 저장
        CategoryJpaEntity categoryJpaEntity = categoryRepository.findById(buildCase.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        BuildCaseJpaEntity jpaEntity = buildCaseMapper.toBuildCaseJpaEntity(buildCase, categoryJpaEntity);
        BuildCaseJpaEntity buildCaseJpaEntity = buildCaseRepository.save(jpaEntity);

        // 테이블 저장
        saveBuildCaseTable(buildCase, buildCaseJpaEntity);
        // 파일 저장
        if (!ObjectUtils.isEmpty(buildCase.getDetailFiles())) {
            saveDetailFile(buildCase, buildCaseJpaEntity);
        }
        saveThumbnailFile(buildCase, buildCaseJpaEntity);

    }

    public void saveBuildCaseTable(BuildCase buildCase, BuildCaseJpaEntity buildCaseJpaEntity) {
        List<BuildCaseTableJpaEntity> tableJpaEntities = buildCase.getBuildCaseTable().stream()
                .map(table -> buildCaseMapper.toBuildCaseTableJpaEntity(table, buildCaseJpaEntity)).toList();
        tableJpaEntities = tableJpaEntities.stream()
                .map(table -> buildCaseTableRepository.save(table)).toList();
        buildCaseJpaEntity.setTable(tableJpaEntities);
    }

    public void saveDetailFile(BuildCase buildCase, BuildCaseJpaEntity buildCaseJpaEntity) {
        List<BuildCaseFileJpaEntity> detailFiles = buildCase.getDetailFiles().stream()
                .map(detail -> buildCaseMapper.toBuildCaseFileJpaEntity(detail, buildCaseJpaEntity))
                .toList();
        for(BuildCaseFileJpaEntity entity : detailFiles) {
            buildCaseJpaEntity.setBuildCaseFiles(entity);
            buildCaseFileRepository.save(entity);
        }
    }

    public void saveThumbnailFile(BuildCase buildCase, BuildCaseJpaEntity buildCaseJpaEntity) {
        BuildCaseFileJpaEntity fileJpaEntity = buildCaseMapper.toBuildCaseFileJpaEntity(buildCase.getThumbnailFile(), buildCaseJpaEntity);
        buildCaseFileRepository.save(fileJpaEntity);
        buildCaseJpaEntity.setBuildCaseFiles(fileJpaEntity);
    }

    @Transactional
    @Override
    public void modifyBuildCase(Long buildCaseId, BuildCase buildCase) {
        BuildCaseJpaEntity jpaEntity = buildCaseRepository.findById(buildCaseId)
                .orElseThrow(() -> new IllegalArgumentException("해당 구축사례가 존재하지 않음."));
        CategoryJpaEntity categoryJpaEntity = categoryRepository.findById(buildCase.getCategory())
                        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 존재하지 않음."));
        jpaEntity.update(buildCase, categoryJpaEntity);
        if (!ObjectUtils.isEmpty(buildCase.getBuildCaseTable())) {
            deleteBuildCaseTable(buildCaseId);
            saveBuildCaseTable(buildCase, jpaEntity);
        }
        if (!ObjectUtils.isEmpty(buildCase.getThumbnailFile())) {
            deleteThumbnailFile(jpaEntity);
            saveThumbnailFile(buildCase, jpaEntity);
        }
        if (!ObjectUtils.isEmpty(buildCase.getDetailFiles())) {
            deleteDatailFiles(jpaEntity);
            saveDetailFile(buildCase, jpaEntity);
        }
    }

    public void deleteDatailFiles(BuildCaseJpaEntity jpaEntity) {
        List<BuildCaseFileJpaEntity> entity = jpaEntity.getBuildCaseFiles().stream()
                .filter(file -> file.getFileType().equals(FileType.DETAIL)).toList();
        if (!ObjectUtils.isEmpty(entity))
            entity.stream().forEach(fileEntity -> buildCaseFileRepository.delete(fileEntity));
    }

    public void deleteThumbnailFile(BuildCaseJpaEntity jpaEntity) {
        List<BuildCaseFileJpaEntity> entity = jpaEntity.getBuildCaseFiles().stream()
                .filter(file -> file.getFileType().equals(FileType.DETAIL)).toList();
        buildCaseFileRepository.delete(entity.get(0));
    }

    @Transactional
    @Override
    public void deleteBuildCase(Long buildCaseId) {
        buildCaseRepository.deleteById(buildCaseId);
    }

    @Override
    public void deleteBuildCaseFile(Long buildCaseId) {
        buildCaseFileRepository.deleteByBuildCaseBuildCaseId(buildCaseId);
    }

    @Override
    public void deleteBuildCaseTable(Long buildCaseId) {
        buildCaseTableRepository.deleteByBuildCaseBuildCaseId(buildCaseId);
    }

    @Override
    public Page<BuildCase> loadBuildCases(Pageable pageable) {
        Page<BuildCaseJpaEntity> entities = buildCaseRepository.findAll(pageable);
        return entities.map(entity -> buildCaseMapper.toBuildCase(entity));
    }

    @Override
    public BuildCase loadBuildCase(Long buildCaseId) {
        BuildCaseJpaEntity jpaEntity = buildCaseRepository.findById(buildCaseId).orElseThrow(() -> new IllegalArgumentException("해당 BuildCase가 존재하지 않음."));
        return buildCaseMapper.toBuildCase(jpaEntity);
    }


}
