package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        do {
            String[][] gameBoard = initializeGameBoard();
            HashMap<Integer, Integer> movesAlreadyUsed = new HashMap<>();
            boolean isPlayer1 = true;
            boolean isGameOver = false;
            int winner = 0; // 0 is default // 1 is player 1 // 2 is Player 2
            int round = 1;

            while (winner != 1 && winner != 2 && !isFull(gameBoard)) {
                clearScreen();
                displayGameInfo(isPlayer1, round);
                displayBoardWithPositionMarkers();
                displayCurrentBoard(gameBoard);
                movesAlreadyUsed.put(round, getUserMove(movesAlreadyUsed));
                int input = movesAlreadyUsed.get(round);
                placePiece(input, gameBoard, isPlayer1);
                isPlayer1 = !isPlayer1;
                winner = checkForWinner(gameBoard, isGameOver);
                round++;
            }

            clearScreen();
            displayCurrentBoard(gameBoard);
            displayGameOver(winner);
        } while(isPlayAgain());

        System.out.println("Exiting game...");
    }

    private static void displayGameInfo(boolean isPlayer1, int round) {

        if (isPlayer1) {
            System.out.println("---------------------------------------------------");
            System.out.println("----------------- ROUND: " + round + "         ----------------");
            System.out.println("----------------- Player " + 1 + " to move ----------------");
            System.out.println("----------------- Placing X        ----------------");
            System.out.println("---------------------------------------------------");
        } else {
            System.out.println("---------------------------------------------------");
            System.out.println("----------------- ROUND: " + round + "         ----------------");
            System.out.println("----------------- Player " + 2 + " to move ----------------");
            System.out.println("----------------- Placing O        ----------------");
            System.out.println("---------------------------------------------------");
        }
    }

    private static boolean isFull(String[][] array) {
        for (String[] row : array) {
            for (String str : row) {
                if (str.equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayGameOver(int winner) {
        if (winner == 1) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nCongratulations player 1, you are the winner!!!\n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else if (winner ==2 ){
            clearScreen();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nCongratulations player 2, you are the winner!!!\n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nOh no, you tied!\n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    private static int checkForWinner(String[][] gameBoard, boolean isGameOver) {
        if (gameBoard[0][0].equals("X") && gameBoard[0][1].equals("X") && gameBoard[0][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[1][0].equals("X") && gameBoard[1][1].equals("X") && gameBoard[1][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[2][0].equals("X") && gameBoard[2][1].equals("X") && gameBoard[2][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[0][0].equals("X") && gameBoard[1][0].equals("X") && gameBoard[2][0].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[0][1].equals("X") && gameBoard[1][1].equals("X") && gameBoard[2][1].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[0][2].equals("X") && gameBoard[1][2].equals("X") && gameBoard[2][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[0][0].equals("X") && gameBoard[1][1].equals("X") && gameBoard[2][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[2][0].equals("X") && gameBoard[2][2].equals("X") && gameBoard[0][2].equals("X")) {
            isGameOver = true;
            return 1;
        } else if (gameBoard[0][0].equals("O") && gameBoard[0][1].equals("O") && gameBoard[0][2].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[1][0].equals("O") && gameBoard[1][1].equals("O") && gameBoard[1][2].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[2][0].equals("O") && gameBoard[2][1].equals("O") && gameBoard[2][2].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[0][0].equals("O") && gameBoard[1][0].equals("O") && gameBoard[2][0].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[0][1].equals("O") && gameBoard[1][1].equals("O") && gameBoard[2][1].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[0][2].equals("O") && gameBoard[1][2].equals("O") && gameBoard[2][2].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[0][0].equals("O") && gameBoard[1][1].equals("O") && gameBoard[2][2].equals("O")) {
            isGameOver = true;
            return 2;
        } else if (gameBoard[2][0].equals("O") && gameBoard[2][2].equals("O") && gameBoard[0][2].equals("O")) {
            isGameOver = true;
            return 2;
        }

        return 0;
    }

    private static void displayCurrentBoard(String[][] gameBoard) {

        System.out.println("\n-------------------\nGame Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.println("\n-+-+-");
            }
        }
        System.out.println("\n-------------------\n");
    }

    private static void placePiece(int userInput, String[][] board, boolean isPlayer1) {
        int count = 1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (count == userInput) {
                    if (isPlayer1) {
                        board[i][j] = "X";
                        isPlayer1 = false;
                    } else {
                        board[i][j] = "O";
                        isPlayer1 = true;
                    }
                    return;
                }
                count++;
            }
        }
    }

    private static String[][] initializeGameBoard() {
        String[][] board = new String[3][3];

        for (String[] chars : board) {
            Arrays.fill(chars, " ");
        }
        return board;
    }

    private static void displayBoardWithPositionMarkers() {
        int count = 1;

        System.out.println("\n-------------------\nNumber Guide:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(count);
                if (j < 2) {
                    System.out.print("|");
                }
                count++;
            }
            if (i < 2) {
                System.out.println("\n-+-+-");
            }
        }
        System.out.println("\n-------------------\n");
    }

    private static int getUserMove(HashMap<Integer, Integer> movesAlreadyUsed) {
        Scanner scanner = new Scanner(System.in);

        // TODO: implement error handling for input. need to disallow numbers already used
        System.out.println("\n\nEnter a number [1-9] to select your placement");
        String userInput = scanner.next();
        if (!userInput.matches("[1-9]")) {
            System.out.println("\n\n-----Error, please enter a single digit [1-9]-----");
            return getUserMove(movesAlreadyUsed);
        }

        int inputAsNum = parseInt(userInput);
        if (movesAlreadyUsed.containsValue(inputAsNum)) {
            System.out.println("\n\n-----Error: That space has already been claimed. Please try again-----");
            return getUserMove(movesAlreadyUsed);
        }

        return inputAsNum;
    }

    private static boolean isPlayAgain() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n\n\n\n-----Would you like to play again? y/n-----");
        String answer = scanner.next();
        while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
            System.out.println("Please answer with a single letter 'y' or 'n'");
            isPlayAgain();
        }
        return answer.equalsIgnoreCase("y");
    }

    private static void clearScreen() {
        for (int clear = 0; clear < 1000; clear++) {
            System.out.println("\b");
        }
    }
}