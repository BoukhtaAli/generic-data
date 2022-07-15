package ma.business.data.genericdata.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ERROR_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class ErrorInfo implements Serializable {

    @Id
    @Column(name = "ID_ERROR_INFO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idErrorInfo;

    @Column(name = "ERROR_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "ERROR_DATE", nullable = false)
    private LocalDateTime errorDate;

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