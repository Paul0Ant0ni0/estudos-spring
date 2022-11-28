package org.soulcodeacademy.empresa.repository;

import org.soulcodeacademy.empresa.domain.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Integer> {
}
