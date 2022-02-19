package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many mines do you want on the field?\n");
        int minesCount = scanner.nextInt();
        int rows = 9;
        int columns = 9;

        Minefield minefield = new Minefield(minesCount,
                                            columns,
                                            rows);
        //minefield.printRealMatrix();
        minefield.printMatrix();

        while (true) {
            System.out.println("Set/unset mines marks or claim a cell as free:\n");
            int columnIndex = scanner.nextInt();
            int rowIndex = scanner.nextInt();
            String action = scanner.next();

            columnIndex--;
            rowIndex--;

            if (action.equalsIgnoreCase("free")) {
                if (minefield.isBomb(rowIndex,
                                     columnIndex)) {
                    System.out.println("You stepped on a mine and failed!");
                    minefield.printRealMatrix();
                    return;
                } else {
                    minefield.exploreCell(rowIndex,
                                          columnIndex);
                }
            } else if (action.equalsIgnoreCase("mine")) {
                minefield.markCell(rowIndex,
                                   columnIndex);
            }

            if (minefield.checkWinner()) {
                System.out.println("Congratulations! You found all the mines!");
                minefield.printRealMatrix();
                return;
            }

            minefield.printMatrix();
        }
    }

}
