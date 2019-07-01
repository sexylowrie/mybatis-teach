package xin.sunce.mybatis.service;

import org.springframework.stereotype.Service;
import xin.sunce.mybatis.dao.CustomerDao;
import xin.sunce.mybatis.entity.Customer;

import javax.annotation.Resource;

/**
 * @author lowrie
 * @date 2019-03-21
 */
@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;

    public Customer findById(Long id) {
        return customerDao.selectByPrimaryKey(id);
    }

}
