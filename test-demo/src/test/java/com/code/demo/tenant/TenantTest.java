package com.code.demo.tenant;

import com.code.demo.tenant.mapper.UserMapper;
import com.code.demo.tenant.pojo.User;
import com.code.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
public class TenantTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        userMapper.insert(user);
    }

    @Test
    public void find() {
        User user = userMapper.selectOne(new LambdaQueryWrapperX<User>().eq(User::getUsername, "admin"));
        System.out.println(user);
    }

}