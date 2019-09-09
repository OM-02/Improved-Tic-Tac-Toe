import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.StringBuilder;

class Tac {

    static Scanner keyboard = new Scanner(System.in);

    static char[][] board = new char[3][3];
    static String[] line = new String[8];
    static int rowSelect;
    static int columnSelect;
    static char player;

    static StringBuilder row1 = new StringBuilder("|   |   |   |");
    static StringBuilder row2 = new StringBuilder("|   |   |   |");
    static StringBuilder row3 = new StringBuilder("|   |   |   |");

    static String divider = "+---+---+---+";

    static void placer() {
        boolean validInputs = false;

        while (!validInputs) {
            try{
                System.out.print("Choose a row: ");
                rowSelect = keyboard.nextInt();
                if (rowSelect > 2) throw new InputMismatchException("That's not a valid row, try again.");

                System.out.print("Choose a column: ");
                columnSelect = keyboard.nextInt();
                if (columnSelect > 2) throw new InputMismatchException("That's not a valid column, try again.");

                if (board[rowSelect][columnSelect] != 0) throw new InputMismatchException("That space is already taken, try again.");

                validInputs = true;
                board[rowSelect][columnSelect] = player;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        switch (rowSelect) {
            case 0:
            switch (columnSelect) {
                case 0:
                row1.setCharAt(2, player);
                break;
                case 1:
                row1.setCharAt(6, player);
                break;
                case 2:
                row1.setCharAt(10, player);
            }
            break;
            case 1:
            switch (columnSelect) {
                case 0:
                row2.setCharAt(2, player);
                break;
                case 1:
                row2.setCharAt(6, player);
                break;
                case 2:
                row2.setCharAt(10, player);
            }
            break;
            case 2:
            switch (columnSelect) {
                case 0:
                row3.setCharAt(2, player);
                break;
                case 1:
                row3.setCharAt(6, player);
                break;
                case 2:
                row3.setCharAt(10, player);
            }
            break;
        }
    }

    static char checkWinner() {
        char win = 'Z';
        String temp = "";
        line[0] = temp + board[0][0] + board[0][1] + board[0][2];
        line[1] = temp + board[1][0] + board[1][1] + board[1][2];
        line[2] = temp + board[2][0] + board[2][1] + board[2][2];
        line[3] = temp + board[0][0] + board[1][0] + board[2][0];
        line[4] = temp + board[0][1] + board[1][1] + board[2][1];
        line[5] = temp + board[0][2] + board[1][2] + board[2][2];
        line[6] = temp + board[0][0] + board[1][1] + board[2][2];
        line[7] = temp + board[0][2] + board[1][1] + board[2][0];

        for (int i = 0; i <= 7; i++) {
            if (line[i].equals("XXX")) {
                win = 'X';
            } else if (line[i].equals("OOO")) {
                win = 'O';
            }
        }
        return win;
    }

    static void boardPrinter() {
        System.out.println(divider);
        System.out.println(row1);
        System.out.println(divider);
        System.out.println(row2);
        System.out.println(divider);
        System.out.println(row3);
        System.out.println(divider);
        System.out.println("");
    }

    public static void main(String[] args) {
        boardPrinter();
        int turn = 1;
        boolean gameWon = false;
        while (!gameWon) {
            player = (turn % 2 == 0) ? 'X' : 'O';
            turn++;

            System.out.println("Player: " + player);
            placer();
            System.out.println("");
            checkWinner();

            boardPrinter();

            if (checkWinner() == 'X') {
                System.out.println("X wins!");
                gameWon = true;
            } else if (checkWinner() == 'O') {
                System.out.println("O wins!");
                gameWon = true;
            } else if (turn == 10) {
                System.out.println("It's a tie!");
                gameWon = true;
            }
        }
    }
}