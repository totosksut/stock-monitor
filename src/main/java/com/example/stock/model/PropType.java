package com.example.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_b_prop_type")
public class PropType {

	@Id
	@GeneratedValue
	private Long propTypeId;
	
	private String propName;

	public Long getPropTypeId() {
		return propTypeId;
	}

	public void setPropTypeId(Long propTypeId) {
		this.propTypeId = propTypeId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}
	
	
}
