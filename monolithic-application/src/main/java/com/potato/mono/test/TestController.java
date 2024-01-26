package com.potato.mono.test;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
	
	@GetMapping
	public String test() {
		return "Hello world!";
	}
	
    @GetMapping("select")
    public List<TestVO> getAllTests() {
        List<TestVO> tests = testService.getAllTests();
        log.info("Retrieved tests: {}", tests);
        return tests;
    }

}
