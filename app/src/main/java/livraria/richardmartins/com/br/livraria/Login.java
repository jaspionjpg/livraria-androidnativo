package livraria.richardmartins.com.br.livraria;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import livraria.richardmartins.com.br.utilitarios.Constants;
import livraria.richardmartins.com.br.utilitarios.DBCreator;

public class Login extends AppCompatActivity implements TextWatcher {

    private EditText loginInput;
    private EditText senhaInput;

    private Button entrar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        loginInput  = (EditText) findViewById(R.id.loginInput);
        loginInput.addTextChangedListener(this);

        senhaInput = (EditText) findViewById(R.id.senhaInput);
        senhaInput.addTextChangedListener(this);

        entrar = (Button) findViewById(R.id.entrar);

        db = new DBCreator(this).getWritableDatabase();
    }

    public void entrar(View view) {
        Cursor rs = db.rawQuery(Constants.GET_LOGIN_USUARIO, new String[]{loginInput.getText().toString(), senhaInput.getText().toString()});

        if (rs.moveToNext()) {
            Intent it = new Intent(this, Home.class);
            it.putExtra("login", loginInput.getText().toString());
            startActivity(it);
            finish();
        } else {
            Toast.makeText(this, "Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sair(View view) {
        finish();
    }

    public void irEsqueceuSenha(View view) {
        Intent it = new Intent(this, EsqueciASenha.class);
        startActivity(it);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!loginInput.getText().equals("") && !senhaInput.getText().toString().equals("")){
            entrar.setEnabled(true);
        } else {
            entrar.setEnabled(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!loginInput.getText().equals("") && !senhaInput.getText().toString().equals("")){
            entrar.setEnabled(true);
        } else {
            entrar.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(!loginInput.getText().equals("") && !senhaInput.getText().toString().equals("")){
            entrar.setEnabled(true);
        } else {
            entrar.setEnabled(false);
        }
    }
}
