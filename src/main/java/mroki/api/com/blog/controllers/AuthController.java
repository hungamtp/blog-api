package mroki.api.com.blog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mroki.api.com.blog.dto.request.JwtResponse;
import mroki.api.com.blog.dto.request.LoginRequest;
import mroki.api.com.blog.dto.common.ResponseDTO;
import mroki.api.com.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "AUTH", description = "AUTH API")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Operation(summary = "Login", description = "", tags = { "AUTH" })
	@ApiResponses(value = { @ApiResponse(responseCode = "2xx", description = "Successfull"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		JwtResponse response = authService.login(loginRequest);
		ResponseDTO dto = new ResponseDTO();
		dto.setData(response);
		return ResponseEntity.ok().body(dto);
	}

}
