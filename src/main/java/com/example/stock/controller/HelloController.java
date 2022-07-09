package com.example.stock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.stock.model.Prop;
import com.example.stock.model.PropType;
import com.example.stock.service.EmailService;
import com.example.stock.service.StockFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.example.stock.config.ConfigUtility;

@Controller
public class HelloController {
	
	@Autowired
	private StockFileService stockFileService;
	
	@Autowired
	private EmailService emailService;
	
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
    		//List<PropType> listPropType = stockFileService.findAllPropType();
    		//for(PropType row : listPropType) {
    		//	logger.info(row.getPropName());
    		//}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
        return "stockFile";
    }
    
    @RequestMapping("/email")
    public String email(Model model) {
    	try {
    		List<Prop> listSender = emailService.findEmailSender();
    		List<Prop> listReceiver = emailService.findEmailReceiverAll();
    		if(listSender!=null && listSender.size()>0) {
    			for(Prop row : listSender) {
    				if(row.getPropName().equals("E_MAIL_SENDER")) {
    					model.addAttribute("emailSender",row.getPropPath());
    					model.addAttribute("emailSenderID",row.getPropId());
    				}else if(row.getPropName().equals("E_MAIL_SENDER_PASS")) {
    					model.addAttribute("emailSenderPass",row.getPropPath());
    					model.addAttribute("emailSenderPassID",row.getPropId());
    				}
    			}
    		}else {
    			model.addAttribute("emailSender","");
				model.addAttribute("emailSenderID","");
				model.addAttribute("emailSenderPass","");
				model.addAttribute("emailSenderPassID","");
    		}
    		
    		model.addAttribute("listReceiver",listReceiver);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
        return "email";
    }
    
    @RequestMapping("/saveEmailSender")
    public String saveEmailSender(HttpServletRequest request , HttpServletResponse response) {
    	
    	try {
    		
    		String emailSender = request.getParameter("emailSender");
    		String emailSenderId = request.getParameter("emailSenderID");
    		
    		String emailSenderPass = request.getParameter("emailSenderPass");
    		String emailSenderPassID = request.getParameter("emailSenderPassID");
    		
    		if(emailSenderId.isEmpty() || emailSenderPassID.isEmpty()) {
    			Prop objEmailSender = new Prop();
    			objEmailSender.setPropName("E_MAIL_SENDER");
    			objEmailSender.setPropPath(emailSender);
    			objEmailSender.setPropType(7L);
    			
    			
    			Prop objEmailSenderPass = new Prop();
    			objEmailSenderPass.setPropName("E_MAIL_SENDER_PASS");
    			objEmailSenderPass.setPropPath(emailSenderPass);
    			objEmailSenderPass.setPropType(7L);
    			
    			emailService.saveEmail(objEmailSender);
    			emailService.saveEmail(objEmailSenderPass);
    			
    		}else {
    			Prop objEmailSender = emailService.getEmail(Long.valueOf(emailSenderId));
    			Prop objEmailSenderPass = emailService.getEmail(Long.valueOf(emailSenderPassID));
    			
    			objEmailSender.setPropPath(emailSender);
    			objEmailSenderPass.setPropPath(emailSenderPass);
    			
    			emailService.saveEmail(objEmailSender);
    			emailService.saveEmail(objEmailSenderPass);
    		}
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/email";
    }
    
    @RequestMapping("/saveEmailReceiver")
    public String saveEmailReceiver(HttpServletRequest request , HttpServletResponse response) {
    	
    	try {
    		
    		String emailReceiver = request.getParameter("emailReceiver");
    		String emailReceiverId = request.getParameter("emailReceiverID");
    		String emailReceiverNew = request.getParameter("emailReceiverNew");
    		
    		
    		if(emailReceiverId==null) {
    			Prop objEmailReceiver = new Prop();
    			objEmailReceiver.setPropName("E_MAIL_RECEIVER");
    			objEmailReceiver.setPropPath(emailReceiver);
    			objEmailReceiver.setPropType(1L);
    			
    			
    			emailService.saveEmail(objEmailReceiver);
    			
    		}else {
    			Prop objEmailReceiver = emailService.getEmail(Long.valueOf(emailReceiverId));
    			
    			objEmailReceiver.setPropPath(emailReceiverNew);
    			
    			emailService.saveEmail(objEmailReceiver);
    		}
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/email";
    }
    
    @RequestMapping("/deleteEmailReceiver")
    public String deleteEmailReceiver(HttpServletRequest request , HttpServletResponse response) {
    	
    	try {
    		
    		String emailReceiverId = request.getParameter("emailReceiverDeleteID");
    		emailService.removeEmail(Long.valueOf(emailReceiverId));
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/email";
    }
    
    @RequestMapping("/dbLocation")
    public String dbLocation() {
        return "dbLocation";
    }
}