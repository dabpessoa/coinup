package com.coinup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coinup.framework.dao.BaseEntity;

@Entity
@Table(name="caixa_periodicidade", schema="coinup")
@SequenceGenerator(name="seq", sequenceName="caixa_periodicidade_id_seq")
public class CaixaPeriodicidade extends BaseEntity {
	private static final long serialVersionUID = 7663316095550832491L;

	public static enum TYPES {
		DIA("diário"),
		MES("mensal"),
		ANO("anual"),
		DECADA("decada");
		
		private String label;
		private TYPES(String label) {this.label = label;}
		public String getLabel(){return this.label;};
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	private Long id;
	
	@Column(name="label")
	private String label;
	
	@Column(name="descricao")
	private String descricao;
	
	public CaixaPeriodicidade() {}
	
	public CaixaPeriodicidade(Long id) {
		this();
		setId(id);
	}

	public CaixaPeriodicidade(String label) {
		this();
		this.label = label;
	}
	
	public CaixaPeriodicidade(String label, String descricao) {
		this();
		this.label = label;
		this.descricao = descricao;
	}
	
	public TYPES findCaixaPeriodicidadeType() {
		if (getLabel() == null) return null;
		TYPES[] types = TYPES.values();
		for (TYPES type : types) {
			if (type.getLabel().equalsIgnoreCase(this.getLabel())) {
				return type;
			}
		}
		return null;
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
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
