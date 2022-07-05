public class FizzBuzz implements Runnable{
    private long start;
    private long end;
    private StringBuilder pString;
    public FizzBuzz(long start, long end, StringBuilder pString) {
        this.start = start;
        this.end = end;
        this.pString = pString;
    }
 
    @Override
    public void run() {
        // System.out.println(end);
        for (long i = start; i <= end; i ++){
            String numberString = "";
            if (i % 3 == 0){
                numberString += "Fizz";
            }
            if (i % 5 == 0){
                numberString += "Buzz";
            }
            if (numberString.equals("")){
                numberString += i;
            }
           
            pString.append(numberString + "\n");
            // System.out.println("in loop!");

        }
        pString = null;
        // System.out.print(pString);
    }
}
