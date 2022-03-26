package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Player player1;

    @OneToOne
    private Player player2;

    @OneToOne
    private Player win;

    @Column(unique = true)
    private String gameJoiningCode;

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties(value = {"game","games","rounds"})
    private List<Round> rounds = new ArrayList<>();

    private long roundNumber;

    public Game() {
    }

    public Game(Long id, Player player1, Player player2, Player win, String gameJoiningCode, List<Round> rounds, long roundNumber) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.win = win;
        this.gameJoiningCode = gameJoiningCode;
        this.rounds = rounds;
        this.roundNumber = roundNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWin() {
        return win;
    }

    public void setWin(Player win) {
        this.win = win;
    }

    public String getGameJoiningCode() {
        return gameJoiningCode;
    }

    public void setGameJoiningCode(String gameJoiningCode) {
        this.gameJoiningCode = gameJoiningCode;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public long getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(long roundNumber) {
        this.roundNumber = roundNumber;
    }
}
