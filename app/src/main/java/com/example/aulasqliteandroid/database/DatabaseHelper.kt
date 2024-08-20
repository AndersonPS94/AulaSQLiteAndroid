package com.example.aulasqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,"loja.db",null,1
) {

    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("info_db", "Executou o onCreate")
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_PRODUTOS(\n" +
                "$ID_PRODUTO integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "$TITULO varchar(100),\n" +
                "$DESCRICAO text\n" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar a tabela")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.i("info_db", "Executou o onUpgrade")
        TODO("Not yet implemented")
    }
}