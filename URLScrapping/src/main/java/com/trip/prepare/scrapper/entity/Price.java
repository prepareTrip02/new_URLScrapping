package com.trip.prepare.scrapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;
	@Column(columnDefinition = "TEXT")
	private String priceLink;
	private String websiteLogo;
	private String webSiteName;
	private String price;
	private String otherInfo;
	
	@Exclude   //exclude from toString method
	@ManyToOne()
	private Overview overview; 


}
