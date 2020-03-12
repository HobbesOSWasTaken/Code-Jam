import java.math.BigInteger;
import java.util.*;

public class Solution2019_3 {
    static ArrayList<Integer> primes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();
        for(int t = 1; t <= numCases; t++) {
            String line1 = scanner.nextLine();
            String[] line1divided = line1.split(" ");
            BigInteger n = new BigInteger(line1divided[0]);
            BigInteger l = new BigInteger(line1divided[1]);
            generatePrimes(n.intValue());
            String line2 = scanner.nextLine();
            String[] values = line2.split(" ");
            ArrayList<Integer> cipherPrimes = new ArrayList<>();
            for (int index = 0; index < values.length; index++) {
                String[] primes = calculatePrimes(Integer.parseInt(values[index]));
                int prime1 = Integer.parseInt(primes[0]);
                int prime2 = Integer.parseInt(primes[1]);
                int whichPrime = 0;
                if (cipherPrimes.size() > 1) {
                    if (cipherPrimes.get(cipherPrimes.size() - 1).equals(prime1)) {
                        cipherPrimes.remove(cipherPrimes.size() - 1);
                        whichPrime = 1;
                    } else if (cipherPrimes.get(cipherPrimes.size() - 2).equals(prime1)) {
                        cipherPrimes.remove(cipherPrimes.size() - 2);
                        whichPrime = 1;
                    } else if (cipherPrimes.get(cipherPrimes.size() - 1).equals(prime2)) {
                        cipherPrimes.remove(cipherPrimes.size() - 1);
                        whichPrime = 2;
                    } else if (cipherPrimes.get(cipherPrimes.size() - 2).equals(prime2)) {
                        cipherPrimes.remove(cipherPrimes.size() - 2);
                        whichPrime = 2;
                    }
                } else {
                    whichPrime = 1;
                }
                if (whichPrime == 1) {
                    cipherPrimes.add(prime1);
                    cipherPrimes.add(prime2);
                } else if (whichPrime == 2) {
                    cipherPrimes.add(prime2);
                    cipherPrimes.add(prime1);
                }
            }
            // Print cipherPrimes
            System.out.println("Generated Primes: ");
            for (int i = 0; i < primes.size(); i++) {
                System.out.print(primes.get(i) + " ");
            }
            System.out.println("\nCipher Primes: ");
            for (int i = 0; i < cipherPrimes.size(); i++) {
                System.out.print(cipherPrimes.get(i) + " ");
            }

            ArrayList<Integer> usedPrimes = new ArrayList<>();
            Set<Integer> set = new LinkedHashSet<>(cipherPrimes);
            usedPrimes.addAll(set);
            // Sort Used Primes
            for (int i = 0; i < usedPrimes.size() - 1; i++) {
                for (int j = 0; j < usedPrimes.size() - i - 1; j++) {
                    if (usedPrimes.get(j) > usedPrimes.get(j + 1)) {
                        Collections.swap(usedPrimes, j, j + 1);
                    }
                }
            }

            System.out.println("\nUsed Primes: ");
            for (int i = 0; i < usedPrimes.size(); i++) {
                System.out.print(usedPrimes.get(i) + " ");
            }

            char[] characters = new char[cipherPrimes.size()];
            for(int i = 0; i < cipherPrimes.size(); i++) {
                for(int j = 0; j < usedPrimes.size(); j++) {
                    if(cipherPrimes.get(i).equals(usedPrimes.get(j))) {
                        characters[i] = (char) (j + 65);
                    }
                }
            }
            System.out.println("\n Case #" + t + ": " + String.valueOf(characters));
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void generatePrimes(int N) {
        for(int num = 3; num <= N; num++) {
            if(isPrime(num)) {
                primes.add(num);
            }
        }
    }

    /**public static String[] calculatePrimes(int product) {
        for(int i = 0; i < primes.size(); i++) {
            for(int j = 0; j < primes.size(); j++) {
                if(primes.get(i) * primes.get(j) == product) {
                    return new String[] {String.valueOf(primes.get(i)), String.valueOf(primes.get(j))};
                }
            }
        }
        return null;
    }**/

    public static String[] calculatePrimes(int product) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= product; i++) {
            while (product % i == 0) {
                factors.add(i);
                product /= i;
            }
        }
        Set<Integer> set = new LinkedHashSet<>(factors);
        factors.clear();
        factors.addAll(set);
        return new String[] {String.valueOf(factors.get(0)), String.valueOf(factors.get(1))};
    }
}
