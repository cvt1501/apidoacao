package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_06_user")
@Entity
public class UsuarioEntity {

    @Id
    @Column(name = "id_user", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "conta_ativa", columnDefinition = "boolean default false")
    private Boolean contaAtiva;

    @Embedded
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doador")
    private DoadorEntity doador;

}
