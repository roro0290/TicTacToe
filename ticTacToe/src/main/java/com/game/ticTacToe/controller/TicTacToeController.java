package com.game.ticTacToe.controller;

import com.game.ticTacToe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.game.ticTacToe.service.GameConstants.*;

@Controller
public class TicTacToeController {

    @Autowired
    GameService gameService;

    @GetMapping
    public ModelAndView index() {
        return ticTacToe();
    }

    /**
     * Renders the Tic-Tac-Toe game page with an empty board.
     *
     * @return the model and view for the Tic-Tac-Toe game page
     */
    @RequestMapping("/index")
    public ModelAndView ticTacToe() {
        ModelAndView modelAndView = new ModelAndView("index");
        char[][] board = gameService.createGameBoard();
        modelAndView.addObject("board", board);
        return modelAndView;
    }

    @PostMapping("/playerMove")
    @ResponseBody
    public ResponseEntity<Object> playerMove(@RequestParam String row, @RequestParam String col, @RequestParam String message) {

        Map<Object, Object> responseMap = new HashMap<>();

        if (message.equals(PLAYER_WIN) || message.equals(PLAYERS_TIE) || message.equals(CPU_WIN)) {
            return ResponseEntity.status(401).body("game over. please reset the game");
        }

        int playerPositionNum = gameService.getPositionNum(row, col);
        if (gameService.isPositionTaken(playerPositionNum)) {
            return ResponseEntity.status(401).body("invalid position selected");
        }

        // PLAYER PUTS THEIR PIECE
        gameService.placePiece(playerPositionNum, PLAYER);

        // CHECK IF THE PLAYER WON
        if (gameService.isPlayerWinner()) {
            responseMap.put("displayMessage", PLAYER_WIN);
            return ResponseEntity.ok(responseMap);
        }

        // IF NO SPACE LEFT, DISPLAY TIE MESSAGE
        if (gameService.isGameATie()) {
            responseMap.put("displayMessage", PLAYERS_TIE);
            return ResponseEntity.ok(responseMap);
        }

        // CPU SEARCH FOR AN AVAILABLE SPOT
        Random rand = new Random();
        int cpuPositionNum;
        do {
            cpuPositionNum = rand.nextInt(9) + 1;
        } while (gameService.isPositionTaken(cpuPositionNum));

        // CPU PUTS THEIR PIECE
        gameService.placePiece(cpuPositionNum, CPU);
        responseMap.put("cpuRowNum", gameService.getRowNum(cpuPositionNum));
        responseMap.put("cpuColNum", gameService.getColNum(cpuPositionNum));

        // CHECK IF CPU WON
        if (gameService.isCpuWinner()) {
            responseMap.put("displayMessage", CPU_WIN);
        } else {
            responseMap.put("displayMessage", "continue the game");
        }

        return ResponseEntity.ok(responseMap);

    }


    @GetMapping("/resetPositions")
    @ResponseBody
    public void resetPositions() {
        gameService.resetGamePositions();
    }

}
