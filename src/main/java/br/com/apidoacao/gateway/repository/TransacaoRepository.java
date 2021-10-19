package br.com.apidoacao.gateway.repository;

import br.com.apidoacao.gateway.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {

    @Query(" Select t from TransacaoEntity t " +
            " join UsuarioEntity u on t.doador.idDoador = u.doador.idDoador" +
            " where u.email = :email ")
    List<TransacaoEntity> findByUserEmail(@Param("email") final String email);
}
