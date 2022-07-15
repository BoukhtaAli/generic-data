package ma.business.data.genericdata.repository;

import lombok.NonNull;
import ma.business.data.genericdata.entity.CrudSuperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<T extends CrudSuperEntity,Long> extends JpaRepository<T, Long> {

    @Override
    Page<T> findAll(@NonNull Pageable pageable);

    @Override
    <S extends T> S save(@NonNull S entity);

    @Override
    Optional<T> findById(@NonNull Long id);

    @Override
    boolean existsById(@NonNull Long id);

    @Override
    void delete(@NonNull T entity);
}
