package net.talaatharb.filedemo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Database file entity
 * 
 * @author mharb
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class DBFile {

	/**
	 * File contents
	 */
	@Lob
	private byte[] data;

	/**
	 * File name
	 */
	private String fileName;

	/**
	 * File type
	 */
	private String fileType;

	/**
	 * ID of file
	 */
	@Id
	private final UUID id = UUID.randomUUID();
}
