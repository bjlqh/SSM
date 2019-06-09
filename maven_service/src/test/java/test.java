import com.lqh.domain.Product;
import com.lqh.service.IProductService;
import com.lqh.service.ISysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring_service.xml")
public class test {
    @Autowired
    private IProductService iProductService;
    @Test
    public void test() throws Exception {
        System.out.println(iProductService.findById(14));
    }
    @Test
    public void test1() throws Exception {

        Product product = new Product();
        product.setProductName("帝都二日游");
        product.setProductNum("itcast-010");
        product.setDepartureTime(new Date());
        product.setCityName("北京");
        product.setProductDesc("dafdadfa");
        product.setProductPrice(9.9);
        product.setProductStatus(1);
        iProductService.save(product);
        System.out.println("存入的id是："+product.getId());
    }
    @Autowired
    private ISysRoleService iSysRoleService;
    @Test
    public void test2() {
        System.out.println(iSysRoleService.findAllRole());
    }
}
