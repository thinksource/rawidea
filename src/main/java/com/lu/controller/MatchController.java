package com.lu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lu.dao.GameRepository;
import com.lu.dao.MatchRepository;
import com.lu.exception.ScorelistException;
import com.lu.model.BadmintonMatch;
import com.lu.model.Game;
import com.lu.model.Score;
import com.lu.model.Scoreslist;
import com.lu.response.ApiResponse;

@RestController
public class MatchController {
	static final String NONAME = "Unknow";
	
	private static final Logger log = LoggerFactory.getLogger(MatchController.class);
	@Autowired
	private GameRepository gamedao;
	@Autowired
	private MatchRepository matchdao;
	
	@RequestMapping(value = "/scorelist", method = RequestMethod.POST, produces =  "application/json")
	public JSONObject resolveScoreslist(@RequestBody Scoreslist sclist) throws ScorelistException{
		List<Score> scores=sclist.getScores();
		
		String person1=null;
		String person2=null;
		int score1=0;
		int score2=0;
		int game1=0;
		int game2=0;
		for(Score i : scores){
			//log.info(new Integer(i.getId()).toString());
			//log.info(i.getScorer());
			if( person1==null || person1.isEmpty()){
				person1=i.getScorer();
				
			}else if ((person2==null || person2.isEmpty()) && person1!=i.getScorer()){
				person2=i.getScorer();
				break;
			}
		}

		BadmintonMatch re=new BadmintonMatch(person1,person2);
		Game game=new Game(person1, person2);
		game.setMatch(re);
		re.getGames().add(game);
		for(Score i : scores){
			if(i.getScorer().equals(person1))game.addScore1();
			else if(i.getScorer().equals(person2))game.addScore2();
			else {

				throw new ScorelistException("Can not get more than 2 different person name from Score list") ;
			}
			if(game.isFinished()){
				game.Finishgame();
				if(game.getWinner().equals(person1))game1++;
				else if(game.getWinner().equals(person2))game2++;
				game=new Game(person1, person2);
				game.setMatch(re);
				re.getGames().add(game);
			}
			//if more than 3 games will just get first 3 game and 
			//neglect the rest of them.
			if(game1+game2>3)break;
		}
		matchdao.save(re);
		for (Game g: re.getGames()){
			gamedao.save(g);
		}
		re.setWinner(re.calculatorWinner());
		ApiResponse result=new ApiResponse(re);
		return result.getMessage();
	}
	
	@RequestMapping("/fetch")
	public JSONObject fetch(@RequestParam("matchid")int id){
		BadmintonMatch re=matchdao.findById(id);
		ApiResponse result=new ApiResponse(re);
		return result.getMessage();
	}

	@RequestMapping("/version")
	public String fetch(){
		
		return "running ok";
	}
	
}
