package com.example.share;


import com.example.share.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = {ShareApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShareApplicationTests {

    @Autowired
    private RedisLock redisLock;
    @Test
    public void test() {

        System.out.println("###################lock######################");
        boolean result = redisLock.tryLock("aaa",1000L,5, TimeUnit.SECONDS);
        System.out.println("###################lock result = " + result);
    }

    @Test
    public void freeLock() {
        System.out.println("###################free lock######################");
        boolean result = redisLock.unlock("aaa",1000L);
        System.out.println("###################free lock result = " + result);
    }

}
