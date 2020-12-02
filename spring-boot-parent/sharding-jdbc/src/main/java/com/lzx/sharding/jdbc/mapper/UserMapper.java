package com.lzx.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.sharding.jdbc.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
