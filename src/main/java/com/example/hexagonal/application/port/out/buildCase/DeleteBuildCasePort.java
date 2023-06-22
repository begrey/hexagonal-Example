package com.example.hexagonal.application.port.out.buildCase;


public interface DeleteBuildCasePort {
    void deleteBuildCase(Long buildCaseId);

    void deleteBuildCaseFile(Long buildCaseId);
    void deleteBuildCaseTable(Long buildCaseId);


}
