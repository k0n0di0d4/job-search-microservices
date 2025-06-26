#!/bin/bash
set -e

docker build -t filipbruh/frontend:latest ./frontend
docker push filipbruh/frontend:latest
kubectl rollout restart deployment/frontend

echo "Frontend code, image, and deployment updated."
./