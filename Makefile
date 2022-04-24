
SRCAPP = FONTS/src/main/java/com/recommender/recommenderapp
SRCTEST = FONTS/src/test/java
MODS = javafx.controls,javafx.fxml
TARGET = target
EXE = EXE
LIBS = libs/junit-4.13.2.jar:libs/hamcrest-core-1.3.jar


all: compile_all

clean:
	rm -r $(TARGET)
	rm -r $(EXE)

build:
	javac -cp  $(LIBS)  --module-path libs/$(HOSTOS)  --add-modules $(MODS)   -d target $(SRCAPP)/Domain/Models/*.java \
																				$(SRCAPP)/Data/Controllers/*.java \
																				$(SRCAPP)/Exceptions/*.java \
																				$(SRCAPP)/Domain/Controllers/*.java \
																				$(SRCAPP)/Domain/Utils/*.java \
																				$(SRCAPP)/Data/Controllers/*.java \
																				$(SRCAPP)/Domain/DataControllers/*.java \
																				$(SRCAPP)/Data/Utils/*.java \
																				$(SRCAPP)/View/Controllers/*.java \
																				$(SRCAPP)/View/Utils/*.java \
																				$(SRCAPP)/View/Views/*.java \
																				$(SRCAPP)/*.java \
																				$(SRCTEST)/stubs/*.java \
																				$(SRCTEST)/test/Domain/Models/*.java \
																				$(SRCTEST)/fakes/*.java



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


ifeq ($(OS),Windows_NT)
HOSTOS := Windows
else
UNAME_S := $(shell uname -s)
	ifeq ($(UNAME_S),Darwin)
HOSTOS := MacOS
	else
HOSTOS := Linux
	endif
endif