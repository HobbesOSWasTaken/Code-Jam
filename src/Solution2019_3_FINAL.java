import java.math.BigInteger;
import java.util.*;

public class Solution2019_3_FINAL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();
        for(int t = 1; t <= numCases; t++) {
            String line1 = scanner.nextLine();
            String[] line1divided = line1.split(" ");
            BigInteger n = new BigInteger(line1divided[0]);
            BigInteger l = new BigInteger(line1divided[1]);
            String line2 = scanner.nextLine();
            String[] values = line2.split(" ");
            ArrayList<BigInteger> cipherPrimes = new ArrayList<>();
            for (int index = 0; index < values.length; index++) {
                String[] primes = calculatePrimes(new BigInteger(values[index]));
                BigInteger prime1 = new BigInteger(primes[0]);
                BigInteger prime2 = new BigInteger(primes[1]);
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

            Set<BigInteger> set = new LinkedHashSet<>(cipherPrimes);
            ArrayList<BigInteger> usedPrimes = new ArrayList<>(set);
            // Sort Used Primes
            for (int i = 0; i < usedPrimes.size() - 1; i++) {
                for (int j = 0; j < usedPrimes.size() - i - 1; j++) {
                    if (usedPrimes.get(j).longValue() > usedPrimes.get(j + 1).longValue()) {
                        Collections.swap(usedPrimes, j, j + 1);
                    }
                }
            }

            char[] characters = new char[cipherPrimes.size()];
            for(int i = 0; i < cipherPrimes.size(); i++) {
                for(int j = 0; j < usedPrimes.size(); j++) {
                    if(cipherPrimes.get(i).equals(usedPrimes.get(j))) {
                        characters[i] = (char) (j + 65);
                    }
                }
            }
            System.out.println("Case #" + t + ": " + String.valueOf(characters));
        }
        System.exit(0);
    }

    public static String[] calculatePrimes(BigInteger product) {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        long productLoop = product.longValue();
        for (int i = 2; i <= productLoop; i++) {
            while (productLoop % i == 0) {
                factors.add(i);
                productLoop /= i;
            }
        }
        Set<Integer> set = new LinkedHashSet<>(factors);
        factors.clear();
        factors.addAll(set);
        return new String[] {String.valueOf(factors.get(0)), String.valueOf(factors.get(1))};
    }
}
