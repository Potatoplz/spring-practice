package com.potato.mono.test;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
	
	/**
	 * SqlSessionTemplate
	 * - MyBatis에서 제공하는 객체로 이걸 주입해야 mapper xml에 연결된다.
	 */
    private final SqlSessionTemplate sqlSessionTemplate;

    public TestRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<TestVO> findAll() {
        return sqlSessionTemplate.selectList("TestMapper.findAll");
    }

}
