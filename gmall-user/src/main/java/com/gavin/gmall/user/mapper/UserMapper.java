package com.gavin.gmall.user.mapper;

import com.gavin.gmall.user.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: gavin
 * @GitHub: https://github.com/gavin-yyj
 * @Date: Created in 15:54 2020/6/7
 * @Description:
 */
public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUsers();
}
