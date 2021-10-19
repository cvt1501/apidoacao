package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_05_livro")
@Entity
public class LivroEntity {

    @Id
    @Column(name = "id_livro", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @Column(name = "nome_livro", nullable = false)
    private String nome;

    @Column(name = "autor_livro", nullable = false)
    private String autor;

    @Column(name = "editora_livro", nullable = false)
    private String editora;

}
