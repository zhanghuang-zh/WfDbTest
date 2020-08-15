import com.zb.pojo.Dept;
import com.zb.service.DeptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestJava {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-redis.xml");
        DeptService deptService = context.getBean(DeptService.class);
        List<Dept> list = deptService.findDeptAll();
        for (Dept dept : list) {
            System.out.println(dept.getId() + "\t" + dept.getDeptName());
        }

    }





}
