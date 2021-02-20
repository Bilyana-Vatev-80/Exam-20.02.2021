import java.util.Scanner;

public class Bomb {
    private static int bombRow = 0;
    private static int bombCol = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",\\s*");
        char[][] matrix = readMatrix(scanner, size);
        int bombs = findBomb(matrix);
        for (String command : commands) {
            matrix[bombRow][bombCol] = '+';
            switch (command) {
                case "up":
                    bombRow--;
                    if (bombRow < 0) {

                        bombRow++;
                    }
                    break;
                case "down":

                    bombRow++;
                    if ( bombRow >= matrix.length) {
                        bombRow--;
                    }
                    break;
                case "left":
                    bombCol--;
                    if (bombCol < 0) {
                        bombCol++;
                    }
                    break;
                case "right":
                    bombCol++;
                    if (bombCol >= matrix.length) {
                        bombCol--;
                    }
                    break;
            }
            if (matrix[bombRow][bombCol] == 'e') {
                System.out.printf("END! %d bombs left on the field%n", bombs);
                return;
            } else if (matrix[bombRow][bombCol] == 'B') {
                System.out.println("You found a bomb!");
                bombs--;
            }
            matrix[bombRow][bombCol] = 's';
            if (bombs == 0) {
                break;
            }
        }
        if (bombs > 0) {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)%n", bombs, bombRow, bombCol);
        } else {
            System.out.println("Congratulations! You found all bombs!");
        }
    }

    public static int findBomb(char[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'B') {
                    count++;
                }
            }
        }
        return count;
    }

    private static char[][] readMatrix(Scanner scan, int size) {
        char[][] matrix = new char[size][size];
        for (int i = 0; i < matrix.length; i++) {
            String input = scan.nextLine();
            input = input.replaceAll("\\s+", "");
            matrix[i] = input.toCharArray();
            if (input.contains("s")) {
                bombRow = i;
                bombCol = input.indexOf("s");
            }
        }
        return matrix;
    }
}
