package com.yyx.assetsys;

import com.yyx.assetsys.entity.User;
import com.yyx.assetsys.mapper.UserMapper;
import com.yyx.assetsys.service.UserService;
import com.yyx.assetsys.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserByName() {
        User mockUser = new User();

        Mockito.when(userMapper.findUserByName("testUser")).thenReturn(mockUser);

        User result = userService.findUserByName("testUser");

        assertEquals(mockUser, result);
    }

    @Test
    public void testRegisterUser() {
        User userToRegister = new User();

        userService.registerUser(userToRegister);

        Mockito.verify(userMapper).registerUser(userToRegister);
    }

    @Test
    public void testFindAllUser() {
        List<User> mockUserList = new ArrayList<>();
        mockUserList.add(new User());
        mockUserList.add(new User());

        Mockito.when(userMapper.findAllUser()).thenReturn(mockUserList);

        List<User> result = userService.findAllUser();

        assertEquals(mockUserList, result);
    }

    @Test
    public void testUpdateUser() {
        User userToUpdate = new User();

        userService.updateUser(userToUpdate);

        Mockito.verify(userMapper).updateUser(userToUpdate);
    }

    @Test
    public void testLoginUser() {
        User existingUser = new User();

        Mockito.when(userMapper.findUserByName("existingUser")).thenReturn(existingUser);

        // 测试成功登录情况
        User loggedInUser = userService.loginUser(new User());
        assertEquals(existingUser, loggedInUser);

        // 测试密码错误的情况
        User wrongPasswordUser = userService.loginUser(new User());
        assertNull(wrongPasswordUser);

        // 测试用户不存在的情况
        User nonExistingUser = userService.loginUser(new User());
        assertNull(nonExistingUser);
    }

    // 可以继续编写其他测试方法...
}
