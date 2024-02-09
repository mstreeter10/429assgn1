javac -d classes -classpath classes;. userinterface\*.java model\*.java common\*.java database\*.java event\*.java exception\*.java impresario\*.java Utilities\*.java
javac -classpath classes;. Project.java
java -cp mariadb-java-client-3.0.3.jar;classes;. Project