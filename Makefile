
SRCAPP = FONTS/src/main/java/com/recommender/recommenderapp
SRCTEST = FONTS/src/test/java
TARGET = target
DRIVERS = EXE
LIBS = libs/junit-4.13.2.jar:libs/hamcrest-core-1.3.jar


all: compile

clean:
	rm -r $(TARGET)

build:
	javac -cp  $(LIBS) -d target $(SRCAPP)/Domain/Models/*.java \
	                          $(SRCAPP)/Domain/DataControllers/*.java \
	                          $(SRCAPP)/Domain/Controllers/*.java \
	                          $(SRCAPP)/Domain/Utils/*.java \
	                          $(SRCAPP)/Data/Controllers/*.java \
	                          $(SRCAPP)/Data/Utils/*.java \
	                          $(SRCAPP)/*.java \
	                          $(SRCTEST)/stubs/*.java \
	                          $(SRCTEST)/test/Domain/Models/*.java



.SILENT: build

compile: build
	jar cvmf FONTS/src/main/java/com/recommender/recommenderapp/Main.mf  target/Recommender.jar -C target .
.SILENT: compile




compile-driver: build
	jar cvmf FONTS/src/test/java/test/Domain/Models/DriverUser.mf $(DRIVERS)/DriverUser.jar -C target .

run_main:
	java -jar target/Recommender.jar
.SILENT: run_main

run_driver:
	java -jar $(DRIVERS)/DriverUser.jar