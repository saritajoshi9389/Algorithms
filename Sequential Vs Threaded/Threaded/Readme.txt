						Threaded Analysis Assignment 01
---------------------------------------------------------------------------------------------
Steps to run, compile this threaded program-
--------------------------------------------------------------------------------------------
RUN->steps:-
Pre-requisite-
Minimum jre 1.8
Use of gradle to for dependency check
Opencsv CSV parser reuired for CSV file handling- Taken care by Gradle
-Untar the java project and import it in any java ide- used Netbeans 8.1
-Use a gradle project to run this project
-Import the project in NetBeans/Eclipse
-It prompts for a scanner input which takes argument as the input directory for data files
-To run this program, Assignment01.java ->use 'file_path'  in arguments tab of command line arguments window-> run and apply

Running this file on LINUX (using gradle)
- navigate to the folder Joshi_Sarita_A1
- Run the below commands
	$ gradle wrapper
	$./gradlew clean build
	$./gradle run -Dexec.args="folder-path"

(the path of data folder is the argument to this code)

---------------------------------------------------------------------------------------------
Content-

-JAVA src folder for run
-gradle build file
-readme

Below is the analysis of the records from the data files
Processing time -> 98 secs for 25 threads in parallel processing
Output:-
-----------------------------------------------------------------------------------------------

Below is the average and median ticket price of all only the airlines which are active in January, 2015.
The K and F values for the entire data, not only for Jan 2015 (For the entire dataset)

K=60457 F=12598804

-----------------------------------------------------------------------------------------------
F9	135.36593251885083	82.11000061035156
WN	139.60651203805148	117.0
AS	203.15005315833852	171.60000610351562
HA	278.3242520496481	122.8499984741211
OO	285.04577337117564	257.3999938964844
MQ	286.3438455758009	267.75
EV	293.6051445268795	273.05999755859375
NK	487.5084192439863	469.3599853515625
AA	500.6122836113925	467.5
B6	500.76423043852105	462.4800109863281
DL	570.1864742397686	480.510009765625
US	572.2144519755986	469.1700134277344
VX	638.057369207271	594.0
UA	957.430094912673	865.4400024414062
-------------------------------------------------------------------------------------------------
Analysis - Parallel execution with 25 threads for 25 different data sets appears to be much faster as
compared to sequential reading of data. The overall through put is increased.
Sequential Takes around 3 mins for the entire data set to be processed with required result.
On the other hand, parallel thread implementation takes around 1 min.
But, on the other hand its an overhead based on the limited infrastructure. (Hardware and memory capacity)

References ->
-Stackoverflow
-Tutorials point
- Online JAVA guide for use of Maps and sorting
-gradle.com
								