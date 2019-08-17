FROM openjdk:8
ADD build/libs/jenkins-course-0.0.1-SNAPSHOT.jar jenkins-course.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "jenkins-course.jar"]