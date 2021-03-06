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
	
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteStockFile(Long id ) throws Exception {
		try {
			propRepo.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Prop> getStockFileList() throws Exception {
		try {
			return propRepo.findByPropType(3L);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Prop> getDBFLocationList() throws Exception {
		try {
			return propRepo.findByPropType(2L);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Prop getStockFIle(Long id) throws Exception{
		try {
			return propRepo.findByPropId(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
