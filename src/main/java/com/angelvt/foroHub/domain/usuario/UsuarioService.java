package com.angelvt.foroHub.domain.usuario;

import com.angelvt.foroHub.infra.errors.IntegrityValidation;
import com.angelvt.foroHub.infra.security.TokenJWT;
import com.angelvt.foroHub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenJWT registrarUsuario(UsuarioDatosRegistro datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new IntegrityValidation("El correo electronico proporcionado ya esta registrado");
        }

        var usuario = new Usuario(datos);

        usuarioRepository.save(usuario);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(datos.email(),
                datos.password());

        var usuarioAuth = authenticationManager.authenticate(authToken);

        var JWTToken = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());

        return new TokenJWT(JWTToken);
    }

    public TokenJWT autenticarUsuario(UsuarioDatosAutenticacion datos) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(datos.email(),
                datos.password());

        var usuarioAuth = authenticationManager.authenticate(authToken);

        var JWTToken = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());

        return new TokenJWT(JWTToken);
    }
}
