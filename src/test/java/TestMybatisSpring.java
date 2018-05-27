import com.blueinfinite.App;
import com.blueinfinite.mapper.CustomMapper;
import com.blueinfinite.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(App.class)
public class TestMybatisSpring {

    @Autowired
    CustomMapper customMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void test_mybatis() {
        //查询
        System.out.println(customMapper.getCustom(3));
        System.out.println(departmentMapper.getInfo(5));
    }
}
