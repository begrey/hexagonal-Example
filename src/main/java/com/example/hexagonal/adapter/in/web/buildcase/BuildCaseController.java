package com.example.hexagonal.adapter.in.web.buildcase;

import com.example.hexagonal.application.port.in.buildcase.*;
import com.example.hexagonal.application.port.in.employment.LoadEmploymentUseCase;
import com.example.hexagonal.application.port.in.employment.ModifyEmploymentCommand;
import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "구축사례 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/build-case/{category}")
class BuildCaseController {

    private final AddBuildCaseUseCase addBuildCaseUseCase;
    private final ModifyBuildCaseUseCase modifyBuildCaseUseCase;
    private final DeleteBuildCaseUseCase deleteBuildCaseUseCase;
    private final LoadBuildCaseUseCase loadBuildCaseUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createBuildCase(@RequestPart BuildcaseRequestDto.Post post,
                                                 @RequestPart(required = true, value = "thumbnail") MultipartFile thumbnail,
                                                 @RequestPart(required = false, value = "detailFiles") List<MultipartFile> detailFiles
                                                ) throws IOException {
        List<BuildCaseTable> buildCaseTables = post.getTables().stream()
                .map(tableDto -> BuildCaseTable.withoutId(tableDto.getTitle(), tableDto.getContent())).toList();
        addBuildCaseUseCase.addBuildCase(AddBuildCaseCommand.create(
                post.getBuildCaseName(),
                post.getIsVisible(),
                thumbnail,
                detailFiles,
                buildCaseTables,
                post.getCategoryId()
        ));
        return "BuildCase Created.";
    }

    @PutMapping(value = "/{buildCaseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateBuildCase(@RequestPart BuildcaseRequestDto.Put update, @PathVariable Long buildCaseId,
                                                 @RequestPart(required = false, value = "thumbnail") MultipartFile thumbnail,
                                                 @RequestPart(required = false, value = "detailFiles") List<MultipartFile> detailFiles) throws IOException {
        List<BuildCaseTable> buildCaseTables = update.getTables().stream()
                        .map(tableDto -> BuildCaseTable.withoutId(tableDto.getTitle(), tableDto.getContent())).toList();
        modifyBuildCaseUseCase.modifyBuildCase(buildCaseId, ModifyBuildCaseCommand.create(
                update.getBuildCaseName(),
                update.getIsVisible(),
                thumbnail,
                detailFiles,
                buildCaseTables,
                update.getCategoryId()
        ));
        return "BuildCase Modified.";
    }

    @GetMapping
    public Page<BuildcaseResponseDto> selectBuildCaseList(Pageable pageable) {
        return loadBuildCaseUseCase.getBuildCases(pageable).map(buildCase -> BuildcaseResponseDto.toDto(buildCase));
    }

    @GetMapping("/{buildCaseId}")
    public BuildcaseResponseDto selectBuildCase(@PathVariable Long buildCaseId) {
        return BuildcaseResponseDto.toDto(loadBuildCaseUseCase.getBuildCase(buildCaseId));
    }

    @DeleteMapping("/{buildCaseId}")
    public String deleteEmployment(@PathVariable Long buildCaseId) {
        deleteBuildCaseUseCase.deleteBuildCase(buildCaseId);
        return "Delete Completed.";
    }
}
