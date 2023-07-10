package kr.or.sw.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class MyBatisUtilTest {

    @Test
    public void testGetSession() {
        log.info("testGetSession()");

        boolean result = MyBatisUtil.getSession() != null;
        Assert.assertTrue("FAIL", result);
    }
}