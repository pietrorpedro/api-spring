package com.projeto.api.Livros;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    
    @GetMapping
    public ResponseEntity<Page<PaginacaoLivros>> listarTudo(@PageableDefault(size = 10, sort={"autor"}) Pageable paginacao) {
        
        Page<PaginacaoLivros> livros = livroRepository.findAll(paginacao).map(PaginacaoLivros::new);

        return new ResponseEntity<Page<PaginacaoLivros>>(livros, null, 200);
    }

    @GetMapping("/{id}")
    public Optional<PaginacaoLivros> listarUm(@PathVariable Long id) {
        try {
            return livroRepository.findById(id).map(PaginacaoLivros::new);
        }catch(Exception e) {
            return null;
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid Livro dados) {

        Livro livro = new Livro(null, dados.getNome(), dados.getAutor(), dados.getTema(), dados.getPaginas(), dados.getPreco());
        livroRepository.save(livro);

        return new ResponseEntity<Livro>(livro, null, 201);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@RequestBody Livro dados, @PathVariable Long id) {
        Optional<Livro> livroOpcional = livroRepository.findById(id);

        if (livroOpcional.isPresent()) {
            Livro livro = livroOpcional.get();
            livro.setNome(dados.getNome());
            livro.setAutor(dados.getAutor());
            livro.setTema(dados.getTema());
            livro.setPaginas(dados.getPaginas());
            livro.setPreco(dados.getPreco());

            livroRepository.save(livro);
            return ResponseEntity.ok(livro);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Null> deletarLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

}
