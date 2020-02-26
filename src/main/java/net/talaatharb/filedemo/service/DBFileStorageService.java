package net.talaatharb.filedemo.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.talaatharb.filedemo.exception.FileStorageException;
import net.talaatharb.filedemo.exception.MyFileNotFoundException;
import net.talaatharb.filedemo.model.DBFile;
import net.talaatharb.filedemo.repository.DBFileRepository;

/**
 * Upload / download service
 * 
 * @author mharb
 *
 */
@Service
public class DBFileStorageService {

	@Autowired
	private DBFileRepository dbFileRepository;

	/**
	 * Retrieve file from database if it exists
	 * 
	 * @param fileId
	 * @return The file model
	 */
	public DBFile getFile(final UUID fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

	/**
	 * Save the file in the database
	 * 
	 * @param file
	 * @return The file after saving
	 */
	public DBFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(file.getBytes(), fileName, file.getContentType());

			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
}
