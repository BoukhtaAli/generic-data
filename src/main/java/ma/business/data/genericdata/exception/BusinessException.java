package ma.business.data.genericdata.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BusinessException extends Exception {

	@With
	private String message;

	@With
	private LocalDateTime time;

	private Map<String, String> additionalData = new HashMap<>(0);

}
