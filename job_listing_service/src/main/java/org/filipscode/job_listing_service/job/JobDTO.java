package org.filipscode.job_listing_service.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String location;
    private String company;
    private JobType employmentType;
    private Double salaryMin;
    private Double salaryMax;
    private String currency;
    private String postedBy;
    private LocalDate postedDate;
    private LocalDate closingDate;
    private Boolean active;
    private String requirements;
}
