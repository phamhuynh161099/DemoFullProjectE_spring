package com.fpt.shop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.fpt.shop.config.StorageProperties;
import com.fpt.shop.exception.StorageException;
import com.fpt.shop.exception.StorageFileNotFoundException;
import com.fpt.shop.service.StorageService;

/*Nho then thu vien apache commons io*/

@Service
public class FileSystemStorageServiceImpl implements StorageService {
	private final Path rootLocation;

	@Override
	public String getStoredFilename(MultipartFile file, String id) {
		String ext =FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}

	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String storedFileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to stores empty file");
			}

			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();

			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
					throw new StorageException("Cannot store fiel outside current directory");
			}
			 try(InputStream inputStream=file.getInputStream()){
				 Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
			 }
			
		} catch (Exception e) {
			throw new StorageException("Failed to store file",e);
		}
	}
	
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file=load(filename);
			Resource resource=new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			
			throw new StorageFileNotFoundException("Could not read file"+filename);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file"+filename);
			// TODO: handle exception
		}
	}
	
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	@Override
	public void delete(String storedFileName) throws IOException{
		Path destinationFile=rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();
		
		Files.delete(destinationFile);
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		}catch (Exception e) {
			// TODO: handle exception
			throw new StorageException("Could not inintialize storage",e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
