package br.com.apidoacao.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_04_transacao")
@Entity
public class TransacaoEntity {

    @Id
    @Column(name = "id_transacao", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransacao;

    @Column(name = "pontos_transacao", nullable = false)
    private Long pontos;

    @Column(name = "status_transacao")
    private String statusTransacao;

    @CreatedDate
    @Column(name = "data_transacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataTransacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_polo")
    private PoloEntity polo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doador")
    private DoadorEntity doador;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livro")
    private List<LivroEntity> livros;

}
