package com.goodguy.support;

import com.aliyun.oss.OSSClient;
import com.goodguy.common.bo.EnumResourceBO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class GoodguySupportApplicationTests {

    @Resource
    OSSClient ossClient; // 注入 OSSClient 实例

    @Test
    void contextLoads() {
        EnumResourceBO bo = new EnumResourceBO(20, "xxxxx");
        System.out.println("\n" + bo.getMsg() + "\n");
    }

    /**
     * 测试阿里云对象存储上传文件流
     */
    @Test
    public void testUpload() throws FileNotFoundException {
        // 文件输入流。填写本地文件的完整路径
        InputStream inputStream = new FileInputStream("C:\\Users\\young\\Desktop\\a.jpg");
        // 依次填写Bucket名称、文件在OSS中保存的文件名、文件输入流
        ossClient.putObject("goodguy", "1-1-1.png", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传完成");
    }

    /**
     * uuid
     */
    @Test
    public void get16UUID(){

        // 1.开头两位，标识业务代码或机器代码(可变参数)
        String machineId = "11";

        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());

        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();

        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }

        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + String.format("%010d", hashCode);

        System.out.println(value);
    }

    /**
     * 压缩
     * @return base64字符串，length=22
     */
    @Test
    public void getUuid22() {
        String src = UUID.randomUUID().toString();
        UUID uuid = UUID.fromString(src);
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        byte[] b = new byte[16];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (msb >>> (8 * (7-i)) & 0xff);
            b[i+8] = (byte) (lsb >>> (8 * (7-i)) & 0xff);
        }

        String s = Base64.getEncoder().withoutPadding().encodeToString(b);
        System.out.println(s);
    }

}
