package com.ddgotxdy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: ddgo
 * @description:
 */
@SpringBootTest
public class MyTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test01() {
        System.out.println(passwordEncoder.encode("1314520ASD"));
        System.out.println(passwordEncoder.matches("1314520ASD", "$2a$10$jiztRjcCotpJwNSIYQ/Cl.pr5EAvR9eFyvB6KIa/yJaACLqmEk94e"));
    }
}
