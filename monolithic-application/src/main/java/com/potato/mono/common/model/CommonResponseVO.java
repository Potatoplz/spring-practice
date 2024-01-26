package com.potato.mono.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * LocalDateTime: Java8 이후 도입 Date보다 좋은듯
 * - UTC나 지역 시간대를 고려하지 않고 날짜와 시간을 표현
 * - 불변성 : 멀티스레드 환경에서 더 안전
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값인 필드는 JSON에 포함하지 않음
@JsonPropertyOrder({ "status", "code", "message", "timestamp", "data" }) // 출력 순서 보장
public class CommonResponseVO<T> {
	
    private int status;
    
    private String code;
    
    private String message;
    
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    
    private T data;
}