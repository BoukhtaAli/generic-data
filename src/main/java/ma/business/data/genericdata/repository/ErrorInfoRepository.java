package ma.business.data.genericdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.business.data.genericdata.entity.ErrorInfo;

@Repository
public interface ErrorInfoRepository extends JpaRepository<ErrorInfo, Long> {
}
