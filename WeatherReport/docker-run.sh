#Below command builds docker image for spring boot project
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=weather-report-image
#Below command runs docker container by using above image
docker run -p 8080:8080 --name weather-report weather-report-image
#Can use below command to run application property of respective environments
#docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t weather-report-docker