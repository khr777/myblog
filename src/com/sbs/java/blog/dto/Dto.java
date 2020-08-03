package com.sbs.java.blog.dto;

import java.util.HashMap;

import java.util.Map;

import lombok.Data;


@Data
public class Dto {
	private int id;
	private String regDate;
	private String updateDate;
	private Map<String, Object> extra;  // db에 담지 못하는 자잘한 것들을 담기 위한 변수. (예시: 작성자 '김혜련')
	
	public Dto() {
		
	}
	
	
	public Dto(Map<String, Object> row) {
		this.id = (int)row.get("id");
		this.regDate = (String)row.get("regDate");
		this.updateDate = (String)row.get("updateDate");
		this.extra = new HashMap<>();
		for ( String key : row.keySet()) {
			if ( key.startsWith("extra__")) {
				Object value = row.get(key);
				String extraKey = key.substring(7);  // "앞에 있는 extra__"는 날려야 하기 때문에 앞에 7글자를 잘라낸다.  
				this.extra.put(extraKey, value);
				
			}
		}
	}

	
	
}
