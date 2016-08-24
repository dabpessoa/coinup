package com.coinup.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coinup.exceptions.SaldoNegativoException;
import com.coinup.framework.dao.BaseEntity;
import com.coinup.utils.NumberUtils;

@Entity
@Table (name = "caixa" , schema = "coinup")
public class Caixa extends BaseEntity {
	private static final long serialVersionUID = 3515594289200993410L;

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
	
	@Column(name="dt_abertura")
	private Date dataAbertura;
	
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
	
	public CaixaPeriodicidade getCaixaPeriodicidade() {
		return caixaPeriodicidade;
	}
	
	public void setCaixaPeriodicidade(CaixaPeriodicidade caixaPeriodicidade) {
		this.caixaPeriodicidade = caixaPeriodicidade;
	}

	@Override
	public String toString() {
		return "Caixa [saldo=" + saldo + ", totalEntrada=" + totalEntrada + ", totalSaida=" + totalSaida
				+ ", quantidadeEntradas=" + quantidadeEntradas + ", quantidadeSaidas=" + quantidadeSaidas
				+ ", ultimaAtualizacao=" + ultimaAtualizacao + "]";
	}
	
}
