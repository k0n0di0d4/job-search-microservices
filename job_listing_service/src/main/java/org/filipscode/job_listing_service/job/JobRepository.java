package org.filipscode.job_listing_service.job;

import org.springframework.data.jpa.repository.JpaRepository;

interface JobRepository extends JpaRepository<Job, Long> {

}
