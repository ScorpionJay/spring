/*
 * Copyright the original author or authors.
 */

package com.vo;

import java.io.Serializable;

/**
 * 投资产品VO
 * 
 * @author jay
 * @since 2017/1/23
 */
public class InverstVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	private String id;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 年利率
	 */
	private String rate;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 期限
	 */
	private String typeTerm;

	public InverstVo() {
		super();
	}

	public InverstVo(String type, String rate, String description, String typeTerm) {
		super();
		this.type = type;
		this.rate = rate;
		this.description = description;
		this.typeTerm = typeTerm;
	}

	public InverstVo(String id, String type, String rate, String description, String typeTerm) {
		super();
		this.id = id;
		this.type = type;
		this.rate = rate;
		this.description = description;
		this.typeTerm = typeTerm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeTerm() {
		return typeTerm;
	}

	public void setTypeTerm(String typeTerm) {
		this.typeTerm = typeTerm;
	}

	@Override
	public String toString() {
		return "InverstVo [id=" + id + ", type=" + type + ", rate=" + rate + ", description=" + description
				+ ", typeTerm=" + typeTerm + "]";
	}

}
