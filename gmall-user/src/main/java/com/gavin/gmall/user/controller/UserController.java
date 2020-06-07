package com.gavin.gmall.user.controller;

import com.gavin.gmall.user.bean.UmsMember;
import com.gavin.gmall.user.bean.UmsMemberReceiveAddress;
import com.gavin.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:51 2020/6/7
 * @Description:
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Hello User";
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUsers(){
        List<UmsMember> umsMembers = userService.getAllUser();
        return umsMembers;
    }

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }

    @RequestMapping("getUmsMemberByName")
    @ResponseBody
    public UmsMember getUmsMemberByName(String name){
        UmsMember userByName = userService.getUserByName(name);
        return userByName;
    }

    @RequestMapping("getReceiveAddressById")
    @ResponseBody
    public UmsMemberReceiveAddress getReceiveAddressById(String id){
        UmsMemberReceiveAddress receiveAddressById = userService.getReceiveAddressById(id);
        return receiveAddressById;
    }
}
