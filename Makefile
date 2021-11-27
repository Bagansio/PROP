
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


run_UserDriver:
	make run_driver DRIVER=DriverUser

run_UserGroupDriver:
	make run_driver DRIVER=DriverUserGroup

run_CollaborativeFilteringDriver:
	make run_driver DRIVER=DriverCollaborativeFiltering

run_ItemDriver:
	make run_driver DRIVER=DriverItem

run_RecommendationDriver:
	make run_driver DRIVER=DriverRecommendation

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
	make compile_driver DRIVER=DriverCollaborativeFiltering
	make compile_driver DRIVER=DriverItem
	make compile_driver DRIVER=DriverRecommendation
	make compile_test_UserUnitary


