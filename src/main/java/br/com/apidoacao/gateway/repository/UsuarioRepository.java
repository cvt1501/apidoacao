package br.com.apidoacao.gateway.repository;

import br.com.apidoacao.gateway.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    @Query(" Select u from UsuarioEntity u where u.email = :email ")
    Optional<UsuarioEntity> findByEmail(@Param("email") final String email);

}
