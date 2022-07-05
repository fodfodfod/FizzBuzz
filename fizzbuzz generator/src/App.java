// .jar command: java -Xmx12g -jar FizzBuzz.jar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
 
public class App {
   
        public static void main(String[] args) throws Exception {
        /** max number of threads allowed to exist at one time(not including main thread) */
        final int threadCount = 10;
        final long range = (long) 5 * 1000000000;
        final long chunkSize = 100000;
        final String fileName = "/Volumes/NO NAME/output-" + range + ".txt";
        // final String fileName = "output/output-" + range + ".txt";

        /**
         * ? is the final breaking things
         * number of threads to be created total
         * (not including main thread)
        */
        final int numThreads = (int) Math.ceil((double) range / chunkSize);
        //get the current time
        long startTime = System.currentTimeMillis();
       
       


        //reset the file
        File tempFile = new File(fileName);
        tempFile.delete();
        LinkedList<StringBuilder> threadStrings = new LinkedList<>();
        

        int threadsCreated = 0;
        int threadsRunning = 0;
        int bytesWritten = 0;
        //create threads
        LinkedList<Thread> threads = new LinkedList<>();
        
        
        System.out.println("Initial threads started");
    
        //create the file to appened to
        File outputFile = new File(fileName);
        FileWriter fw = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fw);


        for (int i = 0; i < numThreads; i++){
            //create a variable to mesure the time spent waiting for the thread to finish
            // long startTime2 = System.currentTimeMillis();
            while (threadsRunning < threadCount && threadsCreated < numThreads){
                // System.out.println("new thread being created");
                threadStrings.add(new StringBuilder());
                threads.add(new Thread(new FizzBuzz(threadsCreated * chunkSize, ((threadsCreated + 1) * chunkSize) -1, threadStrings.getLast())));
                //start the new thread
                threads.getLast().start();
                threadsCreated++;
                threadsRunning++;
            }
            
            threads.get(0).join();
            // long endTime2 = System.currentTimeMillis();
            // System.out.println("Thread " + i + " finished in " + (endTime2 - startTime2) + " milliseconds");


            threadsRunning--;
            

            //create a variable to store the time creating a new thread
            // long startTime3 = System.currentTimeMillis();
            //create a new thread if there are still threads to create
            
            // long endTime3 = System.currentTimeMillis();
            // System.out.println("Thread " + (threadsCreated - 1) + " created in " + (endTime3 - startTime3) + " milliseconds");

            
            //create a variable to store the time writing to the file
            // long startTime4 = System.currentTimeMillis();

            //use a try block to catch any exceptions
            try {
                //write the thread's string to the file
                bw.write(threadStrings.get(0).toString());
                bytesWritten += threadStrings.get(0).length();
                // System.out.println("Thread " + (threadsCreated - 1) + " wrote to file");
            } catch (Exception e){
                System.out.println("Error writing to file: " + e);
                System.out.println("the last number was: " + (threadsCreated - 1) * chunkSize);
                bw.close();
                fw.close();
                break;

            }
            
            
            // long endTime4 = System.currentTimeMillis();
            // System.out.println("Thread " + i + " wrote to file in " + (endTime4 - startTime4) + " milliseconds");
            
            if(i%100==0){
                System.out.println("things are happening");
                System.out.println("the last number was: " + (threadsCreated - 1) * chunkSize);
                System.out.println("the number of bytes written is: " + bytesWritten);
            }
            //set the string builder to an empty string
            // threadStrings.set(0, threadStrings.getFirst().delete(0, threadStrings.getFirst().length()));
            //delete the string builder
            threadStrings.remove();
            //delete the thread
            threads.remove();
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
