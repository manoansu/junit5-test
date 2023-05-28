package pt.amane.junit5test.ecomerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarrinhoCompra {

    private final Cliente cliente;
    private final List<ItemCarrinhoCompra> itens;

    public CarrinhoCompra(Cliente cliente) {
        this(cliente, new ArrayList<>());
    }

    public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(itens);
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
    }

    /**
     * TODO deve retornar uma nova lista para que a antiga não seja alterada
     * @return
     */
    public List<ItemCarrinhoCompra> getItens() {
        return new ArrayList<>(itens);
    }

    public Cliente getCliente() {
        return cliente;
    }

    /**
     * TODO parâmetros não podem ser nulos, deve retornar uma exception
     * TODO quantidade não pode ser menor que 1
     * TODO deve incrementar a quantidade caso o produto já exista
     * @param produto
     * @param quantidade
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        Objects.requireNonNull(produto);
        validaQuantidade(quantidade);

        encontrarItemPeloProduto(produto)
                .ifPresentOrElse(item -> item.adicionarQuantidade(quantidade), ()-> adicionarNovoItem(produto, quantidade));
    }

    /**
     * TODO parâmetro não pode ser nulo, deve retornar uma exception
     * TODO caso o produto não exista, deve retornar uma exception
     * TODO deve remover o produto independente da quantidade
     * @param produto
     */
    public void removerProduto(Produto produto) {
        Objects.requireNonNull(produto);
        ItemCarrinhoCompra item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        this.itens.remove(item);
    }

    /**
     * TODO parâmetro não pode ser nulo, deve retornar uma exception
     * TODO caso o produto não exista, deve retornar uma exception
     * TODO deve aumentar em um quantidade do produto
     * @param produto
     */
    public void aumentarQuantidadeProduto(Produto produto) {
        Objects.requireNonNull(produto);
        ItemCarrinhoCompra item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        item.adicionarQuantidade(1);
    }

    /**
     * TODO parâmetro não pode ser nulo, deve retornar uma exception
     * TODO caso o produto não exista, deve retornar uma exception
     * TODO deve diminuir em um quantidade do produto, caso tenha apenas um produto, deve remover da lista
     * @param produto
     */
    public void diminuirQuantidadeProduto(Produto produto) {
        Objects.requireNonNull(produto);
        ItemCarrinhoCompra item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        if (item.getQuantidade() == 1) {
            itens.remove(item);
        } else {
            item.subtrairQuantidade(1);
        }
    }

    /**
     * TODO implementar soma do valor total de todos itens
     * @return
     */
    public BigDecimal getValorTotal() {
        return this.itens.stream()
                .map(ItemCarrinhoCompra::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * TODO retorna quantidade total de itens no carrinho
     * TODO Exemplo em um carrinho com 2 itens, com a quantidade 2 e 3 para cada item respectivamente, deve retornar 5
     * @return
     */
    public int getQuantidadeTotalDeProdutos() {
        return this.itens.stream()
                .map(ItemCarrinhoCompra::getQuantidade)
                .reduce(0,Integer::sum);
    }

    /**
     * TODO deve remover todos os itens
     */
    public void esvaziar() {
        this.itens.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoCompra that = (CarrinhoCompra) o;
        return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
    }

    private void validaQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void adicionarNovoItem(Produto produto, int quantidade) {
        this.itens.add(new ItemCarrinhoCompra(produto, quantidade));
    }

    private Optional<ItemCarrinhoCompra> encontrarItemPeloProduto(Produto produto) {
        return this.itens
                .stream()
                .filter(item -> item.getProduto().equals(produto))
                .findFirst();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens, cliente);
    }
}
