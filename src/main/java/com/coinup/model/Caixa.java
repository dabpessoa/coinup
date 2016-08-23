package com.coinup.model;

import java.math.BigDecimal;

import com.coinup.exceptions.SaldoNegativoException;
import com.coinup.utils.NumberUtils;

public class Caixa {

	private BigDecimal saldo;
	
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

	@Override
	public String toString() {
		return "Caixa [saldo=" + saldo + "]";
	}
	
}
