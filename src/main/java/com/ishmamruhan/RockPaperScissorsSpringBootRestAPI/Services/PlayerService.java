package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Services;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.GameDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.PlayerDAO;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.RoundDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Player;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    //Add Player
    @PostMapping("/player/add")
    public String addPlayer(Player player) throws CustomizeException {
        long playerId = -1;
        try{
            playerId = playerDAO.save(player).getId();
        }catch(Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"INTERNAL_SERVER_ERROR",ex.getMessage());
        }
        return "Successfully Added Player with ID: "+playerId;
    }
}
