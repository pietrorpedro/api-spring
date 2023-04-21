package com.projeto.api.Livros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String autor;
    @NotNull
    private String tema;
    @NotNull
    private String paginas;
    @NotNull
    private String preco;
}
