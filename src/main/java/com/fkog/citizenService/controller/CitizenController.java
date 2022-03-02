package com.fkog.citizenService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fkog.citizenService.entity.Citizen;
import com.fkog.citizenService.entity.VaccinationCenterDto;

@RestController
@RequestMapping(path = "/citizens")
public class CitizenController {
	private static List<Citizen> ALL_CITIZENS = new ArrayList<>();
	
	@Autowired
	private RestTemplate restTemplate;
	
	static {
		Citizen c = new Citizen();
		c.setId(1);
		c.setName("shubham");
		c.setVaccinationCenterId(10);
		
		ALL_CITIZENS.add(c);
		
	}
	
	@GetMapping(path = "/greeting")
	public ResponseEntity<?> hello(){
		return ResponseEntity.ok("Hello Citizens");
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getCitizenById(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(ALL_CITIZENS.stream().filter(c -> c.getId()==id).findFirst());
	}
	
	@GetMapping(path = "/{id}/vaccinationCenter")
	public ResponseEntity<?> getVaccinationCenterOfCitizen(@PathVariable(name = "id") Long id){
		Optional<Citizen> citizen = ALL_CITIZENS.stream().filter(c -> c.getId()==id).findFirst();
		
		return ResponseEntity.ok(citizen.map(c->{
			
			ResponseEntity<VaccinationCenterDto> response
			= restTemplate.getForEntity("http://VACCINATION-CENTER-SERVICE/vaccinationCenters/" + c.getVaccinationCenterId(), VaccinationCenterDto.class);
			return Optional.ofNullable(response.getBody());
		}));
		
	}
	
}
