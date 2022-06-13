package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayFour {

    private String numbers;

    private List<int[][]> getBoards() {
        List<int[][]> boards = new ArrayList<>();
        String line;

        int counter = 1;

        int row = 0;
        int column = 0;
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_4.txt"))) {

            this.numbers = objReader.readLine();

            int[][] board = new int[5][5];
            while ((line = objReader.readLine()) != null) {
                if (line.length() > 0) {
                    for (String number : line.split("\\s+")) {
                        if (isParsable(number)) {
                            board[row][column] = Integer.parseInt(number);
                            column++;
                        }
                    }
                    row++;
                    column = 0;

                    if (counter % 5 == 0) {
                        boards.add(board);
                        board = new int[5][5];
                        row = 0;
                    }
                    counter++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return boards;
    }

    private List<Integer> getDrawnNumbers() {
        List<Integer> drawnNumbers = new ArrayList<>();
        String line;

        line = numbers;
        for (String number : line.split(",")) {
            drawnNumbers.add(Integer.parseInt(number));
        }

        return drawnNumbers;
    }

    public int playBingo() {
        int lastNumber;
        List<int[][]> boards = getBoards();
        List<Integer> drawnNumber = getDrawnNumbers();
        boolean win = true;
        int sum = 0;

        for (int drawn : drawnNumber) {
            lastNumber = drawn;
            for (int[][] board : boards) {
                checkBoardField(lastNumber, board);
            }


            for (int[][] board : boards) {
                win = isWinBoard(board);
                if (win) {
                    sum = getSum(board, sum);
                    return sum * lastNumber;
                }
            }
            if (win) {
                break;
            }
        }

        return 0;
    }

    public int playReverseBingo() {
        int lastNumber;
        List<int[][]> boards = getBoards();
        List<Integer> drawnNumber = getDrawnNumbers();

        boolean win;
        int sum = 0;

        for (int drawn : drawnNumber) {
            lastNumber = drawn;
            for (int[][] board : boards) {
                checkBoardField(lastNumber, board);
            }


            for (Iterator<int[][]> iterator = boards.iterator(); iterator.hasNext(); ) {
                int[][] board = iterator.next();
                win = isWinBoard(board);
                if (boards.size() == 1) {
                    Optional<int[][]> last = boards.stream().findFirst();
                    if (last.isPresent()) {
                        int[][] lastBoard = last.get();
                        if (isWinBoard(lastBoard)) {
                            sum = getSum(lastBoard, sum);
                            return sum * lastNumber;
                        }
                    }
                }
                if (win) {
                    iterator.remove();
                }
            }

        }

        return 0;
    }

    private int getSum(int[][] board, int sum) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] != -1) {
                    sum += board[row][col];
                }
            }
        }
        return sum;
    }

    private void checkBoardField(int lastNumber, int[][] board) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] == lastNumber) {
                    board[row][col] = -1;
                }
            }
        }
    }

    private boolean isWinBoard(int[][] board) {
        boolean win;
        for (int row = 0; row < 5; row++) {
            win = true;
            for (int col = 0; col < 5; col++) {
                if (board[row][col] != -1) {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }
        for (int col = 0; col < 5; col++) {
            win = true;
            for (int row = 0; row < 5; row++) {
                if (board[row][col] != -1) {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }
        return false;
    }

    private static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
