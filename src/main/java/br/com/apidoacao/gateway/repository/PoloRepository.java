package br.com.apidoacao.gateway.repository;

import br.com.apidoacao.gateway.entity.PoloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoloRepository extends JpaRepository<PoloEntity, Integer> {

    @Query(" Select p from PoloEntity p " +
            " where p.codigoPolo = :codigoPolo ")
    Optional<PoloEntity> findByPoloCodigo(final @Param("codigoPolo") Long codigoPolo);

}
