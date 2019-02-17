package xin.sunce.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import xin.sunce.mybatis.dao.CustomerDao;
import xin.sunce.mybatis.entity.Customer;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一级缓存示例
 * @author ce.sun
 */
public class MyBatisCacheTest {

    private SqlSessionFactory sessionFactory;

    private Logger LOGGER = Logger.getLogger(MyBatisBaseTest.class.getName());

    @Before
    public void getSqlSessionFactory() throws IOException {
        String configPath = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configPath);
        sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void test1() {
        SqlSession sqlSession = sessionFactory.openSession(true);
        CustomerDao customerDao;
        try {
            customerDao = sqlSession.getMapper(CustomerDao.class);
            LOGGER.info(customerDao.selectByPrimaryKey(1L));
            LOGGER.info(customerDao.selectByPrimaryKey(1L));
            LOGGER.info(customerDao.selectByPrimaryKey(1L));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession = sessionFactory.openSession(true);
        CustomerDao customerDao;
        try {
            customerDao = sqlSession.getMapper(CustomerDao.class);
            Customer customer = customerDao.selectByPrimaryKey(1L);
            LOGGER.info(customer);
            customer.setName("wang.er");
            customerDao.updateByPrimaryKey(customer);
            LOGGER.info(customerDao.selectByPrimaryKey(1L));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() {
        SqlSession sqlSession1 = sessionFactory.openSession(true);
        SqlSession sqlSession2 = sessionFactory.openSession(true);
        try {
            CustomerDao customerDao1 = sqlSession1.getMapper(CustomerDao.class);
            CustomerDao customerDao2 = sqlSession2.getMapper(CustomerDao.class);
            LOGGER.info("会话一：" + customerDao1.selectByPrimaryKey(1L));
            LOGGER.info("会话一：" + customerDao1.selectByPrimaryKey(1L));
            Customer customer = new Customer();
            customer.setId(1L);
            customer.setName("wang.er");
            customerDao2.updateByPrimaryKey(customer);
            LOGGER.info("会话二：" + customerDao2.selectByPrimaryKey(1L));
            LOGGER.info("会话一：" + customerDao1.selectByPrimaryKey(1L));
        } finally {
            sqlSession1.close();
            sqlSession2.close();
        }
    }

}
