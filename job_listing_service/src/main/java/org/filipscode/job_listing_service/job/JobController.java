package org.filipscode.job_listing_service.job;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    final JobService jobService;

    @GetMapping("/status")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from job listing service");
    }

    @PostMapping
    public ResponseEntity<JobDTO> addJob(
            @RequestBody CreateJobDTO dto,
            Principal principal) {
        JobDTO jobDTO = jobService.createJob(dto, principal.getName());
        return(ResponseEntity.ok(jobDTO));
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("id") long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
