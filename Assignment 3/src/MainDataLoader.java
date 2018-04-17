import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDataLoader {
     static Connection con;
    public static void main (String args[]) {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment3?useSSL=false", "lavin105", "Lavin105@m.c.edu");
            if(args.length==0){
                System.out.println("Please provide the file path as a command line argument");
            }else{
                //take the file as a command line argument
                String file = args[0];
                //create an object loadData which has a file and a connection
                TableLoader loadData=new TableLoader(file, con);
                System.out.println("Inserting data to the database.....");
                System.out.println("");
                //insert the data into the tables of the database
                loadData.loadAll();
                System.out.println("Insertion Complete");

            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException f) {
            f.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

}


