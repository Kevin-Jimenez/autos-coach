package com.ksjimen.autos.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.auth.AuthResponseDto;
import com.ksjimen.autos.dto.auth.LoginRequest;
import com.ksjimen.autos.dto.auth.RegisterRequest;
import com.ksjimen.autos.exception.InternalLoginException;
import com.ksjimen.autos.exception.UserException;
import com.ksjimen.autos.model.Rol;
import com.ksjimen.autos.model.User;
import com.ksjimen.autos.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	

	public AuthResponseDto register(RegisterRequest requestRegister) throws InternalLoginException {
		AuthResponseDto userRegisterDto = new AuthResponseDto();
		try {
			if(isRequestBodyValid(requestRegister)) {
				User userRegister = User.builder()
						.username(requestRegister.getUsername())
						.password(passwordEncoder.encode(requestRegister.getPassword()))
						.firstname(requestRegister.getFirstname())
						.lastname(requestRegister.getLastname())
						.rol(Rol.USER)
						.build();
				User userResponse =  userRepository.save(userRegister);
				
				if(userResponse.getId() > 0 ) {
					userRegisterDto.setToken(jwtService.getToken(userResponse));
					userRegisterDto.setResponse("Usuario Agregado Correctamente");
					return userRegisterDto;
				}
			}
			userRegisterDto.setToken(null);
			userRegisterDto.setResponse("No pueden haber campos nulos");
			return userRegisterDto;
		}catch (Exception e) {
			throw new InternalLoginException(
					"registerService: Hubo un error realizando el registro del usario " +e.toString());
		}
	}


	private boolean isRequestBodyValid(RegisterRequest requestRegister) {
		boolean valid = false;
		if(requestRegister != null && requestRegister.getUsername() != null && !requestRegister.getUsername().equals("")
				&& requestRegister.getPassword() != null && !requestRegister.getPassword().equals("") 
				&& requestRegister.getFirstname() != null && !requestRegister.getFirstname().equals("")) {
			valid = true;
		}
		return valid;
	}


	public AuthResponseDto login(LoginRequest loginRequest) throws  InternalLoginException {
		AuthResponseDto responseLogin = new AuthResponseDto();
		try {
			if(isValidLogin(loginRequest)) {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				UserDetails user = userRepository.findByUsername(loginRequest.getUsername())
						.orElseThrow(() -> new UserException("Usuario o Contraseña Incorrectos"));
				
				
				String token = jwtService.getToken(user);
				
				responseLogin.setToken(token);
				responseLogin.setResponse("Usuario Autenticado Correctamente");
				return responseLogin;
			}
			responseLogin.setResponse("Usuario y/o Contraseña no pueden ser vacios");
			responseLogin.setToken(null);
			return responseLogin;
		}catch (Exception e) {
			responseLogin.setResponse("Hubo un error al hacer login");
			responseLogin.setToken(null);
			throw new InternalLoginException(
					"loginService: Hubo un error al hacer login "+ e.toString());
		}
	}


	private boolean isValidLogin(LoginRequest loginRequest) {
		boolean flag = false;
		if(loginRequest != null && loginRequest.getUsername() != null && !loginRequest.getUsername().equals("")
				&& loginRequest.getPassword() != null && !loginRequest.getPassword().equals("")) {
			flag = true;
		}
		return flag;
	}

}
