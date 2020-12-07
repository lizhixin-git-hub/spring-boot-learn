package com.ycxc.upload.controller.upload;

import com.ycxc.upload.entity.log.DcLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller("testController")
@RequestMapping("/test")
public class TestController {

    public static void main(String[] args) {
        DcLog[] dcLog=new DcLog[]{new DcLog()};
        String[] strings = new String[]{"dddd", "ffff", "gggg", "hhhh"};
        List<String> lists = Stream.of(test(strings)).collect(Collectors.toList());
        lists.forEach(System.out::println);
    }

    private static <T> T test(T t) {
        return t;
    }

}
