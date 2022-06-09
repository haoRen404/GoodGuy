package com.goodguy.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoodguyAdminApplicationTests {
    @Value("${spring.datasource.driver-class-name}")
    String name;

    @Test
    void contextLoads() {
        String projectPath = System.getProperty("user.dir");
        System.out.println("\n\n\nname = " + projectPath + "\n\n\n");
    }

}
