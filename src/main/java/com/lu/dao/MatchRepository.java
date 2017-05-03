package com.lu.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lu.model.BadmintonMatch;
import com.lu.model.Game;



@Repository
public interface MatchRepository extends CrudRepository<BadmintonMatch, Integer> {
	
	public BadmintonMatch findById(int id);

	
	@Transactional
	public BadmintonMatch save(BadmintonMatch match);
	
	@Transactional
	public void delete(BadmintonMatch match);
}
