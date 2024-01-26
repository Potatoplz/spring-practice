package com.potato.mono.user.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.potato.mono.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Repository
public class UserRepository {
	
	private static final String NAME_SPACE = "UserMapper.";
	
    private final SqlSessionTemplate sqlSessionTemplate;

    public UserRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    
    // 사용자 조회
    public List<UserVO> selectAll() {
    	return sqlSessionTemplate.selectList(NAME_SPACE + "selectAll");
    }
    
    // 사용자 목록 검색조회
    public List<UserVO> selectByCondition(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAME_SPACE + "selectByCondition", params);
    }
    
    // 사용자 ID로 사용자 조회
    public UserVO selectByUserId(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        List<UserVO> users = selectByCondition(params);
        if (users.isEmpty()) {
            return null;
        }

        /**
         * 목록의 첫 번째 요소, 조회된 사용자 객체를 반환
         * UserVO객체를 응답 결과로 처리하고 있기 때문에 int나 boolean으로 처리하지 않은것
         */
        return users.get(0);
    }

    // 사용자 등록
    public int createUser(UserVO userVO) {
    	return sqlSessionTemplate.insert(NAME_SPACE + "insertUser", userVO);
    }
    
    // 사용자 비활성화
    public void deactivateUser(String userId, String userPw) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPw", userPw);
        sqlSessionTemplate.update(NAME_SPACE + "deactivateUser", params);
    }
}
