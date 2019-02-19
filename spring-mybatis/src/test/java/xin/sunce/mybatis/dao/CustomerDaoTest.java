package xin.sunce.mybatis.dao;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xin.sunce.mybatis.entity.Customer;

public class CustomerDaoTest {

    private Logger LOGGER = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Before
    public void getContext() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testSelect() {
        CustomerDao customerDao = applicationContext.getBean(CustomerDao.class);
        Customer customer = customerDao.selectByPrimaryKey(1L);
        LOGGER.info(customer);
    }

}