package com.potato.mono.user.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.potato.mono.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import com.potato.mono.user.model.UserVO;

@Slf4j
@Service
public class UserService {
	
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // 사용자 목록 검색조회
    public List<UserVO> findAll() {
        return userRepository.selectAll();
    }
    // 사용자 목록 검색조회
    public List<UserVO> findByCondition(Map<String, Object> params) {
        return userRepository.selectByCondition(params);
    }
    
    // 사용자 등록
    public UserVO createUser(UserVO userVO) {
        UserVO existingUser = userRepository.selectByUserId(userVO.getUserId());
        if (existingUser != null) {
            // 중복된 ID가 존재하는 경우
            log.info("User ID {} already exists", userVO.getUserId());
            return null;
        }

        int result = userRepository.createUser(userVO);
        if (result > 0) {
            return userVO; // 성공적으로 생성된 경우
        }
        return null; // 삽입 실패시
    }
    
    // 사용자 비활성화
    public void deactivateUser(UserVO userVO) {
        userRepository.deactivateUser(userVO.getUserId(), userVO.getUserPw());
    }
}
