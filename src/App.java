import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {
    
        public static void main(String[] args) throws Exception {
        final int threadCount = 10;
        final int range = 10000000;
        //get the current time
        long startTime = System.currentTimeMillis();
        
        StringBuilder[] threadStrings = new StringBuilder[threadCount];
        //create 4 threads
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++){
            threadStrings[i] = new StringBuilder();
            threads[i] = new Thread(new FizzBuzz(i * range / threadCount + 1, (i + 1) * range / threadCount, threadStrings[i]));
        }
        //start the threads
        for (int i = 0; i < threadCount; i++){
            threads[i].start();
        }
        //join the threads to main thread
        for (int i = 0; i < threadCount; i++){
            threads[i].join();
        }
        

        //combine the strings and print them out
        String pString = "";
        for (int i = 0; i < threadCount; i++){
            pString += threadStrings[i].toString();
        }
        // System.out.print(t1String);
        // System.out.print(pString);

        //print out the time it took to run
        

        //write the output to a file called output.txt
        File file = new File("output.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(pString);
        bw.close();
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }

}
