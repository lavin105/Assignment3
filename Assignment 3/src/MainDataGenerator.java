public class MainDataGenerator {

    public static  void main(String args[]){
            //if no command line arguments given let the user know
            if (args.length == 0) {
                System.out.println("Missing the necessary arguments.");
                System.out.println("Requires name of file and number of tuples.");
                System.exit(0);
            } else {
                //take the first argument as the file name and the second argument is the number of tuples
                String file = args[0];
                int tuple = Integer.parseInt(args[1]);
                //create an object gen with a file and tuple
                Generator gen=new Generator(file, tuple);
                //call the generateData() method to create the file and data
                gen.generateData();
                System.out.println("Data has been added to the file "+ file);
            }
    }




}
