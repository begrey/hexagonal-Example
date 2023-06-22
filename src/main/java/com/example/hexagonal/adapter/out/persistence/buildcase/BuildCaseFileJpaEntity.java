package com.example.hexagonal.adapter.out.persistence.buildcase;


import com.example.hexagonal.global.enums.FileType;
import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="build_case_file")
class BuildCaseFileJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    @Comment("파일 아이디")
    private Long fileId;

    @Column(name = "file_path", nullable = false, length = 50)
    @Comment("파일 경로")
    private String filePath;

    @Column(name = "real_name", nullable = false, length = 50)
    @Comment("파일 원본명")
    private String realName;

    @Column(name = "temp_name", nullable = false, length = 36)
    @Comment("파일 임시명")
    private String tempName;

    @Column(name = "extension", nullable = false, length = 10)
    @Comment("확장자")
    private String extension;

    @Column(name = "file_type", nullable = false, length = 10)
    @Comment("파일 종류 (썸네일 / 상세이미지)")
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(name = "file_size", nullable = false)
    @Comment("파일 크기")
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_case_id", foreignKey = @ForeignKey(name = "build_case_table_ibfk_1"))
    @Comment("구축사례 아이디")
    private BuildCaseJpaEntity buildCase;


//    public static BuildCaseFileJpaEntity toEntity(MultipartFile file, String filePath, BuildCaseJpaEntity buildCase, String fileType) {
//        String filename = file.getOriginalFilename();
//        return BuildCaseFileJpaEntity.builder()
//                .filePath(filePath)
//                .realName(filename)
//                .tempName(UUID.randomUUID().toString())
//                .extension(FilenameUtils.getExtension(filename))
//                .fileSize(file.getSize())
//                .fileType(fileType)
//                .buildCase(buildCase)
//                .build();
//    }

}
