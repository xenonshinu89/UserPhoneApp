FROM openjdk:15 
ADD target/user-phone-app.jar user-phone-app.jar 
EXPOSE 8081 
ENTRYPOINT ["java","-jar","user-phone-app.jar"] 