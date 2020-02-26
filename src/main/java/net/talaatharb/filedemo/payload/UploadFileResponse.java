package net.talaatharb.filedemo.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Response to upload of file
 * 
 * @author mharb
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class UploadFileResponse {
	/**
	 * File download URL
	 */
	private String fileDownloadUri;

	/**
	 * File name
	 */
	private String fileName;

	/**
	 * File type
	 */
	private String fileType;

	/**
	 * Size of file
	 */
	private long size;
}
