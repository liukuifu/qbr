package com.nq.qbr.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class AddressProvincesEntity implements Serializable {
	private ArrayList<GoodsAddressEntity> addressEntity;
	private String provinces;

	public ArrayList<GoodsAddressEntity> getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(ArrayList<GoodsAddressEntity> addressEntity) {
		this.addressEntity = addressEntity;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

}
