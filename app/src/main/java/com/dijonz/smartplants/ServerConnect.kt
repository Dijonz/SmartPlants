package com.dijonz.smartplants

import java.io.ObjectOutputStream
import java.net.Socket
import java.util.ArrayList
import java.io.ObjectInputStream


class ServerConnect {
    val endereco = "172.29.64.1"
    val porta = 7977

    fun enviaVendedor(vendedor: Vendedor){
        try {
            val socket = Socket(endereco, porta)
            val transmissor = ObjectOutputStream(socket.getOutputStream())

            transmissor.writeObject(vendedor)
            transmissor.flush()

            transmissor.close()

            socket.close()
        } catch (Exception: Exception){
            println(Exception.message)
        }
    }

    fun buscarProdutosUnico(email: String): ArrayList<Vendedor?>? {
        val socket = Socket(endereco, porta)
        val transmissor = ObjectOutputStream(socket.getOutputStream())
        val receberObj: java.io.ObjectInputStream = java.io.ObjectInputStream(socket.getInputStream())

        val julianoo = Mensagem(false, email)

        transmissor.writeObject(julianoo)
        transmissor.flush()
        var vendedores: ArrayList<Vendedor?>? = null
        try {
            vendedores = receberObj.readObject() as java.util.ArrayList<Vendedor?> //receber o arraylist como retorno da mensagem
        } catch (e: java.lang.Exception) {
            println(e)
        }

        return vendedores
    }

    fun returnAllVendedores(): ArrayList<Vendedor?>? {
        val socket = Socket(endereco, porta)
        val transmissor = ObjectOutputStream(socket.getOutputStream())
        val receberObj: java.io.ObjectInputStream = java.io.ObjectInputStream(socket.getInputStream())

        val julianoo = Mensagem(true, "VENDEDORES")

        transmissor.writeObject(julianoo)
        transmissor.flush()
        var vendedores: ArrayList<Vendedor?>? = null
        try {
            vendedores = receberObj.readObject() as java.util.ArrayList<Vendedor?> //receber o arraylist como retorno da mensagem
        } catch (e: java.lang.Exception) {
            println(e)
        }

        return vendedores
    }

    fun returnAllProdutos(): ArrayList<Vendedor?>? {
        val socket = Socket(endereco, porta)
        val transmissor = ObjectOutputStream(socket.getOutputStream())
        val receberObj: java.io.ObjectInputStream = java.io.ObjectInputStream(socket.getInputStream())

        val julianoo = Mensagem(true, "PRODUTOS")

        transmissor.writeObject(julianoo)
        transmissor.flush()
        var produtos: ArrayList<Vendedor?>? = null
        try {
            produtos = receberObj.readObject() as java.util.ArrayList<Vendedor?> //receber o arraylist como retorno da mensagem
        } catch (e: java.lang.Exception) {
            println(e)
        }

        return produtos

    }

}