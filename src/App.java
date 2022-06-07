import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
 
public class App {
   
        public static void main(String[] args) throws Exception {
        
        /**max number of threads allowed to exist at one time(not including main thread) */
        final int threadCount = 10;
        final int range = 100000000;
        final int chunkSize = 100000;

        //get the current time
        long startTime = System.currentTimeMillis();
       


        //reset the file
        File tempFile = new File("output.txt");
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
        //join the threads to main thread
        for (int i = 0; i < threads.length; i++){
            threads[i].join();
            threadsRunning--;
            System.out.println("Thread " + i + " finished");

            
            //create a new thread if there are still threads to create
            if (threadsRunning < threadCount && threadsCreated < threads.length){
                threadStrings[threadsCreated] = new StringBuilder();
                threads[threadsCreated] = new Thread(new FizzBuzz(threadsCreated * chunkSize, (threadsCreated + 1) * chunkSize, threadStrings[threadsCreated]));
                //start the new thread
                threads[threadsCreated].start();
                threadsCreated++;
                threadsRunning++;
            }


            
            //append the stringbuilder to the file
            File file = new File("output.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(threadStrings[i].toString());
            bw.close();
            

            
            
        }
        System.out.println("All threads joined");
 
        //combine the strings and print them out
        // String pString = "";
        // for (int i = 0; i < threadCount; i++){
        //     pString += threadStrings[i].toString();
        // }
        // System.out.print(t1String);
        // System.out.print(pString);
 
        //print out the time it took to run
       
 
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
