package com.example.aulasqliteandroid.database

import com.example.aulasqliteandroid.model.Produto

interface IProdutoDao {
    fun salvar(produto: Produto): Boolean
    fun atualizar(produto: Produto): Boolean
    fun remover(idProduto: Int): Boolean
    fun listar(): List<Produto>
}