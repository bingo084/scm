package com.aowin.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期测试类
 *
 * @author bingo
 */
public class DateTest {
    @Test
    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String date = sdf.format(new Date());
        System.out.println(date);
    }
}
