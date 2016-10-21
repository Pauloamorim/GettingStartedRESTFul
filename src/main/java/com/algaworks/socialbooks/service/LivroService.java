package com.algaworks.socialbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exception.LivroNaoEncontradoException;

@Service
public class LivroService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	public Livro obter(Long codLivro){
		Livro livro = livrosRepository.findOne(codLivro);
		
		if(livro == null){
			throw new LivroNaoEncontradoException("Livro não encontrado");
		}
		return livro;
	}
	public Livro salvar(Livro livro){
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	public void deletar(Long codLivro){
		try {
			livrosRepository.delete(codLivro);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não encontrado");
		}
	}
	public void alterar(Livro livro){
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	private void verificarExistencia(Livro livro){
		obter(livro.getId());
	}

}
