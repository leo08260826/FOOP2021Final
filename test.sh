rm -r out/
javac -sourcepath src -d out/ src/*.java
java -cp out/ Main