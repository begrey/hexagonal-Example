package com.example.hexagonal.adapter.out.persistence.employment;


import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.global.enums.DisplayType;
import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="employment")
class EmploymentJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_id")
    @Comment("채용공고 아이디")
    private Long employmentId;

    @Column(name = "position", nullable = false, length = 20)
    @Comment("모집부문")
    private String position;

    @Column(name = "occupation", nullable = false, length = 20)
    @Comment("채용분야")
    private String occupation;

    @Column(name = "assigned_task", nullable = false, length = 300)
    @Comment("해당업무")
    private String assignedTask;

    @Column(name = "qualification", length = 300)
    @Comment("자격요건")
    private String qualification;

    @Column(name = "is_visible", nullable = false)
    @Comment("노출여부")
    @Enumerated(EnumType.STRING)
    private DisplayType isVisible;

    @Column(name = "recruit_start_datetime")
    @Comment("채용시작일")
    private LocalDateTime recruitStartDatetime;

    @Column(name = "recruit_end_datetime")
    @Comment("채용종료일")
    private LocalDateTime recruitEndDatetime;

    @OneToOne(mappedBy = "employment", fetch = FetchType.LAZY)
    private EmploymentFileJpaEntity employmentFile;

    public void setEmploymentFiles(EmploymentFileJpaEntity employmentFile) {
        this.employmentFile = employmentFile;
    }

    public EmploymentJpaEntity update (Employment changeEmployment) {
        this.position = changeEmployment.getPosition();
        this.occupation = changeEmployment.getOccupation();
        this.assignedTask = changeEmployment.getAssignedTask();
        this.qualification = changeEmployment.getQualification();
        this.recruitStartDatetime = changeEmployment.getRecruitStartDatetime();
        this.recruitEndDatetime = changeEmployment.getRecruitEndDatetime();
        this.isVisible = changeEmployment.getIsVisible();
        return this;
    }
}
