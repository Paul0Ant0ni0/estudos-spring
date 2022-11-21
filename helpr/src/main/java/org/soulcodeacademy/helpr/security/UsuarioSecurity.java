package org.soulcodeacademy.helpr.security;

import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


// Está classe serve de ponto para a comunicação entre
// a persistência/banco de dados e o Spring Securiry

public class UsuarioSecurity implements UserDetails {
    // Dados de autenticação
    private String email;
    private String senha;

    // Dados de autorização
    private Perfil perfil;
    // Armazena as permisões/autoridades do usuário
    private ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public UsuarioSecurity(String email, String senha, Perfil perfil){
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        // adiciona a descrição do perfil como role do usuário
        this.authorities.add(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Indica para o Security as permições/papeis d meu usuário
        return this.authorities;
    }

    @Override
    public String getPassword() {
        // Indica para o Security que nosso usuário possui a senha abaixo
        return this.senha;
    }

    @Override
    public String getUsername() {
        // Indica para o Security que nosso usuário possui a email abaixo
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() { // Está conta é válida
        // Flag que indica se a conta está válida
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Flag que indica se a conta está desbloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Flag que indica se as credencias (email e senha) estão válidas
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Flag que indica se o usuário está habilitado
        return true;
    }

    // Indicamos por meios dos getters o estado de autenticação/autorização
    // dos nossos usuários salvos no banco de dados
}
