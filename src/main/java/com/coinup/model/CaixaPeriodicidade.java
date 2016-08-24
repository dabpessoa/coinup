package com.coinup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.coinup.framework.dao.BaseEntity;

@Entity
@Table(name="caixa_periodicidade", schema="coinup")
public class CaixaPeriodicidade extends BaseEntity {
	private static final long serialVersionUID = 7663316095550832491L;

	@Column(name="label")
	private String label;
	
	@Column(name="descricao")
	private String descricao;
	
	public CaixaPeriodicidade() {}

	public CaixaPeriodicidade(String label) {
		this.label = label;
	}
	
	public CaixaPeriodicidade(String label, String descricao) {
		this.label = label;
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
		CaixaPeriodicidade other = (CaixaPeriodicidade) obj;
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
		return "CaixaPeriodicidade [label=" + label + ", descricao=" + descricao + "]";
	}
	
}
