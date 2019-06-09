import com.lqh.dao.IProductDao;
import com.lqh.dao.ISysRoleDao;
import com.lqh.domain.Product;
import com.lqh.domain.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring_dao.xml")
public class test {

    @Autowired
    private IProductDao iProductDao;

    @Test
    public void test() throws Exception {
        System.out.println(iProductDao.findById(14));
    }

    @Test
    public void test1() {
        List<Product> list = iProductDao.findAllPage(1, 3);
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
