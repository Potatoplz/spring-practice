package com.potato.mono.user.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.mono.common.model.CommonResponseVO;
import com.potato.mono.common.model.StatusMessage;
import com.potato.mono.user.model.UserVO;
import com.potato.mono.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
    // 사용자목록 전체조회
	@GetMapping
    public CommonResponseVO<List<UserVO>> selectAll() {
		
		List<UserVO> result = userService.findAll();
		
		// 표준 응답 객체로 결과를 반환
	    CommonResponseVO<List<UserVO>> response = CommonResponseVO.<List<UserVO>>builder()
	            .status(HttpStatus.OK.value())
	            .code(StatusMessage.SUCCESS.name())
	            .message(StatusMessage.SUCCESS.getMessage())
	            .data(result)
	            .build();

        return response;
    }
	
	/**
	 * 사용자 등록
	 * RequestParameter
	 * {
	 * 	"userId":"test1234",
	 * 	"userName":"고양이",
	 * 	"userPw":"1234"
	 * }
	 */ 
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserVO userVO) {
        UserVO newUser = userService.createUser(userVO);
        
        if (newUser == null) {
            return new ResponseEntity<>("이미 존재하는 ID 입니다.", HttpStatus.BAD_REQUEST);
        }
        
        // ResponseEntity를 사용하여 반환
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
	
	/**
	 * 사용자 목록 검색 조회
	 * /user/search?userName=홍길
	 * /user/search?userName=홍길동&userId=test4
	 * /user/search?userSts=D
	 */
	@GetMapping("/search")
	public CommonResponseVO<Object> selectByCondition(
	        @RequestParam(required = false) String userId,
	        @RequestParam(required = false) String userName,
	        @RequestParam(required = false) String userSts) {
		
        log.debug("\r\n\r\n"
                + "===================================================================================================\r\n"
                + "* "+ this.getClass().getSimpleName() + " LOG START \r\n"
                + "===================================================================================================\r\n");
        
	    Map<String, Object> params = new HashMap<>();
	    params.put("userId", userId);
	    params.put("userName", userName);
	    params.put("userSts", userSts);

	    List<UserVO> result = userService.findByCondition(params);
	    
	    Object responseData = result.isEmpty() ? "검색 결과가 없습니다" : result;
	    
	    CommonResponseVO<Object> response = CommonResponseVO.<Object>builder()
	            .status(HttpStatus.OK.value())
	            .code(StatusMessage.SUCCESS.name())
	            .message(StatusMessage.SUCCESS.getMessage())
	            .data(responseData)
	            .build();

	    return response;
	}
	
	/**
	 * 사용자 비활성화
	 */
    @PatchMapping("/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestBody UserVO userVO) {
        userService.deactivateUser(userVO);
        return ResponseEntity.ok(userVO.getUserId() + "님의 계정이 비활성화 되었습니다.");
    }
    
    
    // =======================================================================================================
    // - Test용
    // =======================================================================================================
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @RequestBody UserVO userVO) {
    	return "update userId=" + userId;
    }
    
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
    	return "delete userId=" + userId;
    }
    
    @GetMapping("/successExample")
    public CommonResponseVO<Object> getSuccessResponse() {

        CommonResponseVO<Object> response = new CommonResponseVO<>(
            HttpStatus.OK.value(),
            StatusMessage.SUCCESS.name(),
            "요청이 성공적으로 처리되었습니다.",
            LocalDateTime.now(),
            "여기에 문자열 데이터"
        );

        return response;
    }
    

}
