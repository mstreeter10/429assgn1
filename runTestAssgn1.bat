javac -d "classes" -classpath "classes;." model\*.java
javac -classpath "classes;." TestAssgn1.java
java -cp "mariadb-java-client-3.0.3.jar;classes;." TestAssgn1