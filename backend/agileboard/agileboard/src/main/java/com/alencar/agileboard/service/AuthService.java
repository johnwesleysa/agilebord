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
import org.springframework.dao.DataIntegrityViolationException;

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
        // Cria uma nova entidade de usuário
        User newUser = new User();
        newUser.setUsername(registerDTO.username());
        // Criptografa a senha
        newUser.setPassword(passwordEncoder.encode(registerDTO.password()));

        try {
            // Tenta salvar o novo usuário diretamente
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            // Se a exceção for lançada, é porque a constraint 'UNIQUE' do username foi violada.
            // Isso significa que o usuário já existe.
            throw new IllegalStateException("O nome de usuário '" + registerDTO.username() + "' já existe.");
        }
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
