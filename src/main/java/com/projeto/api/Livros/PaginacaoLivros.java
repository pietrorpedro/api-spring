package com.projeto.api.Livros;

public record PaginacaoLivros(String nome, String autor, String tema, String paginas, String preco) {
    
    public PaginacaoLivros(Livro livro) {
        this(livro.getNome(), livro.getAutor(), livro.getTema(), livro.getPaginas(), livro.getPreco());
    }
}