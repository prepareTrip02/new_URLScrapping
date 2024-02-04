package com.trip.prepare.scrapper.entity;

import java.util.List;

import com.trip.prepare.scrapper.conf.ListToJsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;
	@Column(columnDefinition = "TEXT")
	private String reviewerURL;
	private String reviewerID;
	@Column(columnDefinition = "TEXT")
	private String imageURL;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String info;
	private String starRating;
	private String starRatingTime;
	@Column(columnDefinition = "TEXT")
	private String review;
	@Convert(converter = ListToJsonConverter.class)	
	@Column(columnDefinition = "TEXT")
	private List<String> reviewMeta;
	
	@Exclude
	@ManyToOne()
	private Overview overview;

}
