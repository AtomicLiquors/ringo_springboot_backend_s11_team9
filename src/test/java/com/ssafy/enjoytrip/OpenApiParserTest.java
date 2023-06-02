package com.ssafy.enjoytrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.ringo.model.dao.attraction.AttractionDAO;
import com.project.ringo.model.dto.attraction.Attraction;
@SpringBootTest
public class OpenApiParserTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenApiParserTest.class);
	
	@Autowired
	private AttractionDAO attractionDao;
	
	@Test
	void getOpenAPI() {
		
    	// 인증키 (개인이 받아와야함)
		String serviceKey = "9ckdF3wQxCBkkFWiUrBNRUl4QgPjPRMA87lCNKMjyPbiq%2FTZfz0H3U2u6tL1WhlY%2FR%2BRwSroSJolzPop8DBeMA%3D%3D";

    	// 파싱한 데이터를 저장할 변수
    	String result = "";

    	try {
    		URL url = new URL("https://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
    				+ "numOfRows=100"
    				+ "&MobileOS=ETC&MobileApp=EnjoyTrip&_type=json&"
    				+ "serviceKey=" + serviceKey
    				);
    		
    		/*
    		URL url = new URL("https://apis.data.go.kr/B551011/KorService1/searchKeyword1?keyword=%ED%83%80%EC%9B%8C&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=enjoytrip&_type=json&"
    				+ "serviceKey=" + serviceKey
    				+ "&areaCode=1");*/
    		BufferedReader bf;
    		bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

    		result = bf.readLine();

        	JSONParser jsonParser = new JSONParser();
        	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
        	
        	System.out.println(jsonObject.toJSONString());
        	
        	JSONObject response = (JSONObject)jsonObject.get("response");
        	JSONObject body = (JSONObject)response.get("body");
        	JSONObject items = (JSONObject)body.get("items");
        	JSONArray item = (JSONArray)items.get("item");
        	
        	List<Attraction> list = new ArrayList<>();
        	
        	int cnt = 0;
        	for(Object i : item) {
        		cnt++;
        		JSONObject curr = (JSONObject) i;
        		Attraction attr = new Attraction();
        		attr.setAddr1(curr.get("addr1").toString());
        		attr.setTitle(curr.get("title").toString());
        		attr.setSido_code(Integer.parseInt(curr.get("areacode").toString()));
        		attr.setGugun_code(Integer.parseInt(curr.get("sigungucode").toString()));
        		attr.setContent_id(Integer.parseInt(curr.get("contentid").toString()));
        		attr.setContent_type_id(Integer.parseInt(curr.get("contenttypeid").toString()));
        		attr.setFirst_image(curr.get("firstimage").toString());
        		attr.setLongitude(curr.get("mapx").toString());
        		attr.setLatitude(curr.get("mapy").toString());
        		
        		list.add(attr);
        	}
        	attractionDao.insertAttractionList(list);
        	System.out.println(cnt);
        	
        	
        	//1. 이제 이걸 DB에 세팅하는 코드를 작성해 보자.
        	//2. 이미 존재하는 데이터라면 update할 것인가? 삭제하고 새로 insert하려면 참조 무결성 오류가 발생한다.
        	//3. 기본 동작을 update로 하고, 해당하는 contentid가 없다고 하면 insert로 동작시키자.
        	//=======mybatis mapper에서 on duplicate update로 해결
        	
        	//4. 위 동작을 지정된 시간에 실행시킬 방법을 구상해 보자.


    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}