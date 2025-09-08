package com.alencar.agileboard.service;

import com.alencar.agileboard.domain.User;
import com.alencar.agileboard.dto.RegisterDTO;
import com.alencar.agileboard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alencar.agileboard.dto.AuthResponseDTO;
import com.alencar.agileboard.dto.LoginDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterDTO registerDTO) {
        //Verifica se o username já existe no banco
        if(userRepository.findByUsername(registerDTO.username()).isPresent()) {
            throw new IllegalStateException("O nome de usuário já existe");
        }

        //Cria uma nova entidade de usuário
        User newUser = new User();
        newUser.setUsername(registerDTO.username());

        //Criptografando a senha
        newUser.setPassword(passwordEncoder.encode(registerDTO.password()));

        userRepository.save(newUser);
    }

    public AuthResponseDTO login(LoginDTO loginDTO) {
        //Usa o AuthenticationManager para validar o usuário e senha
        //O spring security faz a busca do ususario e a comparação da senha criptografada por baixo dos panos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.username(),
                        loginDTO.password()
                )
        );

        //Busca o user no banco caso a autenticação passe
        var user = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(() -> new IllegalStateException("Usuário não encontrado após autenticação"));

        //Gera o token JWT para o usuário encontrado
        var jwtToken = jwtService.generateToken(user);

        return new AuthResponseDTO(jwtToken);
    }
}
