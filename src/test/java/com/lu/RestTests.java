package com.lu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lu.model.Score;
import com.lu.model.Scoreslist;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTests {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Test
	public void versionTest() {
		String body = this.restTemplate.getForObject("/version", String.class);
		assertThat(body).isEqualTo("running ok");
	}
	
	@Test
	public void postTest() throws IOException{
		//ClassLoader classLoader = getClass().getClassLoader();
        Resource resource = resourceLoader.getResource("classpath:test.json");
        File jsonFile = resource.getFile();

		String content =  FileUtils.readFileToString(jsonFile, "UTF-8");
		Scoreslist req=JSON.parseObject(content, Scoreslist.class);
		JSONObject rep=this.restTemplate.postForObject("/scorelist", req, JSONObject.class);
		assertThat(rep.getString("match_winner")).isEqualTo("John");
		//assert.getClass().getName().
	}
	
	@Test
	public void failureTest() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:test.json");
        File jsonFile = resource.getFile();
		String content =  FileUtils.readFileToString(jsonFile, "UTF-8");
		Scoreslist req=JSON.parseObject(content, Scoreslist.class);
		Score wrongscore=req.getScores().get(10);
		wrongscore.setScorer("Tom");
		JSONObject rep=this.restTemplate.postForObject("/scorelist", req, JSONObject.class);
		assertThat(rep.getInteger("status")).isEqualTo(500);
		assertThat(rep.getString("message")).isEqualTo("Can not get more than 2 different person name from Score list");
	}
}
