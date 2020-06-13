package livraria.richardmartins.com.br.livraria;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import livraria.richardmartins.com.br.utilitarios.Constants;
import livraria.richardmartins.com.br.utilitarios.DBCreator;

public class EditarLivro extends AppCompatActivity {

    private EditText isbn;
    private EditText titulo;
    private EditText subTitulo;
    private EditText edicao;
    private EditText autor;
    private EditText numeroPaginas;
    private EditText anoPublicacao;
    private EditText editora;

    private Spinner categoria;

    List<String> categories;
    int item = 0;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.editar_livro);

        titulo = (EditText) findViewById(R.id.titulo);
        subTitulo = (EditText) findViewById(R.id.subTitulo);
        autor = (EditText) findViewById(R.id.autor);
        isbn = (EditText) findViewById(R.id.isbn);
        editora = (EditText) findViewById(R.id.nomeEditora);
        edicao = (EditText) findViewById(R.id.edicao);
        anoPublicacao = (EditText) findViewById(R.id.anoPublicacao);
        numeroPaginas = (EditText) findViewById(R.id.paginas);
        categoria = (Spinner) findViewById(R.id.spinner);

        db = new DBCreator(this).getWritableDatabase();

        Cursor rs = db.rawQuery(Constants.GET_ALL_LIVROS_BY_ID, new String[]{  getIntent().getStringExtra("id") });

        if (rs.moveToNext()) {
            isbn.setText(rs.getString(rs.getColumnIndex("ISBN")));
            titulo.setText(rs.getString(rs.getColumnIndex("titulo")));
            subTitulo.setText(rs.getString(rs.getColumnIndex("subTitulo")));
            edicao.setText(rs.getString(rs.getColumnIndex("edicao")));
            autor.setText(rs.getString(rs.getColumnIndex("autor")));
            numeroPaginas.setText(rs.getString(rs.getColumnIndex("paginas")));
            anoPublicacao.setText(rs.getString(rs.getColumnIndex("anoPublicacao")));
            editora.setText(rs.getString(rs.getColumnIndex("nomeEditora")));

            categories = Constants.getCategorias(rs.getString(rs.getColumnIndex("categoria")));
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoria.setAdapter(dataAdapter);
            categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    item = i;
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
    }



    public void salvar(View view) {
        String isbn = this.isbn.getText().toString();
        String titulo = this.titulo.getText().toString();
        String subTitulo = this.subTitulo.getText().toString();
        String edicao = this.edicao.getText().toString();
        String autor = this.autor.getText().toString();
        String paginas = this.numeroPaginas.getText().toString();
        String anoPublicacao = this.anoPublicacao.getText().toString();
        String nomeEditora = this.editora.getText().toString();

        Boolean valido = true;

        if(item == 0){
            this.categoria.requestFocus();
            valido = false;
        }
        if(nomeEditora.equals("")){
            this.editora.requestFocus();
            valido = false;
        }
        if(anoPublicacao.equals("")){
            this.anoPublicacao.requestFocus();
            valido = false;
        }
        if(paginas.equals("")){
            this.numeroPaginas.requestFocus();
            valido = false;
        }
        if(autor.equals("")){
            this.autor.requestFocus();
            valido = false;
        }
        if(edicao.equals("")){
            this.edicao.requestFocus();
            valido = false;
        }
        if(subTitulo.equals("")){
            this.subTitulo.requestFocus();
            valido = false;
        }
        if(titulo.equals("")){
            this.titulo.requestFocus();
            valido = false;
        }
        if(isbn.equals("")){
            this.isbn.requestFocus();
            valido = false;
        }

        if(valido){
            try{

                db.execSQL(Constants.UPDATE_LIVO, new Object[]{titulo,subTitulo,edicao,autor,paginas,anoPublicacao,nomeEditora,categories.get(item), "" ,new Date().toString(),getIntent().getStringExtra("id") });

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            Intent it = new Intent(this, AdministracaoLivros.class);
            startActivity(it);
            finish();
        } else {
            Toast.makeText(this, "Campos não preenchidos!", Toast.LENGTH_SHORT).show();
        }

    }

    public void fechar(View view) {
        finish();
    }
}
