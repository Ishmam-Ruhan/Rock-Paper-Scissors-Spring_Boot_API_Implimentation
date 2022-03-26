package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<Game,Long> {
    Game findBygameJoiningCode(String code);
}
