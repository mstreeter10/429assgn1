javac -d classes -classpath classes;. model\*.java common\*.java database\*.java event\*.java exception\*.java impresario\*.java Utilities\*.java
javac -classpath classes;. Library.java
java -cp mariadb-java-client-3.0.3.jar;classes;. Library