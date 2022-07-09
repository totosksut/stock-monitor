package com.example.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.stock.model.PropType;
import com.example.stock.service.StockFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.example.stock.config.ConfigUtility;

@Controller
public class HelloController {
	
	@Autowired
	private StockFileService stockFileService;
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

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
    	try {
    		List<PropType> listPropType = stockFileService.findAllPropType();
    		for(PropType row : listPropType) {
    			logger.info(row.getPropName());
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
        return "stockFile";
    }
    
    @RequestMapping("/email")
    public String email() {
        return "email";
    }
    
    @RequestMapping("/dbLocation")
    public String dbLocation() {
        return "dbLocation";
    }
}