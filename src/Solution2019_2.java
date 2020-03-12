import java.util.Scanner;

public class Solution2019_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for(int t = 1; t <= numCases; t++) {
            int dimensions = scanner.nextInt();
            scanner.nextLine();
            String directions = scanner.nextLine();
            char[] directionsChar = directions.toCharArray();
            char[] solution = new char[directionsChar.length];
            for(int index = 0; index < directionsChar.length; index++) {
                if(directionsChar[index] == 'S') {
                    solution[index] = 'E';
                }
                else if(directionsChar[index] == 'E') {
                    solution[index] = 'S';
                }
            }
            System.out.println("Case #" + t + ": " + String.valueOf(solution));
        }
        scanner.close();
        System.exit(0);
    }
}
