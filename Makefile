
SRC = FONTS/src/main/java/com/recommender/recommenderapp
TARGET = target
DRIVERS = EXE

clean:
	rm -r $(TARGET)

build:
	javac   -d target/classes $(SRC)/Domain/Models/*.java \
	                          $(SRC)/Domain/DataControllers/*.java \
	                          $(SRC)/Domain/Controllers/*.java \
	                          $(SRC)/Domain/Utils/*.java \
	                          $(SRC)/Data/Controllers/*.java \
	                          $(SRC)/Data/Utils/*.java \
	                          $(SRC)/*.java

.SILENT: build

compile: build
	jar cvmf FONTS/src/main/java/com/recommender/recommenderapp/Main.mf  target/Recommender.jar -C target .
.SILENT: compile

#java -jar target/Recommender.jar