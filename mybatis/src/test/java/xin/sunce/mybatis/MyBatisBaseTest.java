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

public class MyBatisBaseTest {

    private SqlSessionFactory sessionFactory;

    private Logger LOGGER = Logger.getLogger(MyBatisBaseTest.class.getName());

    @Before
    public void getSqlSessionFactory() throws IOException {
        String configPath = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(configPath);
        sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            CustomerDao mapper = sqlSession.getMapper(CustomerDao.class);
            Customer customer = new Customer();
            customer.setId(1L);
            customer.setOptimistic(0L);
            customer.setName("Customer.ce");
            LOGGER.info("insert into table Customer: " + customer.toString());
            mapper.insert(customer);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testQuery() {
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            CustomerDao mapper = sqlSession.getMapper(CustomerDao.class);
            Customer customer = mapper.selectByPrimaryKey(1L);
            LOGGER.info("query table Customer result: " + customer.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testOriginQuery() {
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            Customer customer = (Customer) sqlSession.selectOne("xin.sunce.mybatis.dao.CustomerDao.selectByPrimaryKey", 1L);
            LOGGER.info("query table Customer result: " + customer.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            CustomerDao mapper = sqlSession.getMapper(CustomerDao.class);
            Customer customer = new Customer();
            customer.setId(1L);
            customer.setName("ce.Customer");
            int num = mapper.updateByPrimaryKey(customer);
            LOGGER.info("update table Customer result: " + customer.toString() + ",effected " + num + " rows");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
