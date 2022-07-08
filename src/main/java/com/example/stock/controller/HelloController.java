package com.example.stock.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.example.stock.config.ConfigUtility;

@Controller
public class HelloController {
	
	//@Autowired
	//private ConfigUtility configUtil;

    //@GetMapping({"/","/hello"})
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
        defaultValue = "World", required = true) String name, Model model) {
        model.addAttribute("name", name);
        //System.out.println("DB USERNAME : "+configUtil.readProp().getProperty("DB_USERNAME"));
        //System.out.println("DB PASSWORD : "+configUtil.readProp().getProperty("DB_PASSWORD"));
        return "hello";
    }
    
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    @RequestMapping("/stockFile")
    public String stockFile() {
        return "stockFile";
    }
    @RequestMapping("/email")
    public String about() {
        return "email";
    }
}