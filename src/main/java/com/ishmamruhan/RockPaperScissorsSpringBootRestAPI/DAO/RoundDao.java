package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundDao extends JpaRepository<Round, Long> {
    Round findByid(long roundId);
}
