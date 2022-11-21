package org.soulcodeacademy.helpr.domain.enums;

// Enum -> Delimita um conjunto de valores constantes
// ROLE -> papel de usuário
public enum Perfil { // enum class

    ADMIN("ROLE_ADMIN"), // 0
    FUNCIONARIO("ROLE_FUNCIONARIO"), // 1
    CLIENTE("ROLE_CLIENTE"); // 2

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}