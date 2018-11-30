package com.example.yunxin_sendcode;

import com.example.yunxin_sendcode.untill.MobileMessageSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YunxinSendcodeApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		Boolean i = MobileMessageSend.sendMsg("13414851479");
		System.out.println(i);
	}

}
