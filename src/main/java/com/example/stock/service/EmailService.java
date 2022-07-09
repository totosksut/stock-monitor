package com.example.stock.service;

import java.util.List;

import com.example.stock.model.Prop;

public interface EmailService {

	List<Prop> findEmailAll() throws Exception;
	
	Prop getEmail(Long stockId) throws Exception;
	
	void saveEmail(Prop prop) throws Exception;
	
	void removeEmail(Long stockId) throws Exception;
}
