package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO extends JpaRepository<Player,Long> {
    Player findByid(Long id);
}
