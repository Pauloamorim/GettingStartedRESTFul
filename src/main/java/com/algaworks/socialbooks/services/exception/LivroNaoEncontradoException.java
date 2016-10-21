package com.algaworks.socialbooks.services.exception;

import java.io.Serializable;

public class LivroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6507605146735451086L;
	
	public LivroNaoEncontradoException(String msg) {
		super(msg);
	}
	public LivroNaoEncontradoException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	

}
