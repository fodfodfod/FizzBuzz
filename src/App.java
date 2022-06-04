import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {
    
        public static void main(String[] args) throws Exception {
        //get the current time
        long startTime = System.currentTimeMillis();
        
        StringBuilder t2String = new StringBuilder();
        StringBuilder t3String = new StringBuilder();
        StringBuilder t4String = new StringBuilder();
        StringBuilder t1String = new StringBuilder();
        //create 4 threads
        Thread t1 = new Thread(new FizzBuzz(1, 25000, t1String));
        Thread t2 = new Thread(new FizzBuzz(25001, 50000, t2String));
        Thread t3 = new Thread(new FizzBuzz(50001, 75000, t3String));
        Thread t4 = new Thread(new FizzBuzz(75001, 100000, t4String));
        //start the threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        //join the threads to main thread
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        

        //combine the strings and print them out
        String pString = t1String.toString() + t2String.toString() + t3String.toString() + t4String.toString();
        // System.out.print(t1String);
        // System.out.print(pString);

        //print out the time it took to run
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        //write the output to a file called output.txt
        File file = new File("output.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(pString);
        bw.close();
        
    }

}
