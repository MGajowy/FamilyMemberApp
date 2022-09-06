FROM openjdk:11

COPY target/FamilyMemberApp-*.jar /FamilyMemberApp.jar

CMD ["java" , "-jar" , "/FamilyMemberApp.jar" ]
