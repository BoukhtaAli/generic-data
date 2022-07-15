package ma.business.data.genericdata.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ma.business.data.genericdata.enums.State;

import java.time.LocalDateTime;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class ProcessableEntity<S extends State> extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ATTEMPTS", nullable = false)
    private Integer attempts = 0;

    @Column(name = "STATE", nullable = false)
    private S state = getInitialState();

    @Column(name = "LOCK_DATE")
    private LocalDateTime lockDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ERROR_INFO")
    private ErrorInfo errorInfo;

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
