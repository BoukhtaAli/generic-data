package ma.business.data.genericdata.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.NonNull;
import ma.business.data.genericdata.entity.CrudSuperEntity;
import ma.business.data.genericdata.exception.BusinessException;
import ma.business.data.genericdata.repository.CrudRepository;
import ma.business.data.genericdata.utils.ExceptionUtils;

public abstract class AbstractCrudService<T extends CrudSuperEntity, R extends Long> {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected abstract CrudRepository<T, R> getRepository();

	protected abstract Page<T> findALl(@NonNull Pageable pageable);

	protected abstract T createOrUpdate(@NonNull T entity);

	protected abstract Optional<T> findById(@NonNull R id);

	protected abstract boolean existsById(@NonNull R id);

	protected abstract void delete(@NonNull T entity);

	protected void manageException(final T entity, Exception exception, final String message) throws BusinessException {
		log.error(ExceptionUtils.getStackTrace(exception));
		throw handleExceptionAdditionalData(entity,
				new BusinessException().withMessage(message).withTime(LocalDateTime.now()));
	}

	protected abstract BusinessException handleExceptionAdditionalData(final T entity,
	                                                                   BusinessException businessException);
}
