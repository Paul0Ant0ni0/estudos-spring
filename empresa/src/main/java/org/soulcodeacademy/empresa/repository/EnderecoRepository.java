package org.soulcodeacademy.empresa.repository;

import org.soulcodeacademy.empresa.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
