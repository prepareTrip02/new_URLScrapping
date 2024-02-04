package com.trip.prepare.scrapper.entity;

import java.util.List;
import java.util.Map;

import com.trip.prepare.scrapper.conf.MapToJsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;
	
	@Embedded
	private Destination destination;
	
	private String cid;
	@Column(columnDefinition = "TEXT")
	private String google_map_url;
	private String latitude;
	private String longitude;
	
}
