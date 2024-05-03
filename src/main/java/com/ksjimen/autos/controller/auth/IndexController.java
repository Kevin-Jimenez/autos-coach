package com.ksjimen.autos.controller.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.auth.AuthResponseDto;
import com.ksjimen.autos.dto.auth.LoginRequest;
import com.ksjimen.autos.dto.auth.RegisterRequest;
import com.ksjimen.autos.exception.InternalLoginException;
import com.ksjimen.autos.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth/")
@RequiredArgsConstructor
public class IndexController {
	
	
	private AuthService authService;
	
	@Autowired
	public IndexController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequest loginRequest) throws InternalLoginException{
		AuthResponseDto responseAuth = new AuthResponseDto();
		try {
			responseAuth = authService.login(loginRequest);
		}catch (Exception e) {
			throw new InternalLoginException(
					"loginController: Error realizando el login del usuario "+ e.toString());
		}
		return ResponseEntity.ok(responseAuth);
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequest requestRegister) throws InternalLoginException{
		AuthResponseDto responseAuth = new AuthResponseDto();
		try {
			responseAuth = authService.register(requestRegister);
		}catch (Exception e) {
			throw new InternalLoginException(
					"registerController: Error registrando el usuario " + e.toString());
		}
		
		return ResponseEntity.ok(responseAuth);
	}
	
}
