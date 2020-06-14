package com.gavin.gmall.user.service.impl;

import bean.UmsMember;
import bean.UmsMemberReceiveAddress;
import com.alibaba.dubbo.config.annotation.Service;
import com.gavin.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.gavin.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:52 2020/6/7
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMemberList = userMapper.selectAllUsers();
        return umsMemberList;
    }

    @Override
    public UmsMember getUserById(String id) {
        UmsMember umsMember = userMapper.selectByPrimaryKey(id);
        return umsMember;
    }

    @Override
    public UmsMember getUserByName(String name) {
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(name);
        UmsMember umsMember1 = userMapper.selectOne(umsMember);
        return umsMember1;
    }

    @Override
    public Integer updateUser(UmsMember umsMember) {
        return null;
    }

    @Override
    public Integer deleteUserById(String id) {
       return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);

        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
        return umsMemberReceiveAddressList;
    }

    @Override
    public UmsMemberReceiveAddress getReceiveAddressById(String id) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = umsMemberReceiveAddressMapper.selectByPrimaryKey(id);
        return umsMemberReceiveAddress;
    }

    @Override
    public Integer updateUmsMemberReceiveAddressById(String id) {
        return null;
    }

    @Override
    public Integer deleteUmsMemberReceiveAddressById(String id) {
        return umsMemberReceiveAddressMapper.deleteByPrimaryKey(id);
    }
}
