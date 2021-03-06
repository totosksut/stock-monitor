package com.example.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_b_prop")
public class Prop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long propId;
	
	private String propName;
	
	private String propPath;
	
	private Long propType;
	
	private String propCode;

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropPath() {
		return propPath;
	}

	public void setPropPath(String propPath) {
		this.propPath = propPath;
	}

	public Long getPropType() {
		return propType;
	}

	public void setPropType(Long propType) {
		this.propType = propType;
	}

	public String getPropCode() {
		return propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}
	
	
}
