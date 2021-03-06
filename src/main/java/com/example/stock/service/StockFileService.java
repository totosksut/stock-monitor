package com.example.stock.service;

import java.util.List;

import com.example.stock.model.Prop;
import com.example.stock.model.PropType;

public interface StockFileService {

	List<Prop> findStockFileAll() throws Exception;
	
	Prop getStockFile(Long stockId) throws Exception;
	
	void saveStockFile(Prop prop) throws Exception;
	
	void removeStockFile(Long stockId) throws Exception;
	
	List<PropType> findAllPropType() throws Exception;
	
	Prop getDBFLocation(Long propId) throws Exception;
	
	void saveDBFLocation(Prop prop) throws Exception;
	
	void removeDBFLocation(Long propId) throws Exception;
	
	List<Prop> findDBFLocationAll() throws Exception;
}
