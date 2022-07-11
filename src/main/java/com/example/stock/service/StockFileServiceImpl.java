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
		return stockFileDAO.getStockFileList();
	}

	@Override
	public Prop getStockFile(Long stockId) throws Exception {
		return stockFileDAO.getStockFIle(stockId);
	}

	@Override
	public void saveStockFile(Prop prop) throws Exception {
		stockFileDAO.saveStockFile(prop);
		
	}

	@Override
	public void removeStockFile(Long stockId) throws Exception {
		stockFileDAO.deleteStockFile(stockId);
	}

	@Override
	public List<PropType> findAllPropType() throws Exception {
		return stockFileDAO.getAllPropType();
	}

	@Override
	public Prop getDBFLocation(Long propId) throws Exception {
		return stockFileDAO.getStockFIle(propId);
	}

	@Override
	public void saveDBFLocation(Prop prop) throws Exception {
		stockFileDAO.saveStockFile(prop);
		
	}

	@Override
	public void removeDBFLocation(Long propId) throws Exception {
		stockFileDAO.deleteStockFile(propId);
		
	}

	@Override
	public List<Prop> findDBFLocationAll() throws Exception {
		return stockFileDAO.getDBFLocationList();
	}

}
