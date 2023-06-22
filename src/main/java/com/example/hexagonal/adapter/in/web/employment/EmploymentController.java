package com.example.hexagonal.adapter.in.web.employment;

import com.example.hexagonal.application.port.in.employment.*;
import com.example.hexagonal.application.port.in.user.RegisterUserCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "채용공고 API")
@RequiredArgsConstructor
@RequestMapping(value = "/employments")
@RestController
class EmploymentController {
    private final AddEmploymentUseCase addEmploymentUseCase;
    private final ModifyEmploymentUseCase modifyEmploymentUseCase;
    private final DeleteEmploymentUseCase deleteEmploymentUseCase;
    private final LoadEmploymentUseCase loadEmploymentUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createEmployment(@RequestPart EmploymentRequestDto.Post post,
                                                  @RequestPart(required = true, value = "file") MultipartFile employmentThumbnail) throws IOException {
        addEmploymentUseCase.addEmployment(AddEmploymentCommand.create(post.getPosition(),
                post.getOccupation(),
                post.getAssignedTask(),
                post.getQualification(),
                post.getIsVisible(),
                post.getRecruitStartDatetime(),
                post.getRecruitEndDatetime(),
                employmentThumbnail));
        return "Employment Created.";
    }

    @PutMapping(value = "/{employmentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateEmployment(@RequestPart EmploymentRequestDto.Put update,
                                                  @RequestPart(required = false, value = "file") MultipartFile employmentThumbnail,
                                                  @PathVariable Long employmentId) throws IOException {
        modifyEmploymentUseCase.modifyEmployment(employmentId, ModifyEmploymentCommand.create(update.getPosition(),
                    update.getOccupation(),
                    update.getAssignedTask(),
                    update.getQualification(),
                    update.getIsVisible(),
                    update.getRecruitStartDatetime(),
                    update.getRecruitEndDatetime(),
                    employmentThumbnail));
        return "Employment Modified.";
    }

    @GetMapping
    public Page<EmploymentResponseDto> selectEmploymentList(Pageable pageable) {
        return loadEmploymentUseCase.loadEmployments(pageable)
                .map(employment -> EmploymentResponseDto.toDto(employment));
    }

    @GetMapping("/{employmentId}")
    public EmploymentResponseDto selectEmployment(@PathVariable Long employmentId) {
        return EmploymentResponseDto.toDto(loadEmploymentUseCase.loadEmployment(employmentId));
    }

    @DeleteMapping("/{employmentId}")
    public String deleteEmployment(@PathVariable Long employmentId) {
        deleteEmploymentUseCase.deleteEmployment(employmentId);
        return "Employment Deleted.";
    }
}

