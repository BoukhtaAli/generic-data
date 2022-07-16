package ma.business.data.genericdata.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import ma.business.data.genericdata.entity.ErrorInfo;
import ma.business.data.genericdata.entity.ProcessableEntity;
import ma.business.data.genericdata.enums.State;
import ma.business.data.genericdata.repository.ProcessableRepository;
import ma.business.data.genericdata.utils.ExceptionUtils;

public abstract class LifeCycleManager<T extends ProcessableEntity<S>, S extends State> {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected abstract ProcessableRepository<T, S> getRepository();

	protected abstract JpaRepository<ErrorInfo, Long> getErrorInfoRepository();

	public List<T> process(List<T> entityList) {

		if (!CollectionUtils.isEmpty(entityList)) {

			entityList.forEach(entity -> {
				try {
					execute(entity);
				} catch (Exception exception) {
					lockEntity(entity);
					updateErrorInfo(entity, exception);
				}
			});

		}

		return entityList;
	}

	protected abstract void execute(T entity);

	private void lockEntity(T entity) {
		getRepository().lockEntity(entity.getId(), LocalDateTime.now());
	}
	
	protected void updateErrorInfo(T entity, Exception exception) {
		log.error(ExceptionUtils.getStackTrace(exception));
		ErrorInfo errorInfo =
				new ErrorInfo().withErrorDate(LocalDateTime.now()).withDescription(exception.getMessage());
		entity.setErrorInfo(errorInfo);
		getErrorInfoRepository().save(errorInfo);
	}
}
