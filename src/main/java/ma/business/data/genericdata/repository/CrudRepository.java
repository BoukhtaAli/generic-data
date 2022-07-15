package ma.business.data.genericdata.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import lombok.NonNull;
import ma.business.data.genericdata.entity.CrudSuperEntity;

@NoRepositoryBean
public interface CrudRepository<T extends CrudSuperEntity,S extends Long> extends JpaRepository<T, S> {

    @Override
    Page<T> findAll(@NonNull Pageable pageable);

    @Override
    <S extends T> S save(@NonNull S entity);

    @Override
    Optional<T> findById(@NonNull S id);

    @Override
    boolean existsById(@NonNull S id);

    @Override
    void delete(@NonNull T entity);
}
