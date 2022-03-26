package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.Helpers;

import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DAO.PlayerDAO;
import com.ishmamruhan.RockPaperScissorsSpringBootRestAPI.DTO.Player;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class IdGenerator implements IdentifierGenerator {

    @Autowired
    PlayerDAO playerDAO;


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "player-";

        List<Player> allPlayers = playerDAO.findAll();



        System.out.println("***********************************");
        System.out.println("***********************************");
        System.out.println("value of x: ");
        System.out.println("***********************************");
        System.out.println("***********************************");



        return prefix;
    }
}
