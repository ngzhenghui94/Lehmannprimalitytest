//Lehmann Algorithm: To test whether a number p is a prime number.
//Choose a random number a being less than p. Calculate r = a^((p−1)/2) mod p.
//If r is not 1 or –1 then p is definitely not a prime.
//If r=1 or –1 the likelihood that p is not prime is at most than 50 percent.
//Repeat this algorithm t times, if the calculation equals to 1 or –1
//but does not always equal to 1, then p is probably prime with an error rate of 1 in (1/2^t).


import java.util.Random;
import java.util.ArrayList;


public class lehmanPrimalityTest {
    //store the integers we randomly generated to test.
    private static ArrayList<Integer> testIntegers = new ArrayList<Integer>();

    private static int randomGenerator (int p) {
        Random rand = new Random();
        //Bound is - 1 so that the randomInt we generate can +1 (to prevent out of bound)
        //This is so that the functon wont generate a zero to test the prime.
        //Of course, there are other ways to do it, but this works
        int randomInt = rand.nextInt(p-1);
        randomInt++;
        testIntegers.add(randomInt);
        return randomInt;
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // function to check Lehmann's test  
    private static boolean lehmann(int n, int tries) {
        testIntegers.clear();
        int rand = randomGenerator(n);
        // calculating the exponent
        int e = (n - 1) / 2;
        // cal the result
        int result = ((int)Math.pow(rand, e)) % n;

        // Loop to check n with rand if prime/composite.
        while (tries != 0) {
            // calculating final value using formula  
            result = ((int)Math.pow(rand, e)) % n;
            // check if gcd > 1 then is composite. return true (p != Prime)
            if (gcd(rand,n) > 1) {
                return true;
            } else if((result % n) != 1 || (result % n) != -1 || (result % n) != n-1) {
                rand = randomGenerator(n);
                tries -= 1;
            }else{
                return true;
            }
            if(tries == 0){
                return false; //If all tries pass, return false (p == Prime)
            }
        }
        return true;
    }
//    private static void test(){
//        for(int n = 2; n<20; n++){
//            testIntegers.clear();
//            boolean flag = lehmann(n, 10);
//            if (flag == false) {
//                System.out.println(n + " is Prime.");
//            }
//            else {
//                System.out.println(n + " is Composite.");
//            }
//        }
//    }


    public static void main(String[] args) {
        //test();
        String testValue = args[0];
        int n = Integer.parseInt(testValue); // number to be tested
        int tries = 10; // number of tries

        // if n == 2
        if (n <= 3 && n != 1){
            System.out.println(n + " is Prime.");
            System.exit(0);
        }else if (n == 1){
            System.out.println("invalid test case");
            System.exit(0);
        }
        //Removed. Not essential. Just let it run thru the lehman test.
//        // if even, it is composite
//        if (n % 2 == 0 && n != 2){
//            System.out.println(n + " is Composite. n%2 = 0");
//            System.exit(0);
//        }
        else {
            boolean flag = lehmann(n, tries);
            if (flag == false) {
                System.out.println(n + " is Prime.");
            }
            else {
                System.out.println(n + " is Composite.");
            }
        }
        System.out.println("Tested with: " + testIntegers);
    }
} 
  