public class FizzBuzz implements Runnable{
    private int start;
    private int end;
    private StringBuilder pString;
    public FizzBuzz(int start, int end, StringBuilder pString) {
        this.start = start;
        this.end = end;
        this.pString = pString;
    }
 
    @Override
    public void run() {
        // System.out.println(end);
        for (int i = start; i <= end; i ++){
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
        System.out.println("exit loop!");
        // System.out.print(pString);
    }
}
