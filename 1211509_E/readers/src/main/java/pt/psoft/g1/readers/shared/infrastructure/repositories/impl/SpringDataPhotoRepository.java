package pt.psoft.g1.readers.shared.infrastructure.repositories.impl;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pt.psoft.g1.readers.shared.model.Photo;
import pt.psoft.g1.readers.shared.repositories.PhotoRepository;

public interface SpringDataPhotoRepository extends PhotoRepository, CrudRepository<Photo, Long> {
    /*@Override
    @Query("SELECT p " +
            "FROM Photo p " +
            "WHERE p.pk = :photo_id")
    Optional<Photo> findById(@Param("photo_id") long id);*/
    @Override
    @Modifying
    @Transactional
    @Query("DELETE " +
            "FROM Photo p " +
            "WHERE p.photoFile = :photoFile")
    void deleteByPhotoFile(String photoFile);
}
