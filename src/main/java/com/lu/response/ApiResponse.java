package com.lu.response;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.lu.model.BadmintonMatch;
import com.lu.model.Game;



public class ApiResponse {

    private JSONObject message;
 
    public void setMessage(JSONObject message) {
		this.message = message;
	}


	public ApiResponse(JSONObject message) {

        this.message = message;
    }
 
 
    public JSONObject getMessage() {
        return message;
    }
    
    public ApiResponse(BadmintonMatch match){
    	HashMap<String, Object> map=new HashMap<String, Object>();
    	map.put("id", match.getId());
    	map.put("players", match.getPlayers().toArray());
    	map.put("match_winner", match.getWinner());
    	message=new JSONObject(map);
    	ArrayList<HashMap<String, Object>> Games_details=new ArrayList<HashMap<String, Object>>();
    	for(Game g:match.getGames()){
    		HashMap<String, Object> game_map=new HashMap<String, Object>();
    		game_map.put("game_number", g.getGame_number());
    		game_map.put("game_scores", g.getScoreMap());
    		Games_details.add(game_map);
    	}
    	map.put("match_scores", Games_details.toArray());
    	//return message.toJSONString();
    	
    }
    
    public String toJSONString(){
    	return message.toJSONString();
    }
}
