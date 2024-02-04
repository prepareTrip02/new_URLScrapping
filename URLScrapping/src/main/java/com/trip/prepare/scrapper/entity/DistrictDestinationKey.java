package com.trip.prepare.scrapper.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class DistrictDestinationKey implements Serializable {
	
	private Integer place_id;
	private String place_name;
	private String place_type;
	

}
