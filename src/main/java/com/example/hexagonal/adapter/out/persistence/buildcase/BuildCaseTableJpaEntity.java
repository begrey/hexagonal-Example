package com.example.hexagonal.adapter.out.persistence.buildcase;

import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="build_case_table")
public class BuildCaseTableJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "build_case_table_id")
    @Comment("구축사례 테이블 아이디")
    private Long buildCaseTableId;

    @Column(name = "title", nullable = false, length = 50)
    @Comment("제목")
    private String title;

    @Column(name = "content", nullable = false, length = 200)
    @Comment("내용")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_case_id", foreignKey = @ForeignKey(name = "build_case_table_ibfk_1"))
    @Comment("구축사례 아이디")
    private BuildCaseJpaEntity buildCase;


//    public static BuildCaseTableJpaEntity toEntity(BuildCaseJpaEntity buildCase, String title, String content) {
//        return BuildCaseTableJpaEntity.builder()
//                .title(title)
//                .content(content)
//                .buildCase(buildCase)
//                .build();
//    }

}
