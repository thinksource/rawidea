package com.lu;


import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.lu.dao.GameRepository;
import com.lu.dao.MatchRepository;
import com.lu.model.Game;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gamerepository;
    
    @Autowired
    private MatchRepository matchrepository;
    
    @Test
    public void testExample() throws Exception {
    	Game g=new Game("Tom", "Adam");
    	g.setScore1(21);
    	g.setScore2(9);
    	g.setWinner("Tom");
    	g.setGame_number(1);
    	g.setId(2000);
        this.entityManager.persist(g);
        Game findgame = gamerepository.findById(1);
        assertThat(findgame.getWinner()).isEqualTo("Tom");
        assertThat(findgame.getGame_number()).isEqualTo(1);
        gamerepository.delete(findgame);
    }
}
