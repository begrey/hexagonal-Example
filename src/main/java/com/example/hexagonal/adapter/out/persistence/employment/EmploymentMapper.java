package com.example.hexagonal.adapter.out.persistence.employment;

import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.domain.employment.EmploymentFile;
import org.springframework.stereotype.Component;

@Component
class EmploymentMapper {

    Employment toEmploymentDomain(EmploymentJpaEntity entity) {
        return Employment.withId(entity.getEmploymentId(),
                entity.getPosition(),
                entity.getOccupation(),
                entity.getAssignedTask(),
                entity.getQualification(),
                entity.getIsVisible(),
                entity.getRecruitStartDatetime(),
                entity.getRecruitEndDatetime(),
                toEmploymentFileDomain(entity.getEmploymentFile()));
    }

    EmploymentFile toEmploymentFileDomain(EmploymentFileJpaEntity entity) {
        return EmploymentFile.withId(entity.getFileId(),
                entity.getFilePath(),
                entity.getRealName(),
                entity.getTempName(),
                entity.getExtension(),
                entity.getFileSize());
    }

    EmploymentJpaEntity toEmploymentEntity(Employment employment) {
        System.out.println(employment.getRecruitStartDatetime() + "dddd");
        return EmploymentJpaEntity.builder()
                .employmentId(employment.getEmploymentId())
                .assignedTask(employment.getAssignedTask())
                .occupation(employment.getOccupation())
                .position(employment.getPosition())
                .qualification(employment.getQualification())
                .recruitEndDatetime(employment.getRecruitEndDatetime())
                .recruitStartDatetime(employment.getRecruitStartDatetime())
                .isVisible(employment.getIsVisible())
                .build();
    }

    EmploymentFileJpaEntity toEmploymentFileEntity(EmploymentFile employmentFile, EmploymentJpaEntity employmentJpaEntity) {
        return EmploymentFileJpaEntity.builder()
                .fileId(employmentFile.getFileId())
                .fileSize(employmentFile.getFileSize())
                .filePath(employmentFile.getFilePath())
                .tempName(employmentFile.getTempName())
                .realName(employmentFile.getRealName())
                .extension(employmentFile.getExtension())
                .employment(employmentJpaEntity)
                .build();
    }

}
