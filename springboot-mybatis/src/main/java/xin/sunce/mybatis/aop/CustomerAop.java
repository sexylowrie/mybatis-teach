package xin.sunce.mybatis.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 测试aop
 *
 * @author lowrie
 * @date 2019-03-21
 */
@Aspect
public class CustomerAop {

    @Pointcut("execution(public * xin.sunce.mybatis.Application.testAop(..))")
    public void testAop() {
    }


    @Before(value = "testAop()")
    public void before() {
        System.out.println("before");
    }

}
