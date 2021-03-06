package livraria.richardmartins.com.br.livraria;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import livraria.richardmartins.com.br.entidades.Livro;
import livraria.richardmartins.com.br.utilitarios.Constants;
import livraria.richardmartins.com.br.utilitarios.DBCreator;
import livraria.richardmartins.com.br.utilitarios.LivroSimplesAdapter;

public class LivrosPorPesquisa extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {

    private EditText titulo;

    private ListView lvLivros;
    private List<Livro> livros;
    private LivroSimplesAdapter adapter;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.livros_por_pesquisa);

        titulo = (EditText) findViewById(R.id.titulo);
        titulo.addTextChangedListener(this);
        lvLivros = (ListView) findViewById(R.id.lvLivros);
        lvLivros.setOnItemClickListener(this);

        db = new DBCreator(this).getWritableDatabase();

        setLivros();
    }

    private void setLivros() {
        livros = new ArrayList<>();
        try{
            Cursor rs;
            rs = db.rawQuery(Constants.GET_ALL_LIVROS_BY_TITULO_OR_AUTOR, new String[]{ "%"+titulo.getText().toString()+"%", "%"+titulo.getText().toString()+"%"});

            while (rs.moveToNext()) {
                Livro livro = new Livro();
                livro.setCodigo(rs.getLong(rs.getColumnIndex("codigo")));
                livro.setISBN(rs.getString(rs.getColumnIndex("ISBN")));
                livro.setTitulo(rs.getString(rs.getColumnIndex("titulo")));
                livro.setSubTitulo(rs.getString(rs.getColumnIndex("subTitulo")));
                livro.setEdicao(rs.getString(rs.getColumnIndex("edicao")));
                livro.setAutor(rs.getString(rs.getColumnIndex("autor")));
                livro.setPaginas(rs.getInt(rs.getColumnIndex("paginas")));
                livro.setAnoPublicacao(rs.getInt(rs.getColumnIndex("anoPublicacao")));
                livro.setNomeEditora(rs.getString(rs.getColumnIndex("nomeEditora")));
                livro.setCategoria(rs.getString(rs.getColumnIndex("categoria")));
                livros.add(livro);
            }
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        adapter = new LivroSimplesAdapter(this, livros);
        lvLivros.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void voltar(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent it = new Intent(this, DetalheLivro.class);
        it.putExtra("id", livros.get(i).getCodigo().toString());
        startActivity(it);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setLivros();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setLivros();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        setLivros();
    }
}
