Name: Brandon Lavinsky
Email: lavin105@mail.chapman.edu
ID: 2255914

Running instruction:

In order to generate the data run MainDataGenerator
MainDataGenerator takes two parameters as command line
arguments first the file name/location and the second parameter is the number tuples to be generated
Make sure to edit the run configuration to have a csv file and then the number of tuples to be generated

In order to load the data into the database run MainDataLoader
MainDataLoader takes 1 command line argument which is the name/location
of the file that you want to have loaded
Make sure to edit the run configurations and pass in the csv file with data in it to load into eh database


Important information:

For this project I used 3 external libraries from maven
these libraries include:

Mysql connector
mysql:mysql-connector-java:5.1.45

Apache CSV Reader
org.apache.commons:commons-csv:1.0

Datafactory
org.fluttercode.datafactory:datafactory:0.8