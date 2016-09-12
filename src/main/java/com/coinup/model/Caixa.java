package com.coinup.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coinup.exceptions.SaldoNegativoException;
import com.coinup.framework.dao.BaseEntity;
import com.coinup.model.CaixaPeriodicidade.TYPES;
import com.coinup.utils.NumberUtils;

@Entity
@Table (name = "caixa" , schema = "coinup")
@SequenceGenerator(name="seq", sequenceName="coinup.caixa_id_seq")
public class Caixa extends BaseEntity {
	private static final long serialVersionUID = 3515594289200993410L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	private Long id;
	
	@Column(name="saldo")
	private BigDecimal saldo;
	
	@Column(name="vl_total_entrada")
	private BigDecimal totalEntrada;
	
	@Column(name="vl_total_saida")
	private BigDecimal totalSaida;
	
	@Column(name="qtd_entradas")
	private Integer quantidadeEntradas;
	
	@Column(name="qtd_saidas")
	private Integer quantidadeSaidas;
	
	@Column(name="dt_ultima_atualizacao")
	private Date ultimaAtualizacao;
	
	@Column(name="dt_criacao")
	private Date dataCriacao;
	
	@Column(name="dt_fechamento")
	private Date dataFechamento;
	
	@ManyToOne
	@JoinColumn(name="cd_caixa_periodicidade", referencedColumnName="id")
	private CaixaPeriodicidade caixaPeriodicidade;
	
	public Caixa() {}
	
	public BigDecimal realizarEntrada(BigDecimal valor) {
		if (valor == null) valor = BigDecimal.ZERO;
		if (getSaldo() == null) setSaldo(BigDecimal.ZERO);
		setSaldo(getSaldo().add(valor));
		return getSaldo();
	}
	
	public BigDecimal realizarSaida(BigDecimal valor) {
		if (valor == null) valor = BigDecimal.ZERO;
		if (getSaldo() == null) setSaldo(BigDecimal.ZERO);
		BigDecimal result = getSaldo().subtract(valor);
		if (NumberUtils.isSmaller(result, BigDecimal.ZERO)) {
			throw new SaldoNegativoException("Saldo Negativo. Operação: "+getSaldo()+" - "+valor);
		} else {
			setSaldo(result);
		}
		return getSaldo();
	}
	
	public String getPeriodicidade() {
		if (getCaixaPeriodicidade() != null) {
			TYPES type = getCaixaPeriodicidade().findCaixaPeriodicidadeType();
			if (type != null) {
				return type.getLabel();
			}
		} return null;
	}
	
	public boolean isFechado() {
		return getDataFechamento() != null; 
	}
	
	public boolean isAberto() {
		return !isFechado();
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public BigDecimal getTotalEntrada() {
		return totalEntrada;
	}

	public void setTotalEntrada(BigDecimal totalEntrada) {
		this.totalEntrada = totalEntrada;
	}

	public BigDecimal getTotalSaida() {
		return totalSaida;
	}

	public void setTotalSaida(BigDecimal totalSaida) {
		this.totalSaida = totalSaida;
	}

	public Integer getQuantidadeEntradas() {
		return quantidadeEntradas;
	}

	public void setQuantidadeEntradas(Integer quantidadeEntradas) {
		this.quantidadeEntradas = quantidadeEntradas;
	}

	public Integer getQuantidadeSaidas() {
		return quantidadeSaidas;
	}

	public void setQuantidadeSaidas(Integer quantidadeSaidas) {
		this.quantidadeSaidas = quantidadeSaidas;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Date getDataFechamento() {
		return dataFechamento;
	}
	
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public CaixaPeriodicidade getCaixaPeriodicidade() {
		return caixaPeriodicidade;
	}
	
	public void setCaixaPeriodicidade(CaixaPeriodicidade caixaPeriodicidade) {
		this.caixaPeriodicidade = caixaPeriodicidade;
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
		result = prime * result + ((caixaPeriodicidade == null) ? 0 : caixaPeriodicidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Caixa other = (Caixa) obj;
		if (caixaPeriodicidade == null) {
			if (other.caixaPeriodicidade != null)
				return false;
		} else if (!caixaPeriodicidade.equals(other.caixaPeriodicidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Caixa [saldo=" + saldo + ", totalEntrada=" + totalEntrada + ", totalSaida=" + totalSaida
				+ ", quantidadeEntradas=" + quantidadeEntradas + ", quantidadeSaidas=" + quantidadeSaidas
				+ ", ultimaAtualizacao=" + ultimaAtualizacao + "]";
	}
	
}
