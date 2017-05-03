package com.lu.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lu.model.BadmintonMatch;
import com.lu.model.Game;



@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
	
	

	public List<Game> findByBadmintonmatch(BadmintonMatch b);
	
	public Game findById(int id);
	
	@Transactional
	public Game save(Game game);
	
	@Transactional
	public void delete(Game game);
	
	
	
	
}
