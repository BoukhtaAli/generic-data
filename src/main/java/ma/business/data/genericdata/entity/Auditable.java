package ma.business.data.genericdata.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Auditable<U> {

    @CreatedBy
    @Column(name = "CREATED_BY")
    U createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFY_BY")
    U lastModifyBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFY_DATE")
    LocalDateTime lastModifyDate;
}
