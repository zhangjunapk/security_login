package org.zj.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zj.register.bean.User;
import org.zj.register.dao.UserMapper;
import org.zj.register.service.IUserService;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:16
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String email, String password) {
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email",email);
        criteria.andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user)==1;
    }

    @Override
    public boolean isExist(String email) {
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email",email);
        return userMapper.selectOneByExample(example)!=null;
    }
}
