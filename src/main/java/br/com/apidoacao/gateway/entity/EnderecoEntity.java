package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_01_endereco")
@Embeddable
@Entity
public class EnderecoEntity {

    @Id
    @Column(name = "id_endereco", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(length = 9, nullable = false)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @Column(length = 50, nullable = false)
    private String cidade;

    @Column(length = 50, nullable = false)
    private String estado;

}
