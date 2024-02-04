package com.trip.prepare.scrapper.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "all_district_destination_data")
public class DistrictDestination {
	
	@EmbeddedId
	@Embedded
	private DistrictDestinationKey districtDestinationKey;
	//private String id_state_district_and_destination;
	private String state_name;

}
