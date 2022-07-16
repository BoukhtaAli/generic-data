package ma.business.data.genericdata.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ERROR_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorInfo implements Serializable {

    @Id
    @Column(name = "ID_ERROR_INFO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idErrorInfo;

    @Column(name = "ERROR_DESCRIPTION", nullable = false)
    @With
    String description;

    @Column(name = "ERROR_DATE", nullable = false)
    @With
    LocalDateTime errorDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorInfo errorInfo = (ErrorInfo) o;
        return Objects.equals(getIdErrorInfo(), errorInfo.getIdErrorInfo()) && Objects.equals(getErrorDate(), errorInfo.getErrorDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdErrorInfo(), getErrorDate());
    }
}
