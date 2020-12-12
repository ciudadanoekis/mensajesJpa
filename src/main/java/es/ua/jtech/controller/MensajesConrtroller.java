package es.ua.jtech.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ua.jtech.jpa.entities.Mensaje;
import es.ua.jtech.repository.MensajesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping("${jtech.api.rest.basePath}/${jtech.api.rest.version}")
@Api(value = "API de mensajes", tags = {
	"Mensajes"
})
public class MensajesConrtroller {

	private final MensajesRepository mensajesService;

	public MensajesConrtroller(MensajesRepository mensajesService) {
		super();
		this.mensajesService = mensajesService;
	}

	@GetMapping("/mensajes/{menssageId}")
	@ApiOperation(value = "Obtiene el mensaje indicado")
    @ApiResponses({
        @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK",
            responseHeaders = {
                @ResponseHeader(name = "X-Api-Version", description = "API version", response = String.class)
        }),
        @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad request", response = Exception.class,
            responseHeaders = {
                @ResponseHeader(name = "X-Api-Version", description = "API version", response = String.class)
        }),
        @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error",
            response = Exception.class, responseHeaders = {
                @ResponseHeader(name = "X-Api-Version", description = "API version", response = String.class)
        })
    })
	public Optional<Mensaje> getMessageById(
			@ApiParam(name = "mensajeId", value = "identificador del mensaje", required = true)
			@PathVariable(value = "mensajeId") Integer messageId) {
		return mensajesService.findById(messageId);
	}


}
