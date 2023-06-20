package com.example.hexagonal.adapter.out.persistence.category;

import com.example.hexagonal.adapter.out.persistence.buildcase.BuildCaseJpaEntity;

import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="category")
public class CategoryJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @Comment("카테고리 아이디")
    private long categoryId;

    @Column(name = "category_name", nullable = false, length = 12)
    @Comment("카테고리명")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "category_ibfk_1"))
    private CategoryJpaEntity parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<CategoryJpaEntity> child = new ArrayList<>();

    @OneToOne(mappedBy = "category", fetch = FetchType.LAZY)
    private BuildCaseJpaEntity buildCase;

}
