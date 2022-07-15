package ma.business.data.genericdata.repository;


import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import ma.business.data.genericdata.entity.ProcessableEntity;
import ma.business.data.genericdata.enums.State;

@NoRepositoryBean
public interface ProcessableRepository<T extends ProcessableEntity<S>, S extends State> extends JpaRepository<T, Long> {

    @Query("update #{#entityName} t set t.lockDate = :lockDate where t.id = :id")
    @Transactional
    @Modifying
    void lockEntity(@Param("id") Long id, @Param("lockDate") LocalDateTime lockDate);

    @Query("update #{#entityName} t set t.lockDate = null where t.id = :id")
    @Transactional
    @Modifying
    void unlockEntity(@Param("id") Long id);
}
