package net.talaatharb.filedemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.talaatharb.filedemo.model.DBFile;

/**
 * File repository
 * 
 * @author mharb
 *
 */
@Repository
public interface DBFileRepository extends JpaRepository<DBFile, UUID> {

}
