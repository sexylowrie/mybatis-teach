package xin.sunce.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import xin.sunce.mybatis.dao.ClassDao;
import xin.sunce.mybatis.dao.StudentDao;

import java.io.IOException;
import java.io.InputStream;

/**
 * 二级缓存示例
 *
 * @author ce.sun
 */
public class MyBatisCachingExecutorTest {

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
        SqlSession sqlSession1 = sessionFactory.openSession(true);
        SqlSession sqlSession2 = sessionFactory.openSession(true);
        try {
            StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
            StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
            LOGGER.info(studentDao1.getStudentById(1));
            LOGGER.info(studentDao2.getStudentById(1));
        } finally {
            sqlSession1.close();
            sqlSession2.close();
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession1 = sessionFactory.openSession(true);
        SqlSession sqlSession2 = sessionFactory.openSession(true);
        try {
            StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
            StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
            LOGGER.info(studentDao1.getStudentById(1));
            sqlSession1.commit();
            LOGGER.info(studentDao2.getStudentById(1));
        } finally {
            sqlSession1.close();
            sqlSession2.close();
        }
    }

    @Test
    public void test3() {
        SqlSession sqlSession1 = sessionFactory.openSession(true);
        SqlSession sqlSession2 = sessionFactory.openSession(true);
        SqlSession sqlSession3 = sessionFactory.openSession(true);
        try {
            StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
            StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
            StudentDao studentDao3 = sqlSession3.getMapper(StudentDao.class);
            LOGGER.info("会话一：" + studentDao1.getStudentById(1));
            sqlSession1.commit();
            LOGGER.info("会话二，studentDao3更新之前：" + studentDao2.getStudentById(1));
            studentDao3.updateStudentName("测测", 1);
            sqlSession3.commit();
            LOGGER.info("会话二，studentDao3更新之后：" + studentDao2.getStudentById(1));
        } finally {
            sqlSession1.close();
            sqlSession2.close();
            sqlSession3.close();
        }
    }

    @Test
    public void test4() {
        SqlSession sqlSession1 = sessionFactory.openSession(true);
        SqlSession sqlSession2 = sessionFactory.openSession(true);
        SqlSession sqlSession3 = sessionFactory.openSession(true);
        try {
            StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
            StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
            ClassDao classDao = sqlSession3.getMapper(ClassDao.class);
            LOGGER.info("会话一：" + studentDao1.getStudentByIdWithClassInfo(1));
            sqlSession1.commit();
            LOGGER.info("会话二，classDao更新之前：" + studentDao2.getStudentByIdWithClassInfo(1));
            classDao.updateClassName("特殊一班", 1);
            sqlSession3.commit();
            LOGGER.info("会话二，classDao更新之后：" + studentDao2.getStudentByIdWithClassInfo(1));
        } finally {
            sqlSession1.close();
            sqlSession2.close();
            sqlSession3.close();
        }
    }


}
