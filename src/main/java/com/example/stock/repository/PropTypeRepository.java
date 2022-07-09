package com.example.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.model.PropType;;

public interface PropTypeRepository extends JpaRepository<PropType, Long>{

	PropType findByPropTypeId(Long propTypeId);

	List<PropType> findAll();
}
