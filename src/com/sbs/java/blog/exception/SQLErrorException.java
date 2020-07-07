package com.sbs.java.blog.exception;
										// Exception이 아닌 RuntimeException으로 해주면 try/catch를 해도되고, 안해도 되고. 다른 package에서 왜 try/catch 안했냐고 뭐라 하지 않음.
public class SQLErrorException extends RuntimeException {

	public SQLErrorException(String message) {
		super(message);
	}

}
