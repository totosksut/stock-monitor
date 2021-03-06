package com.example.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.model.Prop;

public interface  PropRepository extends JpaRepository<Prop, Long>{

	Prop findByPropId(Long propId);

	List<Prop> findAll();
	
	List<Prop> findByPropType(Long propType);
	
	Prop findByPropNameAndPropType(String propName,Long propType);
}
