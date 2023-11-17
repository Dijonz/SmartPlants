package com.dijonz.smartplants

import java.io.ObjectOutputStream
import java.net.Socket

class ServerConnect {
    val endereco = "192.168.0.101"
    val porta = 7777

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

}