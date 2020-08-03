package com.sbs.java.blog.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CateItem extends Dto {
	private String name;
	
	
	public CateItem() {
		
	}
	
	//row들의 값들을 형변환 해주는 이유는 Map은 Object 타입을 리턴하기 때문!!
	//필요한, 맞는 타입으로 꼭 형변환을 해주어야 한다! 
	public CateItem(Map<String, Object> row) {
		super(row);
		this.name = (String)row.get("name");
	}
	
	
}
