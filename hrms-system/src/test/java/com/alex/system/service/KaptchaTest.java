package com.alex.system.service;

import com.baomidou.kaptcha.Kaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author _Alexzinv_
 * @date 2021/12/16
 * @description
 */
@SpringBootTest
public class KaptchaTest {

    private Kaptcha kaptcha;

    @Autowired
    public void setKaptcha(Kaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    @Test
    public void renderTest(){
        String render = kaptcha.render();
        System.out.println(render);
    }
}
