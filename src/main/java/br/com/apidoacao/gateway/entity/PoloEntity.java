package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_02_polo")
@Entity
public class PoloEntity {

    @Id
    @Column(name = "id_polo", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPolo;

    @Column(name = "nome_polo", nullable = false)
    private String nome;

    @Column(name = "codigo_polo", nullable = false)
    private Long codigoPolo;

    @Embedded
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;

}
