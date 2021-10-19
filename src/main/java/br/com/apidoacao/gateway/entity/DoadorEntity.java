package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_03_doador")
@Embeddable
@Entity
public class DoadorEntity {

    @Id
    @Column(name = "id_doador", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoador;

    @Column(name = "nome_doador", nullable = false)
    private String nome;

    @Column(name = "cpf_doador", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "pontos_doador", nullable = false)
    private Long pontos = 0L;

    @Embedded
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    private EnderecoEntity endereco;

}
