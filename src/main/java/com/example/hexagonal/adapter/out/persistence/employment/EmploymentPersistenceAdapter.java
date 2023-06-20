package com.example.hexagonal.adapter.out.persistence.employment;

import com.example.hexagonal.application.port.in.employment.*;
import com.example.hexagonal.application.port.out.employment.AddEmploymentPort;
import com.example.hexagonal.application.port.out.employment.DeleteEmploymentPort;
import com.example.hexagonal.application.port.out.employment.LoadEmploymentPort;
import com.example.hexagonal.application.port.out.employment.ModifyEmploymentPort;
import com.example.hexagonal.domain.employment.Employment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
class EmploymentPersistenceAdapter implements AddEmploymentPort, DeleteEmploymentPort, LoadEmploymentPort, ModifyEmploymentPort {

    private final EmploymentRepository employmentRepository;
    private final EmploymentFileRepository employmentFileRepository;
    private final EmploymentMapper employmentMapper;

    @Override
    public void addEmployment(Employment employment) {
        EmploymentJpaEntity jpaEntity = employmentRepository.save(employmentMapper.toEmploymentEntity(employment));
        EmploymentFileJpaEntity fileJpaEntity = employmentMapper.toEmploymentFileEntity(employment.getEmploymentFile(), jpaEntity);

        employmentFileRepository.save(fileJpaEntity);
        jpaEntity.setEmploymentFiles(fileJpaEntity);
    }

    @Override
    public void deleteEmployment(Long employmentId) {
        employmentFileRepository.deleteByEmploymentEmploymentId(employmentId);
        employmentRepository.deleteById(employmentId);
    }

    @Override
    public Page<Employment> loadEmployments(Pageable pageable) {
        Page<EmploymentJpaEntity> jpaEntities = employmentRepository.findAll(pageable);
        return jpaEntities.map(entity -> employmentMapper.toEmploymentDomain(entity));
    }

    @Override
    public Employment loadEmployment(Long employmentId) {
        EmploymentJpaEntity jpaEntity = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Employment가 존재하지 않음."));
        return employmentMapper.toEmploymentDomain(jpaEntity);
    }

    @Override
    public void modifyEmployment(Long employmentId, Employment changeEmployment) {
        EmploymentJpaEntity jpaEntity = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Employment가 존재하지 않음."));
        // 바꿀 File이 있는 경우에만 변경
        if (!ObjectUtils.isEmpty(changeEmployment.getEmploymentFile())) {
            employmentFileRepository.deleteByEmploymentEmploymentId(employmentId);
            EmploymentFileJpaEntity fileJpaEntity = employmentMapper.toEmploymentFileEntity(changeEmployment.getEmploymentFile(), jpaEntity);
            employmentFileRepository.save(fileJpaEntity);
        }
        jpaEntity.update(changeEmployment);
        employmentRepository.save(jpaEntity);
    }
}
