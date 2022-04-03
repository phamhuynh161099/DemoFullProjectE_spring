package com.fpt.shop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
	private String location;
	
//	Nho bam bong den de add file pomlxml
}
