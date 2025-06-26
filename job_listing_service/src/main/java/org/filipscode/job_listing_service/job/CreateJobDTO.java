package org.filipscode.job_listing_service.job;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String location;

    @NotBlank
    private String company;

    @NotBlank
    private JobType employmentType;

    private Double salaryMin;
    private Double salaryMax;
    private String currency;
    private LocalDate closingDate;
    private String requirements;
}

