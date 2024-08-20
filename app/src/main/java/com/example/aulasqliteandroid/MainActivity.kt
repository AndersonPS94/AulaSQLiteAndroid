package com.example.aulasqliteandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqliteandroid.database.DatabaseHelper
import com.example.aulasqliteandroid.database.DatabaseHelper.Companion.ID_PRODUTO
import com.example.aulasqliteandroid.database.ProdutoDAO
import com.example.aulasqliteandroid.databinding.ActivityMainBinding
import com.example.aulasqliteandroid.model.Produto

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            btnSalvar.setOnClickListener {
                salvar()
            }
            btnListarProdutos.setOnClickListener {
                listarProdutos()
            }
            btnAtualizar.setOnClickListener {
                atualizar()
            }
            btnRemover.setOnClickListener {
                remover()
            }
        }
    }

    private fun remover() {

        val produtoDao = ProdutoDAO(this)
        produtoDao.remover(1)
    }

    private fun atualizar() {
        val titulo = binding.editProduto.text.toString()
        val produtoDao = ProdutoDAO(this)
        val produto = Produto(1,
            titulo,
            "Descrição...")

        produtoDao.atualizar(produto)
    }

    private fun listarProdutos() {

        val produtoDao = ProdutoDAO(this)
        val listaProdutos = produtoDao.listar()

        var texto = ""
        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach { produto ->
                texto += "${produto.idProduto} - ${produto.titulo} \n"
                Log.i("info_db", "id: ${produto.idProduto}, titulo: ${produto.titulo}")
            }
            binding.textResultado.text = texto
        }else{
            binding.textResultado.text = "Nenhum produto encontrado"
        }
    }

    private fun salvar() {

        val titulo = binding.editProduto.text.toString()
        val produtoDao = ProdutoDAO(this)
        val produto = Produto(-1,
            titulo,
            "Descrição...")

if(produtoDao.salvar(produto)){
    Toast.makeText(this,
        "Sucesso ao salvar o produto",
        Toast.LENGTH_SHORT
    ).show()
    binding.editProduto.text.clear()
    }else{
    Toast.makeText(
        this,
        "Erro ao salvar o produto",
        Toast.LENGTH_SHORT
    ).show()
        }
    }
}