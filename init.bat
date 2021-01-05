@echo off

IF EXIST "C:\Program Files\Java\jdk-11.0.7" set JAVA_HOME=C:\Program Files\Java\jdk-11.0.7

start /B mvn spring-boot:run 