
SRCAPP = FONTS/src/main/java/com/recommender/recommenderapp
SRCTEST = FONTS/src/test/java
TARGET = target
EXE = EXE
LIBS = libs/junit-4.13.2.jar:libs/hamcrest-core-1.3.jar


all: compile_all

clean:
	rm -r $(TARGET)
	rm -r $(EXE)

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

compile_main: build
	mkdir -p $(EXE)
	jar cvmf FONTS/src/main/java/com/recommender/recommenderapp/Main.mf  $(EXE)/Recommender.jar -C target .
.SILENT: compile


compile_driver: build
	mkdir -p $(EXE)/DRIVERS
	jar cvmf FONTS/src/test/java/test/Domain/Models/$(DRIVER).mf $(EXE)/DRIVERS/$(DRIVER).jar -C target .

compile_test_UserUnitary: build
	mkdir -p $(EXE)/JUNIT/libs
	jar cvmf FONTS/src/test/java/test/Domain/Models/JUnitUser.mf $(EXE)/JUNIT/JUnitUser.jar  -C target .
	cp -R libs/*.jar $(EXE)/JUNIT/libs

run_main:
	java -jar $(EXE)/Recommender.jar
.SILENT: run_main


run_driverUser:
	make run_driver DRIVER=DriverUser

run_driverUserGroup:
	make run_driver DRIVER=DriverUserGroup

run_junitUser:
	make run_junit TEST=JUnitUser

run_driver:
	java -jar $(EXE)/DRIVERS/$(DRIVER).jar

run_junit:
	java -jar $(EXE)/JUNIT/$(TEST).jar


compile_all:
	make compile_main
	make compile_driver DRIVER=DriverUser
	make compile_driver DRIVER=DriverUserGroup
	make compile_test_UserUnitary

help:
	echo "List of tasks:"

	echo ""

	echo "- (default):\t\tbuild Othello applications, drivers and JUnit JARs to dist/ folder"

	echo ""

	echo "- othello:\t\texecute the Othello application"
	echo "- driver:\t\texecute the driver specified by DRIVER=<Driver>"
		echo "\t\t\t\t- make driver DRIVER=Pair"
		echo "\t\t\t\t- make driver DRIVER=Bot"
		echo "\t\t\t\t- make driver DRIVER=User"
		echo "\t\t\t\t- make driver DRIVER=Configuration"
		echo "\t\t\t\t- make driver DRIVER=Game"
		echo "\t\t\t\t- make driver DRIVER=Board"
		echo "\t\t\t\t- make driver DRIVER=EasyDifficulty"
		echo "\t\t\t\t- make driver DRIVER=MediumDifficulty"
		echo "\t\t\t\t- make driver DRIVER=HardDifficulty"

	echo ""

	echo "- test-ranking:\t\texecute JUnit ranking tests"
	echo "- test-entry:\t\texecute JUnit entry tests"

	echo ""

	echo "- run-othello:\tbuild and execute the Othello application JAR"
	echo "- run-driver:\t\tbuild and execute the driver specified by DRIVER=<Driver> JAR"
	echo "- run-test-ranking:\tbuild and execute the JUnit ranking tests application JAR"
	echo "- run-test-entry:\tbuild and execute the JUnit entry tests application JAR"

	echo ""

	echo "- compile-othello:\tbuild the Othello application JAR to dist/ folder"
	echo "- compile-driver:\tbuild the driver specified by DRIVER=<Driver> JAR to dist/ folder"
		echo "\t\t\t\t- make compile-driver DRIVER=Pair"
		echo "\t\t\t\t- make compile-driver DRIVER=Bot"
		echo "\t\t\t\t- make compile-driver DRIVER=User"
		echo "\t\t\t\t- make compile-driver DRIVER=Configuration"
		echo "\t\t\t\t- make compile-driver DRIVER=Game"
		echo "\t\t\t\t- make compile-driver DRIVER=Board"
		echo "\t\t\t\t- make compile-driver DRIVER=EasyDifficulty"
		echo "\t\t\t\t- make compile-driver DRIVER=MediumDifficulty"
		echo "\t\t\t\t- make compile-driver DRIVER=HardDifficulty"
	echo "- compile-test-ranking:\tbuild the JUnit ranking tests application JAR to dist/ folder"
	echo "- compile-test-entry:\tbuild the JUnit entry tests application JAR to dist/ folder"

	echo ""

	echo "- doc:\t\t\tbuild doxygen documentation"
	echo "- clean:\t\tclean binaries and databases"
.SILENT: help
