JC = javac
ENTRY_POINT = Main
SOURCE_FILES = src/*.java

run:
	$(JC) -sourcepath src -d out/ $(SOURCE_FILES) 
	java -cp out/ $(ENTRY_POINT)

clean:
	rm -rf out/
