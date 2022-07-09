package com.example.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.dao.EmailDAO;
import com.example.stock.model.Prop;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	EmailDAO emailDao;

	@Override
	public List<Prop> findEmailSender() throws Exception {
		return emailDao.getEmailSender();
	}
	
	@Override
	public List<Prop> findEmailReceiverAll() throws Exception {
		return emailDao.getEmailReceiver();
	}

	@Override
	public Prop getEmail(Long stockId) throws Exception {
		return emailDao.getEmailDesc(stockId);
	}

	@Override
	public void saveEmail(Prop prop) throws Exception {
		emailDao.saveEmail(prop);
	}

	@Override
	public void removeEmail(Long stockId) throws Exception {
		emailDao.deleteEmail(stockId);
	}

}
