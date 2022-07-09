package com.example.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.dao.StockFileDAO;
import com.example.stock.model.Prop;
import com.example.stock.model.PropType;

@Service
public class StockFileServiceImpl implements StockFileService{
	
	@Autowired
    private StockFileDAO stockFileDAO;


	@Override
	public List<Prop> findStockFileAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prop getStockFile(Long stockId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveStockFile(Prop prop) throws Exception {
		stockFileDAO.saveStockFile(prop);
		
	}

	@Override
	public void removeStockFile(Long stockId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropType> findAllPropType() throws Exception {
		// TODO Auto-generated method stub
		return stockFileDAO.getAllPropType();
	}

}
