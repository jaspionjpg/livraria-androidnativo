package livraria.richardmartins.com.br.livraria;

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

public class AlterarSenha extends AppCompatActivity implements TextWatcher {

    private EditText loginInput;
    private EditText senhaatualInput;
    private EditText novasenhaInput;
    private EditText confirmeInput;

    private Button salvar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.alterar_senha);

        loginInput  = (EditText) findViewById(R.id.loginInput);
        loginInput.addTextChangedListener(this);
        senhaatualInput = (EditText) findViewById(R.id.senhaatualInput);
        senhaatualInput.addTextChangedListener(this);
        novasenhaInput = (EditText) findViewById(R.id.novasenhaInput);
        novasenhaInput.addTextChangedListener(this);
        confirmeInput = (EditText) findViewById(R.id.confirmeInput);
        confirmeInput.addTextChangedListener(this);

        salvar = (Button) findViewById(R.id.salvar);

        loginInput.setText(getIntent().getStringExtra("login"));

        db = new DBCreator(this).getWritableDatabase();
    }

    public void salvarAlteracoes(View view) {
        String login = loginInput.getText().toString();
        String senhaAntiga = senhaatualInput.getText().toString();
        String senha = novasenhaInput.getText().toString();
        String confirme = confirmeInput.getText().toString();

        if(!senha.equals(confirme)){
            Toast.makeText(this, "Senhas não coincidem!", Toast.LENGTH_SHORT).show();
        } else {
            Cursor rs = db.rawQuery(Constants.GET_LOGIN_USUARIO, new String[]{login, senhaAntiga});

            if (rs.moveToNext()) {
                rs = db.rawQuery(Constants.GET_USUARIO_BY_LOGIN, new String[]{login});

                if (rs.moveToNext()) {
                    db.execSQL(Constants.UPDATE_SENHA_USUARIO, new String[]{senha, login});
                    finish();
                } else {
                    Toast.makeText(this, "Ocorreu um erro inesperado!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void sair(View view) {
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!senhaatualInput.getText().equals("") && !loginInput.getText().equals("") && !novasenhaInput.getText().toString().equals("") && !confirmeInput.getText().toString().equals("")){
            salvar.setEnabled(true);
        } else {
            salvar.setEnabled(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!senhaatualInput.getText().equals("") && !loginInput.getText().equals("") && !novasenhaInput.getText().toString().equals("") && !confirmeInput.getText().toString().equals("")){
            salvar.setEnabled(true);
        } else {
            salvar.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(!senhaatualInput.getText().equals("") && !loginInput.getText().equals("") && !novasenhaInput.getText().toString().equals("") && !confirmeInput.getText().toString().equals("")){
            salvar.setEnabled(true);
        } else {
            salvar.setEnabled(false);
        }
    }
}
