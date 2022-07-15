package com.paofu.data_structure;

import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:  测试邮件发送
 * @Author: 泡芙和树
 * @Date: 2022/4/29 9:09
 */
@RestController
@Slf4j
public class MailController {

    @GetMapping("/testMail")
    public void testMail() {
        for (int i = 0; i < 5; i++) {
            try {
                MailUtil.send("2550206744@qq.com", "testEmail---", "testEmail---", false);
                log.info("第" + (i + 1) + "封邮件发送成功");
            } catch (Exception e) {
                e.printStackTrace();
                log.info(e.getMessage());
            }
        }
    }
}
