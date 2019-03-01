package xin.sunce.mybatis.dao;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xin.sunce.mybatis.ApplicationTest;
import xin.sunce.mybatis.entity.Customer;

public class CustomerDaoTest extends ApplicationTest {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSelect() {
        Customer customer = customerDao.selectByPrimaryKey(1L);
        LOGGER.info(customer.toString());
    }


}