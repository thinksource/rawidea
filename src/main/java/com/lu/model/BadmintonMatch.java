package com.lu.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="badminton_match")
public class BadmintonMatch {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="match_id")
	private int id;
	
	@ElementCollection 
	private List<String> players;
    @OneToMany(mappedBy="badmintonmatch", cascade = CascadeType.ALL)
    private List<Game> games;
    
    private String winner;
	
	
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public String calculatorWinner() {
		int[] games_scores=new int[2];
		games_scores[0]=0;
		games_scores[1]=0;
		for(Game g : games){
			if(g.getWinner()==players.get(0)){
				games_scores[0]++;
			}else if(g.getWinner()==players.get(1)){
				games_scores[1]++;
			}
		}
		return games_scores[0]>games_scores[1]?players.get(0):players.get(1);
	}
	
	
	
	public BadmintonMatch(){
		super();
		players=new ArrayList<String>();
		games=new ArrayList<Game>();		
	}
	public BadmintonMatch(String play1, String play2) {
		super();
		players=new ArrayList<String>();
		players.add(play1);
		players.add(play2);
		games=new ArrayList<Game>();
	}
	
	
	
}
