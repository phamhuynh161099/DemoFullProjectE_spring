package com.fpt.shop.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void delete(String storedFileName) throws IOException;

	Path load(String filename);

	Resource loadAsResource(String filename);

	void store(MultipartFile file, String storedFileName);

	String getStoredFilename(MultipartFile file, String id);

}
