package com.potato.mono.test;

import lombok.Data;

/**
 * @Data
 * - @Getter, @Setter, @ToString, @EqualsAndHashCode, 
 * - 그리고 인자가 없는 생성자에 대한 @NoArgsConstructor 어노테이션을 포괄
 */
@Data
public class TestVO {
    private Long id;
    private String name;
}

