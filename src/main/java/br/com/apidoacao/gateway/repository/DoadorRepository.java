package br.com.apidoacao.gateway.repository;

import br.com.apidoacao.gateway.entity.DoadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoadorRepository extends JpaRepository<DoadorEntity, Integer> {

    @Query(" Select u.doador from UsuarioEntity u " +
            " where u.email = :email ")
    Optional<DoadorEntity> findByUserEmail(@Param("email") final String email);

}
