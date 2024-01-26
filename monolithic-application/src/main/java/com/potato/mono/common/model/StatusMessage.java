package com.potato.mono.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMessage {
	SUCCESS("요청이 정상 처리되었습니다."), 
	FAIL("요청이 실패했습니다.");
	
	private String message;
}
