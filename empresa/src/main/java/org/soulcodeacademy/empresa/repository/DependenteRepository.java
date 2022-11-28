package org.soulcodeacademy.empresa.repository;

import org.soulcodeacademy.empresa.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
}
