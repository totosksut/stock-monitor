package com.example.stock.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.stock.model.Prop;
import com.example.stock.model.PropType;
import com.example.stock.repository.PropRepository;
import com.example.stock.repository.PropTypeRepository;

@Repository
public class StockFileDAO {
	
	@Autowired
	PropRepository propRepo;
	
	@Autowired
	PropTypeRepository propTypeRepo;

	@Transactional(rollbackFor = Exception.class)
	public void saveStockFile(Prop prop) throws Exception {
		try {
			propRepo.save(prop);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<PropType> getAllPropType() throws Exception {
		try {
			return propTypeRepo.findAll();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
