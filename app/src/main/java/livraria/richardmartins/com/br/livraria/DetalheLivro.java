package livraria.richardmartins.com.br.livraria;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import livraria.richardmartins.com.br.entidades.Livro;
import livraria.richardmartins.com.br.utilitarios.Constants;
import livraria.richardmartins.com.br.utilitarios.DBCreator;

public class DetalheLivro extends AppCompatActivity {

    private TextView titulo;
    private TextView subTitulo;
    private TextView autor;
    private TextView isbn;
    private TextView editora;
    private TextView edicao;
    private TextView anoPublicacao;
    private TextView numeroPaginas;
    private TextView categoria;
    private ImageView imagem;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.detalhe_livro);

        titulo = (TextView) findViewById(R.id.titulo);
        subTitulo = (TextView) findViewById(R.id.subTitulo);
        autor = (TextView) findViewById(R.id.autor);
        isbn = (TextView) findViewById(R.id.isbn);
        editora = (TextView) findViewById(R.id.editora);
        edicao = (TextView) findViewById(R.id.edicao);
        anoPublicacao = (TextView) findViewById(R.id.anoPublicacao);
        numeroPaginas = (TextView) findViewById(R.id.numeroPaginas);
        categoria = (TextView) findViewById(R.id.categoria);

        imagem = (ImageView) findViewById(R.id.imageView2);
        db = new DBCreator(this).getWritableDatabase();

        Cursor rs;
        rs = db.rawQuery(Constants.GET_ALL_LIVROS_BY_ID, new String[]{  getIntent().getStringExtra("id") });

        if (rs.moveToNext()) {
            isbn.setText("ISBN: " +rs.getString(rs.getColumnIndex("ISBN")));
            titulo.setText("Titulo: " +rs.getString(rs.getColumnIndex("titulo")));
            subTitulo.setText("SubTitulo: " +rs.getString(rs.getColumnIndex("subTitulo")));
            edicao.setText("Edição: " +rs.getString(rs.getColumnIndex("edicao")));
            autor.setText("Autor: " +rs.getString(rs.getColumnIndex("autor")));
            numeroPaginas.setText("Número páginas: " +rs.getString(rs.getColumnIndex("paginas")));
            anoPublicacao.setText("Ano publicação: " +rs.getString(rs.getColumnIndex("anoPublicacao")));
            editora.setText("Editora: " +rs.getString(rs.getColumnIndex("nomeEditora")));
            categoria.setText("Categoria: " +rs.getString(rs.getColumnIndex("categoria")));

            File imgFile = new File(rs.getString(rs.getColumnIndex("imagem")));
            if(imgFile.exists()){

                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imagem.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "Campos não preenchidos!", Toast.LENGTH_SHORT).show();

            }


           // try{
              //  byte[] arquivo = rs.getBlob(rs.getColumnIndex("imagem"));
               // if(arquivo !=null){
               // Bitmap image  = BitmapFactory.decodeByteArray(arquivo, 0, arquivo.length);
                //imagem.setImageBitmap(image);

             //   String base64 = rs.getString(rs.getColumnIndex("imagem"));
              //  byte[] arquivo = Base64.decode(base64.getBytes(), Base64.DEFAULT);
               // Bitmap image  = BitmapFactory.decodeByteArray(arquivo, 0, arquivo.length);
               // imagem.setImageBitmap(image);
            //}catch (Exception e){
             //   Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //}

        }
    }

    public void voltar(View view) {
        finish();
    }
}
