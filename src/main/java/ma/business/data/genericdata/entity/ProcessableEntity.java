package ma.business.data.genericdata.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ma.business.data.genericdata.enums.State;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ProcessableEntity<S extends State> extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ATTEMPTS", nullable = false)
    Integer attempts = 0;

    @Column(name = "STATE", nullable = false)
    S state = getInitialState();

    @Column(name = "LOCK_DATE")
    LocalDateTime lockDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ERROR_INFO")
    ErrorInfo errorInfo;

    protected abstract S getInitialState();

    protected abstract S getAbandonedState();

    protected abstract S getFinalState();

    public abstract Class<ProcessableEntity> getEntityClass();

    public void resetEntityState(S state){
        this.state = state;
        this.attempts = 0;
        this.errorInfo = null;
    }
}
