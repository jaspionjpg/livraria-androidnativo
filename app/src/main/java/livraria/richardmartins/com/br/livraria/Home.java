package livraria.richardmartins.com.br.livraria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().hide();
        login = getIntent().getStringExtra("login");
    }

    public void irCatalogo(View view) {
        Intent it = new Intent(this, Catalogo.class);
        startActivity(it);
    }

    public void irAdministracaoDeDados(View view) {
        Intent it = new Intent(this, AdministracaoLivros.class);
        startActivity(it);
    }

    public void irAlteracoaDeSenha(View view) {
        Intent it = new Intent(this, AlterarSenha.class);
        it.putExtra("login", login);
        startActivity(it);
    }

    public void irSobreEquipe(View view) {
        Intent it = new Intent(this, EquipeDesenvolvimento.class);
        startActivity(it);
    }

    public void sair(View view) {
        finish();
    }
}
