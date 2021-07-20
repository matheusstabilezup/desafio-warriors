package br.com.zup

import io.micronaut.runtime.Micronaut.build
import org.h2.tools.Server.createWebServer

fun main(args: Array<String>) {
    createWebServer()
        .start()
    build()
        .args(*args)
        .packages("br.com.zup")
        .start()
}

