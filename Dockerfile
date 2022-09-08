FROM openjdk:11

COPY target/FamilyMemberApp-*.jar app.jar

ENTRYPOINT ["java" , "-jar" , "app.jar" ]
