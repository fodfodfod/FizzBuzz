public class App {
    public static void main(String[] args) throws Exception {
        
        // for each number print out fizz if it is divisible by 3, buzz if it is divisible by 5, fizzbuzz if it is divisible by both 3 and 5, or the number itself otherwise
        for (int i = 1; i < 100; i ++){
            if (i % 3 == 0 && i % 5 == 0){
                System.out.println("FizzBuzz");
            }
            else if (i % 3 == 0){
                System.out.println("Fizz");
            }
            else if (i % 5 == 0){
                System.out.println("Buzz");
            }
            else {
                System.out.println(i);
            }
        }
    }
}
