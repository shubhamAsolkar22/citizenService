package com.fkog.citizenService.entity;

public class Citizen {

	private long id;
	
	private String name;
	
	private long vaccinationCenterId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVaccinationCenterId() {
		return vaccinationCenterId;
	}

	public void setVaccinationCenterId(long vaccinationCenterId) {
		this.vaccinationCenterId = vaccinationCenterId;
	}
	
}
