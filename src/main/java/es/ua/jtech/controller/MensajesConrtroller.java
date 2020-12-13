package es.ua.jtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ua.jtech.jpa.model.MensajeInput;
import es.ua.jtech.jpa.model.MensajeOutput;
import es.ua.jtech.service.MensajeService;
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

	private final MensajeService mensajesService;

	public MensajesConrtroller(MensajeService mensajesService) {
		super();
		this.mensajesService = mensajesService;
	}

	/**
     * Devuelve la lista de mensajes solicitada.
     *
     * @return lista de empleados
     */
	@GetMapping("/mensajes")
	@ApiOperation(value = "Obtiene todos los mensajes")
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
	public List<MensajeOutput> listMessages() {

		return mensajesService.findAllMensajes();
	}

	/**
     * devuelve la informacion del mensaje indicado
     *
     * @param mensajeId
     *           identificador del mensaje
     *@return mensaje indicado
     */
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
	public MensajeOutput getMessageById(
			@ApiParam(name = "mensajeId", value = "identificador del mensaje", required = true)
			@PathVariable(value = "mensajeId") Integer messageId) {
		return mensajesService.findMessageById(messageId);
	}

	 /**
     * create message.
     *
     * @param mensajeInput
     *            the message input
     * @return the message output
     * @throws MethodArgumentNotValidException
     *             the method argument not valid exception
     */
    @PostMapping("/messages/message")
    @ApiOperation(value = "Crear un mensaje")
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
    @PreAuthorize("hasWritePermissions() || isPeople() || hasRoleFinance() || hasRoleProduction() || hasRoleFacilityManager()")
    public MensajeOutput insertNickName(
    	@ApiParam(name = "empleado", value = "datos del empleado", required = true)
    	@RequestBody(required = true) MensajeInput originalMessageInput)
    	throws MethodArgumentNotValidException{
    	return mensajesService.insertMessage(originalMessageInput);
    }



}
