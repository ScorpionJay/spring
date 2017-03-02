/*
 * Copyright the original author or authors.
 */

package com.one.vo;

import java.io.Serializable;

/**
 * Created by jay on 2017/1/23.
 */
public class InverstVo implements Serializable{

    private static final long serialVersionUID = 1L;

   

    @Override
	public String toString() {
		return "InverstVo [id=" + id + ", type=" + type + ", yearRate=" + yearRate + ", productType=" + productType
				+ ", productDesc=" + productDesc + ", typeTerm=" + typeTerm + "]";
	}

	public InverstVo() {
    }

    public InverstVo(String type, String yearRate, String productType, String productDesc, String typeTerm) {
        this.type = type;
        this.yearRate = yearRate;
        this.productType = productType;
        this.productDesc = productDesc;
        this.typeTerm = typeTerm;
    }

    
    
    public InverstVo(String id, String type, String yearRate, String productType, String productDesc, String typeTerm) {
		super();
		this.id = id;
		this.type = type;
		this.yearRate = yearRate;
		this.productType = productType;
		this.productDesc = productDesc;
		this.typeTerm = typeTerm;
	}



	private String id;
    
    private String type;

    private String yearRate;

    private String productType;

    private String productDesc;

    private String typeTerm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYearRate() {
        return yearRate;
    }

    public void setYearRate(String yearRate) {
        this.yearRate = yearRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getTypeTerm() {
        return typeTerm;
    }

    public void setTypeTerm(String typeTerm) {
        this.typeTerm = typeTerm;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
