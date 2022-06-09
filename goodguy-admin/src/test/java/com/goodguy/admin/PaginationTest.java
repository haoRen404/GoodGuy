package com.goodguy.admin;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaginationTest {

    // @Autowired
    // MessageTemplateMapper messageTemplateMapper;
    //
    // /**
    //  * 分页：
    //  * 1.统计记录数，使用count(1)
    //  *    SELECT COUNT(1) FROM student WHERE age > ?
    //  * 2.实现分页，在sql语句的末尾加入 limit 0,3
    //  *    SELECT id,name,age,email,status FROM student WHERE age > ? LIMIT 0,3
    //  */
    // @Test
    // public void testPage(){
    //     QueryWrapper<MessageTemplate> qw = new QueryWrapper<>();
    //     IPage<MessageTemplate> page  = new Page<>();
    //     //设置分页的数据
    //     page.setCurrent(1);//第一页
    //     page.setSize(3);// 每页的记录数
    //
    //     IPage<MessageTemplate> result = messageTemplateMapper.selectPage(page, qw); // 分页查询
    //
    //     //获取分页后的记录
    //     List<MessageTemplate> templateList = result.getRecords();
    //     for (MessageTemplate s: templateList ) {
    //         System.out.println(s);
    //     }
    //     System.out.println("templateList.size()=" + templateList.size());
    //     //分页的信息
    //     long pages  = result.getPages();
    //     System.out.println("页数：" + pages);
    //     System.out.println("总记录数：" + result.getTotal());
    //     System.out.println("当前页码：" + result.getCurrent());
    //     System.out.println("每页的记录数：" + result.getSize());
    // }

}
