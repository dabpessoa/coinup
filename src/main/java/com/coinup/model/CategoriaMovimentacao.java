package com.coinup.model;

import com.coinup.framework.dao.BaseEntity;

public class CategoriaMovimentacao extends BaseEntity {
	private static final long serialVersionUID = 6862936660257401821L;

	public static enum TYPES {
		ALIMENTACAO,
		LAZER,
		CONTAS,
		ESCOLA,
		FACULDADE,
		UNIVERSIDADE,
		TRABALHO,
		ESPOSA,
		MARIDO,
		FILHOS,
		PARENTES,
		FAMILIA,
		AJUDA,
		EMPRESTIMO;
	}
	
	private String label;
	private String descricao;
	
	public CategoriaMovimentacao() {}

	public CategoriaMovimentacao(String label) {
		this.label = label;
	}
	
	public CategoriaMovimentacao(String label, String descricao) {
		this(label);
		this.descricao = descricao;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaMovimentacao other = (CategoriaMovimentacao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaMovimentacao [label=" + label + ", descricao=" + descricao + "]";
	}
	
}
