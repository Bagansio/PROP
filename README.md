## How to usage Makefile

HOW TO USE:

make clean

make

make run_testname


*DEFAULT*:


- make
   + use *compile_all*, compiles and generate all the jar necessaries in EXE dir



*COMPILE*:


All require *build*

- make build
    + compile all the java files in target


- make compile_all
  + compile each driver in EXE

- make compile_main
  +  compile Main 

- make compile_driver  <DRIVER=drivername>
    + compile the drivername Driver in EXE

- make compile_test_UserUnitary
    + compile the User Unitary test in JUnit in EXE


*RUN*:

Use default make or make compile_all first

- make run_main:
  + Execute Main

- make  run_UserDriver
  + Execute UserDriver

- make run_UserGroupDriver
    + Execute UserGroupDriver

- make run_driver <DRIVER=drivername>
    + Execute drivername Driver

- make run_junitUser
  + Execute User JUnit Test

- make run_junit <TEST=testname>
    + Execute testname JUnit test

*CLEAN*

- make clean
  + Delete the EXE and target dirs



