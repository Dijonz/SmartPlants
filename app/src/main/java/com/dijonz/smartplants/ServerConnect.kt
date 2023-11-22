package com.dijonz.smartplants

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.ArrayList

class ServerConnect {
    private val endereco = "10.0.2.2"
    private val porta = 7977

    fun enviaVendedor(vendedor: Vendedor) {
        Socket(endereco, porta).use { socket ->
            ObjectOutputStream(socket.getOutputStream()).use { transmissor ->
                transmissor.writeObject(vendedor)
                transmissor.flush()
            }
        }
    }

    fun buscarProdutosUnico(email: String): ArrayList<Vendedor?>? {
        return Socket(endereco, porta).use { socket ->
            ObjectOutputStream(socket.getOutputStream()).use { transmissor ->
                val receberObj = ObjectInputStream(socket.getInputStream())
                val julianoo = Mensagem(false, email)

                transmissor.writeObject(julianoo)
                transmissor.flush()

                try {
                    return receberObj.readObject() as ArrayList<Vendedor?>
                } catch (e: Exception) {
                    println(e)
                }

                null
            }
        }
    }

    fun returnAllVendedores(): ArrayList<Vendedor?>? {
        return Socket(endereco, porta).use { socket ->
            ObjectOutputStream(socket.getOutputStream()).use { transmissor ->
                val receberObj = ObjectInputStream(socket.getInputStream())
                val julianoo = Mensagem(true, "VENDEDORES")

                transmissor.writeObject(julianoo)
                transmissor.flush()

                try {
                    return receberObj.readObject() as ArrayList<Vendedor?>
                } catch (e: Exception) {
                    println(e)
                }

                null
            }
        }
    }

    fun returnAllProdutos(): ArrayList<Vendedor?>? {
        return Socket(endereco, porta).use { socket ->
            ObjectOutputStream(socket.getOutputStream()).use { transmissor ->
                val receberObj = ObjectInputStream(socket.getInputStream())
                val julianoo = Mensagem(true, "PRODUTOS")

                transmissor.writeObject(julianoo)
                transmissor.flush()

                try {
                    return receberObj.readObject() as ArrayList<Vendedor?>
                } catch (e: Exception) {
                    println(e)
                }

                null
            }
        }
    }
}
