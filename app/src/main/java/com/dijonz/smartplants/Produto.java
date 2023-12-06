package com.dijonz.smartplants;

import java.io.Serializable;

public class Produto implements Serializable, Cloneable {

    private final String nome;
    private final String preco;
    private final String uri;

    public String getNome() {
        return nome;
    }
    public String getPreco() {return preco;}

    public String getUri() {return uri; }

    public Produto(String nome, String preco, String uri){
        this.nome = nome;
        this.preco = preco;
        this.uri = uri;
    }

    public Produto(Object obj){
        com.dijonz.smartplants.Produto copia = (com.dijonz.smartplants.Produto) obj;
        this.nome = copia.getNome();
        this.preco= copia.getPreco();
        this.uri= copia.getUri();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco +  '\'' +
                ", uri='" + uri + "}";
    }

    @Override
    public Object clone() {
        return new com.dijonz.smartplants.Produto(this);
    }
}





