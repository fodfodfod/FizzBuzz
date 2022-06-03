public class App {
    public static void main(String[] args) throws Exception {
        //get the current time
        long startTime = System.currentTimeMillis();
        String pString = "";
        // for each number print out fizz if it is divisible by 3, buzz if it is divisible by 5, fizzbuzz if it is divisible by both 3 and 5, or the number itself otherwise
        for (int i = 1; i < 10000; i ++){
            if (i % 3 == 0 && i % 5 == 0){
                pString += "FizzBuzz\n";
            }
            else if (i % 3 == 0){
                pString += "Fizz\n";
            }
            else if (i % 5 == 0){
                pString += "Buzz\n";
            }
            else {
                pString += i + "\n";
            }
        }
        System.out.println(pString);

        //print out the time it took to run
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }
}
