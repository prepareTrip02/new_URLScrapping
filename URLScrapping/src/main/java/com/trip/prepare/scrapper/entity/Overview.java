package com.trip.prepare.scrapper.entity;

import java.util.List;

import com.trip.prepare.scrapper.conf.ListToJsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Overview {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer sn;
		// Overview
		@Column(columnDefinition = "TEXT")
		private String googleMapUrl;
		private String cid;
		private String name;
		private String catogery;
		private String address;
		private String phone;
		private String loc_plus_code;
		private String website;
		private String menu;
		private String Opening_hours;
		
		// Reviews
		@Column(columnDefinition = "TEXT")
		@Convert(converter = ListToJsonConverter.class)	
		private List<String> starReviewList;
		private String totalStar;
		private String totalReview;
		// refineReviewList for other then Hotels
		@Convert(converter = ListToJsonConverter.class)	
		@Column(columnDefinition = "TEXT")
		private List<String> refineReviewList;
		// categoryReviewList for Hotels
		@Convert(converter = ListToJsonConverter.class)	
		@Column(columnDefinition = "TEXT")
		private List<String> categoryReviewList;
		
		@Embedded
		private Destination destination;
		
		@OneToOne(cascade = CascadeType.ALL,mappedBy="overview")
		private About about;
		
		@OneToMany(cascade = CascadeType.ALL,mappedBy ="overview")
		private List<Price> priceList;
		
		@OneToMany(cascade = CascadeType.ALL,mappedBy ="overview")
		private List<Review> reviewList;
		

}
