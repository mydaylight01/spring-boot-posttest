package com.ss.trainingspringboot.config;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(description = "REST Api default path")
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui/";
    }
}
