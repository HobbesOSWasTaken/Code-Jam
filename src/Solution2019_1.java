import java.math.BigInteger;
import java.util.Scanner;

public class Solution2019_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for (int t = 1; t <= numCases; t++) {
            String num = "0";
            try {
                num = String.valueOf(scanner.nextBigInteger());
            } catch(Exception e) {}
            char[] numChar = num.toCharArray();
            char[] solutionChar = new char[numChar.length];
            for(int index = 0; index < numChar.length; index++) {
                if(numChar[index] == '4') {
                    solutionChar[index] = '1';
                    numChar[index] = (char)((int)numChar[index] - 1);
                }
                else {
                    solutionChar[index] = '0';
                }
            }
            System.out.println("Case #" + t + ": " + String.valueOf(numChar) + " " + String.valueOf(solutionChar));
        }
        scanner.close();
        System.exit(0);
    }
}
