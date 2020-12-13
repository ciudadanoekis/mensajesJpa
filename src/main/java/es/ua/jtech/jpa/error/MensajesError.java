package es.ua.jtech.jpa.error;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;

import com.paradigma.intranet.microservices.architecture.error.Error;

import lombok.Getter;

@Getter
public enum MensajesError implements Error {
	MESSAGE_NOT_EXITS(HttpStatus.NOT_FOUND.value(), "MSG-001",
		"Mensaje no existe"),
	;

	private Integer status;
	private String code;
	private String description;
	private String[] fields;



	private MensajesError(Integer status, String code, String description, String... fields) {
		this.status = status;
		this.code = code;
		this.description = description;
		if(ArrayUtils.isNotEmpty(fields)) {
			this.fields = fields;
		}
	}


	@Override
	public String[] getFields() {
		String[] result = null;

		if(ArrayUtils.isNotEmpty(fields)) {
			result = fields.clone();
		}

		return result;
	}

}
