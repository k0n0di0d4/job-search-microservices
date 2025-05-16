#!/bin/bash
cd identity_service
mvn clean package

cd ../job_listing_service
mvn clean package

cd ../api_gateway
mvn clean package

cd ../service_registry
mvn clean package
