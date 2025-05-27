#!/bin/bash
set -e

# 1. Build JARs in parallel
(cd identity_service && mvn clean package) &
(cd job_listing_service && mvn clean package) &
(cd api_gateway && mvn clean package) &
wait

# 2. Build Docker images in parallel
docker build -t filipbruh/api-gateway:latest ./api_gateway &
docker build -t filipbruh/identity-service:latest ./identity_service &
docker build -t filipbruh/job-service:latest ./job_listing_service &
wait

docker push filipbruh/api-gateway:latest &
docker push filipbruh/identity-service:latest &
docker push filipbruh/job-service:latest &
wait

# 3. Restart deployments in parallel (local dev, no push needed)
kubectl rollout restart deployment/api-gateway &
kubectl rollout restart deployment/identity-service &
kubectl rollout restart deployment/job-service &
wait

echo "Code, images, and deployments updated."
