package com.example.stock.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.stock.model.Prop;
import com.example.stock.service.EmailService;
import com.example.stock.service.StockFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.example.stock.config.ConfigUtility;

@Controller
public class HelloController {
	
	
	@Autowired
	private EmailService emailService;
	
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
    public String stockFile(Model model) {
    	logger.info("stockFile");
    	try {
    		List<Prop> listStockFile = stockFileService.findStockFileAll();
    		model.addAttribute("listStockFIle",listStockFile);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
        return "stockFile";
    }
    
    @RequestMapping("/saveStockFile")
    public String saveStockFile(
    		 @RequestParam(value = "groupName", required = false) String groupName,
    		 @RequestParam(value = "pathFile", required = false) String pathFile,
    		 @RequestParam(value = "groupNameNew", required = false) String groupNameNew,
    		 @RequestParam(value = "pathFileNew", required = false) String pathFileNew,
    		 @RequestParam(value = "stockFileId", required = false) String stockFileId
    		) {
    	
    	try {
    		
    		if(stockFileId==null) {
    			Prop objStockFile = new Prop();
    			objStockFile.setPropName(groupName);
    			objStockFile.setPropPath(pathFile);
    			objStockFile.setPropType(3L);
    			
    			stockFileService.saveStockFile(objStockFile);
    		}else {
    			Prop objStockFile = stockFileService.getStockFile(Long.valueOf(stockFileId));
    			objStockFile.setPropName(groupNameNew);
    			objStockFile.setPropPath(pathFileNew);
    			
    			stockFileService.saveStockFile(objStockFile);
    		}
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/stockFile";
    }
    
    @RequestMapping("/deleteStockFile")
    public String deleteStockFile(@RequestParam(value = "stockIdDelete", required = false) String stockIdDelete) {
    	
    	try {

    		stockFileService.removeStockFile(Long.valueOf(stockIdDelete));
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/stockFile";
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
    public String saveEmailSender(
    		 @RequestParam(value = "emailSender", required = false) String emailSender,
    		 @RequestParam(value = "emailSenderId", required = false) String emailSenderId,
    		 @RequestParam(value = "emailSenderPass", required = false) String emailSenderPass,
    		 @RequestParam(value = "emailSenderPassID", required = false) String emailSenderPassID
    		) {
    	
    	try {
    		
			/*
			 * String emailSender = request.getParameter("emailSender"); String
			 * emailSenderId = request.getParameter("emailSenderID");
			 * 
			 * String emailSenderPass = request.getParameter("emailSenderPass"); String
			 * emailSenderPassID = request.getParameter("emailSenderPassID");
			 */
    		
    		if(emailSenderId==null || emailSenderPassID==null) {
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
    public String saveEmailReceiver(
    			@RequestParam(value = "emailReceiver", required = false) String emailReceiver,
    			@RequestParam(value = "emailReceiverID", required = false) String emailReceiverId,
    			@RequestParam(value = "emailReceiverNew", required = false) String emailReceiverNew
    		) {
    	
    	try {
    		
			/*
			 * String emailReceiver = request.getParameter("emailReceiver"); String
			 * emailReceiverId = request.getParameter("emailReceiverID"); String
			 * emailReceiverNew = request.getParameter("emailReceiverNew");
			 */
    		
    		
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
    public String deleteEmailReceiver(
    			@RequestParam(value = "emailReceiverDeleteID", required = false) String emailReceiverId
    		) {
    	
    	try {
    		
    		//String emailReceiverId = request.getParameter("emailReceiverDeleteID");
    		emailService.removeEmail(Long.valueOf(emailReceiverId));
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/email";
    }
    
    
    @RequestMapping("/dbLocation")
    public String dbLocation(Model model) {
    	logger.info("dbLocation");
    	try {
    		List<Prop> listDBF = stockFileService.findDBFLocationAll();
    		model.addAttribute("listDBF",listDBF);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
        return "dbLocation";
    }
    
    @RequestMapping("/savedbLocation")
    public String savedbLocation(
    		 @RequestParam(value = "groupName", required = false) String groupName,
    		 @RequestParam(value = "pathFile", required = false) String pathFile,
    		 @RequestParam(value = "dbfId", required = false) String dbfId
    		) {
    	
    	try {
    		
    		Prop objDBF = stockFileService.getDBFLocation(Long.valueOf(dbfId));
    		//objDBF.setPropName(groupName);
    		objDBF.setPropPath(pathFile);
			
			stockFileService.saveDBFLocation(objDBF);
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    	}
    	
        return "redirect:/dbLocation";
    }
}