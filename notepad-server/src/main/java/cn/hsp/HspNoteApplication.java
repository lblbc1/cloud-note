package cn.hsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 花生皮编程(CSDN、简书、掘金、今日头条、微信公众号、抖音、快手、B站、西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@MapperScan({"cn.hsp.mapper","cn.hsp.login.mapper"}) //扫描的mapper
@SpringBootApplication
public class HspNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(HspNoteApplication.class, args);
	}
}
