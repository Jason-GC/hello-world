package com.csx.csxspringboot;

import com.alibaba.excel.EasyExcel;
import com.csx.csxspringboot.bean.User;
import com.csx.csxspringboot.utils.ExcelUtil;
import com.csx.csxspringboot.utils.easyExcelListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class CsxSpringBootApplicationTests {

    @Test
    public void mysqlConnecTest(){




    }

    @Test
    public void readEasyExcelTest(){

        easyExcelListener easyExcelListener = new easyExcelListener();

        EasyExcel.read("user.xlsx",User.class,easyExcelListener)
                .sheet()
                .doRead();
    }

    @Test
    public void parseExcelTest() throws IOException {
        List<Object> list = ExcelUtil.parseExcelFile("user01.xlsx");
        System.out.println(list.size());
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    void contextLoads() {
        List<User> userList = new ArrayList<>();
        for(int i=0; i<10; i++){
            User user = new User();
            user.setUserName("Csx"+i);
            user.setUserAge(i);
            user.setIgnore("略略略");
            userList.add(user);
        }
        EasyExcel.write("user.xlsx",User.class).sheet("user01").
                doWrite(userList);
    }

}
