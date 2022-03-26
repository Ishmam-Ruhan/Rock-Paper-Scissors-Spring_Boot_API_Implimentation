package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Controllers;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.GameDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.PlayerDAO;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.RoundDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Game;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Player;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Round;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement.CustomizeException;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Services.GameService;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;


    //Add Player
    @PostMapping("/player/add")
    public ResponseEntity<String> Add_Player(@RequestBody Player player) throws CustomizeException {
        return ResponseEntity.ok(playerService.addPlayer(player));
    }


    //Create New Game and generate join token
    @GetMapping("/player/{id}/create/new/rounds={rounds}")
    public ResponseEntity<String> Create_New_Game_As_A_USER(@PathVariable Long id, @PathVariable long rounds) throws CustomizeException {
        return ResponseEntity.ok(gameService.createNewGame(id,rounds));
    }

    // join a player using joining code
    @GetMapping("/player/{id}/join/{code}")
    public ResponseEntity<String> Join_A_Game_Using_GameCode(@PathVariable Long id, @PathVariable String code) throws CustomizeException {
        return ResponseEntity.ok(gameService.joinGameByCode(id,code));
    }

    //Playing Game
    @GetMapping("/game/{gameCode}/player={playerId}/call={call}")
    public ResponseEntity<String> Play_Game(@PathVariable String gameCode, @PathVariable String call, @PathVariable Long playerId) throws CustomizeException {
        return ResponseEntity.ok(gameService.playGame(gameCode,call,playerId));
    }

}
