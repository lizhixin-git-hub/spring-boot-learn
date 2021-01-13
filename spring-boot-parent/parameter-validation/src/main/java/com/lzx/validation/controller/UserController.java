package com.lzx.validation.controller;

import com.lzx.validation.entity.User;
import com.lzx.validation.groups.Groups;
import com.lzx.validation.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/save")
    public Result<Object> save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            return new Result<>(Boolean.FALSE, 400, "非法参数 !", map);
        }
        return new Result<>(Boolean.TRUE, 200, "参数正确 !", user);
    }

    @PostMapping("/advice")
    public Result<Object> advice(@Valid User user) {
        return new Result<>(Boolean.TRUE, 200, "参数正确 !", user);
    }

    @PostMapping("/groups")
    public Result<User> groups(@Validated(Groups.Add.class) User user) {
        return new Result<>(Boolean.TRUE, 200, "参数正确 !", user);
    }

}
