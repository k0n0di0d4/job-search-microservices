package org.filipscode.job_listing_service.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public JobDTO createJob(CreateJobDTO dto, String postedBy) {
        Job job = Job.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .location(dto.getLocation())
        .company(dto.getCompany())
        .employmentType(dto.getEmploymentType())
        .salaryMin(dto.getSalaryMin())
        .salaryMax(dto.getSalaryMax())
        .currency(dto.getCurrency())
        .postedBy(postedBy)
        .postedDate(LocalDate.now())
        .closingDate(dto.getClosingDate())
        .active(true)
        .requirements(dto.getRequirements())
        .build();

        Job saved = jobRepository.save(job);
        return toDto(saved);
    }

    // Get all
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID
    public Optional<JobDTO> getJobById(Long id) {
        return jobRepository.findById(id).map(this::toDto);
    }

    // Map Entity to DTO
    public JobDTO toDto(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getCompany(),
                job.getEmploymentType(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getCurrency(),
                job.getPostedBy(),
                job.getPostedDate(),
                job.getClosingDate(),
                job.getActive(),
                job.getRequirements()
        );
    }

}
