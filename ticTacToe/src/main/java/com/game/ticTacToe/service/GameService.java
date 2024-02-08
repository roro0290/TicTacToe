package com.game.ticTacToe.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.game.ticTacToe.service.GameConstants.PLAYER;

@Component
public class GameService {

    static List<Integer> playerPositions = new ArrayList<>();
    static List<Integer> cpuPositions = new ArrayList<>();

    List<List<Integer>> winnerList = new ArrayList<>();

    public GameService() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> topCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> bottomCol = Arrays.asList(3, 6, 9);
        List<Integer> crossOne = Arrays.asList(1, 5, 9);
        List<Integer> crossTwo = Arrays.asList(3, 5, 7);

        winnerList.add(topRow);
        winnerList.add(midRow);
        winnerList.add(bottomRow);
        winnerList.add(topCol);
        winnerList.add(midCol);
        winnerList.add(bottomCol);
        winnerList.add(crossOne);
        winnerList.add(crossTwo);
    }

    /*
     ** depending on the input, we will change the space to X (player) or O (computer)
     */
    public char[][] createGameBoard() {
        return new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public int getPositionNum(String row, String col) {
        int position = 0, rowNum = Integer.parseInt(row), colNum = Integer.parseInt(col);

        if (rowNum == 0) {
            if (colNum == 0) position = 1;
            else if (colNum == 1) position = 2;
            else if (colNum == 2) position = 3;
        } else if (rowNum == 1) {
            if (colNum == 0) position = 4;
            if (colNum == 1) position = 5;
            if (colNum == 2) position = 6;
        } else if (rowNum == 2) {
            if (colNum == 0) position = 7;
            if (colNum == 1) position = 8;
            if (colNum == 2) position = 9;
        }

        return position;
    }

    public int getRowNum(int positionNum) {
        if (positionNum == 1 || positionNum == 2 || positionNum == 3) {
            return 0;
        } else if (positionNum == 4 || positionNum == 5 || positionNum == 6) {
            return 1;
        } else {
            return 2;
        }
    }

    public int getColNum(int positionNum) {
        if (positionNum == 1 || positionNum == 4 || positionNum == 7) {
            return 0;
        } else if (positionNum == 2 || positionNum == 5 || positionNum == 8) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean isPositionTaken(int position) {
        return playerPositions.contains(position) || cpuPositions.contains(position);
    }

    /*
     ** 2D array is [row][column]
     */
    public void placePiece(int num, String user) {
        if (user.equals(PLAYER)) {
            playerPositions.add(num);
        } else {
            cpuPositions.add(num);
        }
    }

    public void resetGamePositions() {
        playerPositions.clear();
        cpuPositions.clear();
    }

    public boolean isPlayerWinner() {
        for (List<Integer> l : winnerList) {
            if (playerPositions.containsAll(l)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCpuWinner() {
        for (List<Integer> l : winnerList) {
            if (cpuPositions.containsAll(l)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameATie() {
        return playerPositions.size() + cpuPositions.size() == 9;
    }


}
