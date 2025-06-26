package org.filipscode.job_listing_service.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String location;

    @NotBlank
    private String company;

    @NotBlank
    private JobType employmentType; // e.g. "FULL_TIME", "PART_TIME", "CONTRACT", "INTERNSHIP"

    private Double salaryMin;
    private Double salaryMax;

    private String currency;

    @NotBlank
    private String postedBy;

    private LocalDate postedDate;

    private LocalDate closingDate; // when application closes

    private Boolean active = true; // for soft deletion

    private String requirements;
}
