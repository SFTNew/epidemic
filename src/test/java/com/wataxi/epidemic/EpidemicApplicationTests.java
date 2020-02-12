package com.wataxi.epidemic;

import com.wataxi.epidemic.utils.PasswordEncryption;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpidemicApplicationTests {


    @Test
    void contextLoads() {
        System.out.println(PasswordEncryption.encode("admin")) ;
    }

}
