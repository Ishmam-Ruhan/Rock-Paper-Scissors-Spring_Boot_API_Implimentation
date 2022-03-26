package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Services;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.GameDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.PlayerDAO;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.RoundDao;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Game;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Player;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Round;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.ErrorManagement.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GameService {
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private RoundDao roundDao;


    public String createNewGame( Long id,  long rounds) throws CustomizeException {

        //Create Game
        Game game = new Game();

        String gameCode = generateGameId();

        try{
            long check =  playerDAO.findByid(id).getId();
            game.setPlayer1(playerDAO.findByid(id));
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Not Found",ex.getMessage());
        }

        game.setGameJoiningCode(gameCode);
        game.setRoundNumber(rounds);

        try{
            gameDao.save(game).getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",ex.getMessage());
        }


        //Create 1st Round
        Round round = new Round();
        round.setActive(true);

        try{
            long check = gameDao.findBygameJoiningCode(gameCode).getId();
            round.setGame(gameDao.findBygameJoiningCode(gameCode));
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Game Not Found",ex.getMessage());
        }


        try{
            roundDao.save(round).getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",ex.getMessage());
        }


        return  "Your Teammate Can Join Through Code: "+gameCode;
    }

    //Join A Player By game Code into a game
    public String joinGameByCode( Long id,  String code) throws CustomizeException {
        Game game=null;

        try{
            game = gameDao.findBygameJoiningCode(code);
            game.getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Game Not Found",ex.getMessage());
        }

        if(game.getPlayer2()!=null)throw new CustomizeException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value(), "Game is Full!","Game Full!! No new player can't be added!");

        try{
            Player player = playerDAO.findByid(id);
            player.getId();
            game.setPlayer2(player);
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Player Not Found",ex.getMessage());
        }

        try{
            gameDao.save(game).getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",ex.getMessage());
        }


        return "Successfully Joined the game!!!!!!";
    }

    //Playing Game
    public String playGame( String gameCode,  String call,  Long playerId) throws CustomizeException {
        Game game=null;
        try{
            game = gameDao.findBygameJoiningCode(gameCode);
            game.getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Game Not Found",ex.getMessage());
        }
        List<Round> rounds = game.getRounds();

        Round round =  rounds.stream()
                .filter(Round::getActive).findFirst().orElseThrow(() -> new CustomizeException(HttpStatus.FORBIDDEN.value(), "No Round Found!","Game is Over / Not Active right now!"));

        long roundId = round.getId();

        if(Objects.equals(playerId, game.getPlayer1().getId())){
            if(round.getPlayer1Call()!=null){
                return "You have already CALLED!! Wait for your fiends response!";
            }
            else if(round.getPlayer1Call()!=null && round.getPlayer2Call() != null){
                return "This round is Over!! You cannot make change your decision!";
            }else{
                round.setPlayer1Call(call);
            }
        }
        else if(Objects.equals(playerId, game.getPlayer2().getId())){

            if(round.getPlayer2Call()!=null && round.getPlayer1Call() == null){
                return "You have already CALLED!! Wait for your fiends response!";
            }
            else if(round.getPlayer2Call()!=null && round.getPlayer1Call() != null){
                return "This round is Over!! You cannot make change your decision!";
            }else{
                round.setPlayer2Call(call);
            }
        }
        else{
            throw new CustomizeException(HttpStatus.FORBIDDEN.value(), "Not Authorized","Player with id: "+playerId+" is not authorized for this game!");
        }

        try{
            roundDao.save(round).getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",ex.getMessage());
        }

        Round round1= null;

        try{
            round1 = roundDao.findByid(roundId);
            round1.getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.NOT_FOUND.value(), "Round Not Found of game: "+game.getGameJoiningCode(),ex.getMessage());
        }



        if(round.getPlayer1Call() == null || round.getPlayer2Call() == null){
            return "Wait for Opponent response!";
        }

        String player1Call = round1.getPlayer1Call();
        String player2Call = round1.getPlayer2Call();



        String winner="";

        if(player1Call.equals("ROCK") && player2Call.equals("PAPER")){
            winner="Player 2 Winner!!!!";
            round.setWin(game.getPlayer2());
        }
        else if(player1Call.equals("ROCK") && player2Call.equals("SCISSORS")){
            winner="Player 1 Winner!!!!";
            round.setWin(game.getPlayer1());
        }
        else if(player1Call.equals("PAPER") && player2Call.equals("ROCK")){
            winner="Player 1 Winner!!!!";
            round.setWin(game.getPlayer1());
        }
        else if(player1Call.equals("PAPER") && player2Call.equals("SCISSORS")){
            winner="Player 2 Winner!!!!";
            round.setWin(game.getPlayer2());
        }
        else if(player1Call.equals("SCISSORS") && player2Call.equals("ROCK")){
            winner="Player 2 Winner!!!!";
            round.setWin(game.getPlayer2());
        }
        else if(player1Call.equals("SCISSORS") && player2Call.equals("PAPER")){
            winner="Player 1 Winner!!!!";
            round.setWin(game.getPlayer1());
        }else{
            winner="Drawn!";
        }


        round.setActive(false);

        try{
            roundDao.save(round).getId();
        }catch (Exception ex){
            throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",ex.getMessage());
        }


        final Game game1 = gameDao.findBygameJoiningCode(gameCode);
        if(game1.getRounds().size()<game1.getRoundNumber()){
            Round round2 = new Round();
            round2.setActive(true);
            round2.setGame(game1);
            roundDao.save(round2).getId();
        }else{
            long player1Wins = game1.getRounds().stream()
                    .filter(round2 -> round2.getWin()!=null)
                    .filter(round2 -> round2.getWin().equals(game1.getPlayer1()))
                    .count();
            long player2Wins = game1.getRounds().stream()
                    .filter(round2 -> round2.getWin()!=null)
                    .filter(round2 -> round2.getWin().equals(game1.getPlayer2()))
                    .count();


            if(player1Wins>player2Wins){
                game1.setWin(game1.getPlayer1());
            }
            else if(player1Wins<player2Wins){
                game1.setWin(game1.getPlayer2());
            }
            try{
                gameDao.save(game1).getId();
            }catch (Exception ex){
                throw new CustomizeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",ex.getMessage());
            }
        }


        return "Round Result: "+winner;
    }

    // Get rounds of a particular game
    public List<Round> getRounds( String gameId){
        Game game = gameDao.findBygameJoiningCode(gameId);

        return game.getRounds();
    }

    public String generateGameId(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        String result="";

        for (int i = 0; i < 10; i++) {
            int pos = (int) (AlphaNumericString.length()*Math.random());
            result+=AlphaNumericString.charAt(pos);
        }

        return result;
    }
}
