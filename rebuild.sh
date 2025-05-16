#!/bin/bash

./jar_creation.sh
docker-compose down -v --remove-orphans
docker builder prune -af
docker-compose up -d --build
