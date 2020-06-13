package livraria.richardmartins.com.br.livraria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Catalogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.catalogo);
    }

    public void irListaTodosLivros(View view) {
        Intent it = new Intent(this, TodosLivros.class);
        startActivity(it);
    }

    public void irLivrosCategoria(View view) {
        Intent it = new Intent(this, LivrosPorCategoria.class);
        startActivity(it);
    }

    public void irPesquisarLivros(View view) {
        Intent it = new Intent(this, LivrosPorPesquisa.class);
        startActivity(it);
    }

    public void irHome(View view) {
        finish();
    }
}
