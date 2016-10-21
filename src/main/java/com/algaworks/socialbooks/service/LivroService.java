package com.algaworks.socialbooks.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exception.LivroNaoEncontradoException;

@Service
public class LivroService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	@Autowired
	private ComentariosRepository comentariosRepository;
	
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
	public Comentario salvarComentario(Long livroId, Comentario comentario){
		Livro livro = obter(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentariosRepository.save(comentario);
	}
	public List<Comentario> listarComentarios(Long idLivro) {
		Livro livro = obter(idLivro);
		
		return livro.getComentarios();
	}

}
