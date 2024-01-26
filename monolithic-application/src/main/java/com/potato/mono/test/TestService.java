package com.potato.mono.test;

import java.util.List;

import org.springframework.stereotype.Service;

// TestMapper를 주입 받아 getAllTests 메소드에서 DB의 모든 데이터를 조회한다. 
@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestVO> getAllTests() {
        return testRepository.findAll();
    }
}