package com.example.stock.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.stock.model.Prop;
import com.example.stock.repository.PropRepository;
import com.example.stock.repository.PropTypeRepository;

@Repository
public class EmailDAO {

	@Autowired
	PropRepository propRepo;
	
	@Autowired
	PropTypeRepository propTypeRepo;

	@Transactional(rollbackFor = Exception.class)
	public void saveEmail(Prop prop) throws Exception {
		try {
			propRepo.save(prop);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteEmail(Long id ) throws Exception {
		try {
			propRepo.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Prop> getEmailSender() throws Exception {
		try {
			return propRepo.findByPropType(7L);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Prop> getEmailReceiver() throws Exception {
		try {
			return propRepo.findByPropType(1L);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Prop getEmailDesc(Long id) throws Exception{
		try {
			return propRepo.findByPropId(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
