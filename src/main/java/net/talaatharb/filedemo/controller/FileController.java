package net.talaatharb.filedemo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.talaatharb.filedemo.model.DBFile;
import net.talaatharb.filedemo.payload.UploadFileResponse;
import net.talaatharb.filedemo.service.DBFileStorageService;

/**
 * Controller for the upload / download of files
 * 
 * @author mharb
 *
 */
@RestController
public class FileController {

	@Autowired
	private DBFileStorageService dbFileStorageService;

	/**
	 * Download end point
	 * 
	 * @param fileId
	 * @return The file
	 */
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable UUID fileId) {
		// Load file from database
		DBFile dbFile = dbFileStorageService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	/**
	 * Upload end point
	 * 
	 * @param file
	 * @return The details of the upload
	 */
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		DBFile dbFile = dbFileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId().toString()).toUriString();

		return new UploadFileResponse(fileDownloadUri, dbFile.getFileName(), file.getContentType(), file.getSize());
	}

	/**
	 * Upload end point for multiple files
	 * 
	 * @param files
	 * @return The details of the uploads
	 */
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(this::uploadFile).collect(Collectors.toList());
	}

}
