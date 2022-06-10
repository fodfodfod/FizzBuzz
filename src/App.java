// .jar command: java -Xmx12g -jar FizzBuzz.jar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
 
public class App {
   
        public static void main(String[] args) throws Exception {
        
        /**max number of threads allowed to exist at one time(not including main thread) */
        final int threadCount = 10;
        final int range = 1100000000;
        final int chunkSize = 100000;

        //get the current time
        long startTime = System.currentTimeMillis();
       


        //reset the file
        File tempFile = new File("output-" + range + ".txt");
        tempFile.delete();
        StringBuilder[] threadStrings = new StringBuilder[(range/chunkSize) + 1];
        

        int threadsCreated = 0;
        int threadsRunning = 0;
        //create 4 threads
        Thread[] threads = new Thread[(range/chunkSize) + 1];
        while ( threadsCreated < threadCount){
 
            threadStrings[threadsCreated] = new StringBuilder();
 
            threads[threadsCreated] = new Thread(new FizzBuzz(threadsCreated * range / threadCount + 1, (threadsCreated + 1) * range / threadCount, threadStrings[threadsCreated]));
            threads[threadsCreated].start();
            threadsCreated++;
            threadsRunning++;
        }
        
        System.out.println("Initial threads started");
    
        //create the file to appened to
        File outputFile = new File("output-" + range + ".txt");
        FileWriter fw = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fw);


        for (int i = 0; i < threads.length; i++){
            //create a variable to mesure the time spent waiting for the thread to finish
            // long startTime2 = System.currentTimeMillis();
            threads[i].join();
            // long endTime2 = System.currentTimeMillis();
            // System.out.println("Thread " + i + " finished in " + (endTime2 - startTime2) + " milliseconds");


            threadsRunning--;
            

            //create a variable to store the time creating a new thread
            // long startTime3 = System.currentTimeMillis();
            //create a new thread if there are still threads to create
            if (threadsRunning < threadCount && threadsCreated < threads.length){
                threadStrings[threadsCreated] = new StringBuilder();
                threads[threadsCreated] = new Thread(new FizzBuzz(threadsCreated * chunkSize, (threadsCreated + 1) * chunkSize, threadStrings[threadsCreated]));
                //start the new thread
                threads[threadsCreated].start();
                threadsCreated++;
                threadsRunning++;
            }
            // long endTime3 = System.currentTimeMillis();
            // System.out.println("Thread " + (threadsCreated - 1) + " created in " + (endTime3 - startTime3) + " milliseconds");

            
            //create a variable to store the time writing to the file
            // long startTime4 = System.currentTimeMillis();

            //append the stringbuilder to the file
            bw.write(threadStrings[i].toString());
            
            
            // long endTime4 = System.currentTimeMillis();
            // System.out.println("Thread " + i + " wrote to file in " + (endTime4 - startTime4) + " milliseconds");

            if(i%100==0){
                System.out.println("things are happening");
            }
            
            //delete the string builder
            threadStrings[i] = null;
            //delete the thread
            threads[i] = null;
        }
        bw.close();
        System.out.println("All threads joined");
 
        //combine the strings and print them out
        // String pString = "";
        // for (int i = 0; i < threadCount; i++){
        //     pString += threadStrings[i].toString();
        // }
        // System.out.print(t1String);
        // System.out.print(pString);
 
        
       
 
        //write the output to a file called output.txt
        // File file = new File("output.txt");
        // FileWriter fw = new FileWriter(file);
        // BufferedWriter bw = new BufferedWriter(fw);
        // bw.write(pString);
        // bw.close();
       
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }
 
}
