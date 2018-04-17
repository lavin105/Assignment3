import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableLoader {
    //variable declarations
     private String filePath;
     private Connection con;
     private int Person_ID;
    //TableLoader Constructor
    public TableLoader(String _filePath, Connection _con){
        filePath=_filePath;
        con=_con;
    }



    public void loadAll(){
        //SQL queries to be used
        String sql="INSERT INTO Person(FirstName, LastName) VALUES(?,?)";
        String sql2="INSERT INTO Credentials(Person_ID,SSN, Email) VALUES(?,?,?)";
        String sql3="INSERT INTO Work(Person_ID,BusinessName,BusinessAddress,BusinessCity) VALUES(?,?,?,?)";
        String sql4="INSERT INTO Residence(Person_ID,Address,City) VALUES(?,?,?)";
        String sql5="INSERT INTO Hobby(Person_ID,Activity) VALUES(?,?)";


        try {
            //use a csv reader
            Reader in=new FileReader(filePath);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            int count=0;
            //for each records in the file insert the data into particular tables
            for (CSVRecord record:records) {
                //ignore the first line because it is column attributes
                if (count>0){
                    //inserts into the person table
                    String firstName = record.get(0);
                    String lastName = record.get(1);
                    PreparedStatement ps=con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, firstName);
                    ps.setString(2, lastName);
                    ps.executeUpdate();
                    //Resultset for getting the generated keys
                    ResultSet rs_key = ps.getGeneratedKeys();
                    if (rs_key.next()) {
                        Person_ID = rs_key.getInt(1);
                    }

                    //inserts into the credentials table
                    Long SSN = Long.parseLong(record.get(2));
                    String Email = record.get(3);
                    PreparedStatement ps2=con.prepareStatement(sql2);
                    ps2.setInt(1, Person_ID);
                    ps2.setLong(2, SSN);
                    ps2.setString(3, Email);
                    ps2.executeUpdate();

                    //inserts into the work table
                    String BusinessName = record.get(4);
                    String BusinessAddress = record.get(5);
                    String BusinessCity = record.get(6);
                    PreparedStatement ps3=con.prepareStatement(sql3);
                    ps3.setInt(1, Person_ID);
                    ps3.setString(2, BusinessName);
                    ps3.setString(3, BusinessAddress);
                    ps3.setString(4, BusinessCity);
                    ps3.executeUpdate();

                    //inserts into the residence table
                    String Address = record.get(7);
                    String City = record.get(8);
                    PreparedStatement ps4=con.prepareStatement(sql4);
                    ps4.setInt(1, Person_ID);
                    ps4.setString(2, Address);
                    ps4.setString(3, City);
                    ps4.executeUpdate();

                    //inserts into the hobby table
                    String hobby = record.get(9);
                    PreparedStatement ps5=con.prepareStatement(sql5);
                    ps5.setInt(1, Person_ID);
                    ps5.setString(2, hobby);
                    ps5.executeUpdate();


                }
                //increment the count to know when the first line has been skipped
                count++;
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
