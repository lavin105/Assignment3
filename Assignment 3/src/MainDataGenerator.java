public class MainDataGenerator {

    public static  void main(String args[]){

            if (args.length == 0) {
                System.out.println("Missing the necessary arguments.");
                System.out.println("Requires name of file and number of tuples.");
                System.exit(0);
            } else {
                String file = args[0];
                int tuple = Integer.parseInt(args[1]);
                Generator gen=new Generator(file, tuple);
                gen.generateData();
            }
    }




}
