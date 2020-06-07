package com.gavin.gmall.user.service;

import com.gavin.gmall.user.bean.UmsMember;
import com.gavin.gmall.user.bean.UmsMemberReceiveAddress;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:52 2020/6/7
 * @Description:
 */
public interface UserService {
    List<UmsMember> getAllUser();
    UmsMember getUserById(String id);
    UmsMember getUserByName(String name);

    Integer updateUser(UmsMember umsMember);
    Integer deleteUserById(String id);



    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
    UmsMemberReceiveAddress getReceiveAddressById(String id);
    Integer updateUmsMemberReceiveAddressById(String id);
    Integer deleteUmsMemberReceiveAddressById(String id);
}
