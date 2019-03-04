package xin.sunce.mybatis.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xin.sunce.mybatis.Application;
import xin.sunce.mybatis.entity.Customer;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerDaoTest {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSelect() {
        Customer customer = customerDao.selectByPrimaryKey(1L);
        LOGGER.info(customer.toString());
    }


}