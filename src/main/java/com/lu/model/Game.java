package com.lu.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "game")
public class Game {

	static final int FINISHSCORE = 21;
	static final int FINALSCORE = 30;
	static final String NORESULT = "match is not finished yet";
	static final String ERROR = "error";
	static final String UNKNOW = "unknow";

	@Id
	@Column(name = "game_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String person1;
	private String person2;
	private String winner;
	private int score1;
	private int score2;
	private int game_number;
	public BadmintonMatch getBadmintonmatch() {
		return badmintonmatch;
	}

	public void setBadmintonmatch(BadmintonMatch badmintonmatch) {
		this.badmintonmatch = badmintonmatch;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "match_id")
	private BadmintonMatch badmintonmatch;

	public BadmintonMatch getMatch() {
		return badmintonmatch;
	}

	public void setMatch(BadmintonMatch match) {
		this.badmintonmatch = match;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGame_number() {
		return game_number;
	}

	public void setGame_number(int game_number) {
		this.game_number = game_number;
	}

	public String getPerson1() {
		return person1;
	}

	public void setPerson1(String person1) {
		this.person1 = person1;
	}

	public String getPerson2() {
		return person2;
	}

	public void setPerson2(String person2) {
		this.person2 = person2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public boolean isFinished() {
		if (score1 == FINALSCORE || score2 == FINALSCORE)
			return true;
		else if ((score1 > FINISHSCORE || score2 > FINISHSCORE) && Math.abs(score1 - score2) >= 2)
			return true;
		else
			return false;
	}

	public void Finishgame() {

		if (isFinished()) {
			game_number++;
			if (score1 > score2)
				winner = person1;
			else
				winner = person2;
		}
	}

	public String getWinner() {
		return winner;
	}

	public String getScore(String person) {
		if (person1 == person) {
			score1++;
			return person;
		} else if (person2 == person) {
			score2++;
			return person;
		} else
			return ERROR;

	}

	public Game(String person1, String person2) {
		super();
		this.person1 = person1;
		this.person2 = person2;
		this.score1 = 0;
		this.score2 = 0;
		this.game_number = 1;
		this.winner = NORESULT;
	}

	public Game() {
		super();
		this.person1 = UNKNOW;
		this.person2 = UNKNOW;
		this.score1 = 0;
		this.score2 = 0;
		this.game_number = 0;
		this.winner = NORESULT;

	}

	public void addScore1() {
		score1++;
	}

	public void addScore2() {
		score2++;
	}

	public HashMap<String, Integer> getScoreMap() {
		HashMap<String, Integer> re = new HashMap<String, Integer>();
		re.put(person1, score1);
		re.put(person2, score2);
		return re;
	}

}
