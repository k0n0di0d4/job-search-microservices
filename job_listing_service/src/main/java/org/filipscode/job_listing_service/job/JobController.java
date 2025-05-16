package org.filipscode.job_listing_service.job;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    @GetMapping("/status")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from job listing service");
    }
}
