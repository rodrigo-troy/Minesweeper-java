package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many mines do you want on the field?\n");
        int minesCount = scanner.nextInt();
        int rows = 9;
        int columns = 9;

        char[][] matrix = new char[rows][columns];

        prepareMatrix(matrix,
                      minesCount);
        countMinesPerCell(matrix);
        printMatrix(matrix);

        while (true) {
            System.out.println("Set/unset mines marks or claim a cell as free:\n");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String action = scanner.next();

            x--;
            y--;

            if (matrix[y][x] != '.' &&
                matrix[y][x] != 'X' &&
                matrix[y][x] != 'O') {
                System.out.println("There is a number here!");
                printMatrix(matrix);
            } else if (matrix[y][x] == 'X') {
                matrix[y][x] = '*';
                minesCount--;
                printMatrix(matrix);
            } else if (matrix[y][x] == '.') {
                matrix[y][x] = 'O';
                minesCount++;
                printMatrix(matrix);
            } else if (matrix[y][x] == 'O') {
                matrix[y][x] = '.';
                minesCount--;
                printMatrix(matrix);
            } else {
                printMatrix(matrix);
            }


            if (minesCount == 0) {
                System.out.println("Congratulations! You found all the mines!");
                return;
            }
        }
    }

    private static void countMinesPerCell(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {

                if (matrix[r][c] == 'X') {
                    incrementCellValue(matrix,
                                       r - 1,
                                       c - 1);
                    incrementCellValue(matrix,
                                       r - 1,
                                       c);
                    incrementCellValue(matrix,
                                       r - 1,
                                       c + 1);
                    incrementCellValue(matrix,
                                       r,
                                       c - 1);
                    incrementCellValue(matrix,
                                       r,
                                       c + 1);
                    incrementCellValue(matrix,
                                       r + 1,
                                       c - 1);
                    incrementCellValue(matrix,
                                       r + 1,
                                       c);
                    incrementCellValue(matrix,
                                       r + 1,
                                       c + 1);
                }

            }
        }
    }

    private static void prepareMatrix(char[][] matrix,
                                      int minesCount) {
        for (char[] chars : matrix) {
            for (int c = 0; c < matrix[0].length; c++) {
                chars[c] = '.';
            }
        }

        if (minesCount > matrix.length * matrix[0].length) {
            minesCount = matrix.length * matrix[0].length;
        }

        while (minesCount != 0) {
            int rowIndex = (int) (Math.random() * matrix.length);
            int colIndex = (int) (Math.random() * matrix.length);

            if (matrix[rowIndex][colIndex] == '.') {
                matrix[rowIndex][colIndex] = 'X';
                minesCount--;
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        System.out.print(" |");
        for (int c = 0; c < matrix[0].length; c++) {
            System.out.printf("%d",
                              c + 1);
        }
        System.out.println("|");

        System.out.print("-|");
        for (int c = 0; c < matrix[0].length; c++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int r = 0; r < matrix.length; r++) {
            System.out.printf("%d|",
                              r + 1);

            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 'X') {
                    System.out.print('.');
                } else if (matrix[r][c] == 'O') {
                    System.out.print('*');
                } else {
                    System.out.print(matrix[r][c]);
                }
            }

            System.out.println("|");
        }

        System.out.print("-|");
        for (int c = 0; c < matrix[0].length; c++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private static void incrementCellValue(char[][] matrix,
                                           int rowIndex,
                                           int colIndex) {
        if (rowIndex < 0 ||
            colIndex < 0 ||
            rowIndex == matrix.length ||
            colIndex == matrix[0].length) {
            return;
        }

        switch (matrix[rowIndex][colIndex]) {
            case 'X':
                break;
            case '.':
                matrix[rowIndex][colIndex] = '1';
                break;
            default:
                int i = Integer.parseInt(String.valueOf(matrix[rowIndex][colIndex])) + 1;
                matrix[rowIndex][colIndex] = Character.forDigit(i,
                                                                10);
                break;
        }
    }
}
