package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO;


import javax.persistence.*;

@Entity
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String player1Call;

    private String player2Call;

    @OneToOne
    private Player win;

    @ManyToOne()
    @JoinColumn(name = "Game_id")
    private Game game;

    private Boolean isActive;

    public Round() {
    }

    public Round(long id, String player1Call, String player2Call, Player win, Game game, Boolean isActive) {
        this.id = id;
        this.player1Call = player1Call;
        this.player2Call = player2Call;
        this.win = win;
        this.game = game;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1Call() {
        return player1Call;
    }

    public void setPlayer1Call(String player1Call) {
        player1Call = player1Call.toUpperCase();

        if(player1Call.startsWith("R")){
            this.player1Call = "ROCK";
        }
        else if(player1Call.startsWith("P")){
            this.player1Call = "PAPER";
        }
        else if(player1Call.startsWith("S")){
            this.player1Call = "SCISSORS";
        }
    }

    public String getPlayer2Call() {
        return player2Call;
    }

    public void setPlayer2Call(String player2Call) {
        player2Call = player2Call.toUpperCase();

        if(player2Call.startsWith("R")){
            this.player2Call ="ROCK";
        }
        else if(player2Call.startsWith("P")){
            this.player2Call = "PAPER";
        }
        else if(player2Call.startsWith("S")){
            this.player2Call = "SCISSORS";
        }
    }

    public Player getWin() {
        return win;
    }

    public void setWin(Player win) {
        this.win = win;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
