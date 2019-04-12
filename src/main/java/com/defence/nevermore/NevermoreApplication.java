package com.defence.nevermore;

import com.defence.nevermore.domain.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class NevermoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(NevermoreApplication.class, args);
    }

    @RequestMapping("/user/hello")
    @ResponseBody
    public Response hello() {
        String h = "1";
        return Response.success("hello");
    }



}
