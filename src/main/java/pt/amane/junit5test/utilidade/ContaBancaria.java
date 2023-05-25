package pt.amane.junit5test.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {

    private BigDecimal saldo;

    /**
     *  TODO 1 - validar saldo: não pode ser nulo, case seja deve lançar uma IllegalArgumentException
     *  TODO 2 - pode ser zero ou negativo
     * @param saldo
     */
    public ContaBancaria(BigDecimal saldo) {
        if (saldo == null) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.saldo = saldo;
    }

    /**
     *  TODO 1 - validar valor: não pode ser nulo ou menor que zero, case seja deve lançar uma IllegalArgumentException
     *  TODO 2 - Deve subtrair o valor do saldo
     *  TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
     * @param valor
     */
    public void saque(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        if (this.saldo.compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    /**
     *  TODO 1 - validar valor: não pode ser nulo ou menor que zero uma IllegalArgumentException
     *  TODO 2 - Deve adicionar o valor ao saldo
     *  TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
     * @param valor
     */
    public void deposito(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.saldo = this.saldo.add(valor);
    }

    /**
     * TODO 1 - retornar saldo
     * @return
     */
    public BigDecimal saldo() {
        return this.saldo;
    }
}