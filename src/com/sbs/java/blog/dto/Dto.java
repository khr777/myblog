package com.sbs.java.blog.dto;

import java.util.HashMap;
import java.util.Map;

public class Dto {
	private int id;
	private String regDate;
	private Map<String, Object> extra;  // db에 담지 못하는 자잘한 것들을 담기 위한 변수. (예시: 작성자 '김혜련')
	
	public Dto() {
		
	}
	
	
	public Dto(Map<String, Object> row) {
		this.id = (int)row.get("id");
		this.regDate = (String)row.get("regDate");
		this.extra = new HashMap<>();
		for ( String key : row.keySet()) {
			if ( key.startsWith("extra__")) {
				Object value = row.get(key);
				String extraKey = key.substring(7);  // "앞에 있는 extra__"는 날려야 하기 때문에 앞에 7글자를 잘라낸다.  
				this.extra.put(extraKey, value);
			}
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Map<String, Object> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}


	@Override
	public String toString() {
		return "dto [id=" + id + ", regDate=" + regDate + ", extra=" + extra + "]";
	}
	
}
