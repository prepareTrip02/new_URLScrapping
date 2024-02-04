package com.trip.prepare.scrapper.entity;

import java.util.List;
import java.util.Map;

import com.trip.prepare.scrapper.conf.MapToJsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
public class About {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;
	@Column(columnDefinition = "TEXT")
	private String summary;
	/*
	 * @ElementCollection
	 * 
	 * @CollectionTable( name = "about_disabled_service_map", joinColumns
	 * = @JoinColumn(name = "about_sn") )
	 * 
	 * @MapKeyColumn(name = "map_key_column_name")
	 * 
	 * @Column(name = "list_value_column_name")
	 */
	@Convert(converter = MapToJsonConverter.class)
	@Column(columnDefinition = "TEXT")
    private Map<String, List<String>> aboutDisabledServiceMap;
	@Convert(converter = MapToJsonConverter.class)
	@Column(columnDefinition = "TEXT")
	private Map<String, List<String>> aboutEnabledServiceMap;
	
	@Exclude
	@OneToOne(cascade = CascadeType.ALL)
	private Overview overview; 
	
}
