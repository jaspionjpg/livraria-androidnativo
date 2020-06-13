package livraria.richardmartins.com.br.livraria;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import livraria.richardmartins.com.br.utilitarios.Constants;
import livraria.richardmartins.com.br.utilitarios.DBCreator;

public class NovoLivro extends AppCompatActivity {

    private EditText isbnInput;
    private EditText tituloInput;
    private EditText subTituloInput;
    private EditText edicaoInput;
    private EditText autorInput;
    private EditText paginasInput;
    private EditText anoPublicacaoInput;
    private EditText nomeEditoraInput;

    private Spinner categorias;

    private ImageView imagem;
    List<String> categories;
    int item = 0;
    private SQLiteDatabase db;

    private Bitmap imagemBitmap;

    private String IMAGE_DIR ="FotosCapas";
    String foto ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.novo_livro);

        isbnInput = (EditText) findViewById(R.id.isbn);
        tituloInput = (EditText) findViewById(R.id.titulo);
        subTituloInput = (EditText) findViewById(R.id.subTitulo);
        edicaoInput = (EditText) findViewById(R.id.edicao);
        autorInput = (EditText) findViewById(R.id.autor);
        paginasInput = (EditText) findViewById(R.id.paginas);
        anoPublicacaoInput = (EditText) findViewById(R.id.anoPublicacao);
        nomeEditoraInput = (EditText) findViewById(R.id.nomeEditora);
        categorias = (Spinner) findViewById(R.id.categorias);

        imagem = (ImageView) findViewById(R.id.imageView2);

        categories = Constants.getCategorias();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter);

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db = new DBCreator(this).getWritableDatabase();
    }

    public void salvarAlteracoes(View view) {
        String isbn = this.isbnInput.getText().toString();
        String titulo = this.tituloInput.getText().toString();
        String subTitulo = this.subTituloInput.getText().toString();
        String edicao = this.edicaoInput.getText().toString();
        String autor = this.autorInput.getText().toString();
        String paginas = this.paginasInput.getText().toString();
        String anoPublicacao = this.anoPublicacaoInput.getText().toString();
        String nomeEditora = this.nomeEditoraInput.getText().toString();
        String categoria = this.categorias.toString();

        Boolean valido = true;

        if(item == 0){
            categorias.requestFocus();
            valido = false;
        }
        if(nomeEditora.equals("")){
            nomeEditoraInput.requestFocus();
            valido = false;
        }
        if(anoPublicacao.equals("")){
            anoPublicacaoInput.requestFocus();
            valido = false;
        }
        if(paginas.equals("")){
            paginasInput.requestFocus();
            valido = false;
        }
        if(autor.equals("")){
            autorInput.requestFocus();
            valido = false;
        }
        if(edicao.equals("")){
            edicaoInput.requestFocus();
            valido = false;
        }
        if(subTitulo.equals("")){
            subTituloInput.requestFocus();
            valido = false;
        }
        if(titulo.equals("")){
            tituloInput.requestFocus();
            valido = false;
        }
        if(isbn.equals("")){
            isbnInput.requestFocus();
            valido = false;
        }


        String base64 = "";

        try{
            // ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //imagemBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            // byte[] imageBytes = stream.toByteArray();
            //base64 =  Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         //   imagemBitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
            //   File f = new File(Environment.getExternalStorageDirectory()
            //      + File.separator + "DCIM"+ File.separator +"testimage.jpg");
            // f.createNewFile();
            //FileOutputStream fo = new FileOutputStream(f);
            //fo.write(bytes.toByteArray());
            //fo.close();

        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if(valido){
            try{
                ContentValues values = new ContentValues();
                values.put("ISBN",isbn);
                values.put("titulo",titulo);
                values.put("subTitulo",subTitulo);
                values.put("edicao",edicao);
                values.put("autor",autor);
                values.put("paginas",paginas);
                values.put("anoPublicacao",anoPublicacao);
                values.put("nomeEditora",nomeEditora);
                values.put("categoria",categories.get(item));
                values.put("imagem", foto);
                values.put("dataCadastro",new Date().toString());

                db.insert("livro", null, values);

                //db.execSQL(Constants.INSERT_LIVRO, new Object[]{isbn,titulo,subTitulo,edicao,autor,paginas,anoPublicacao,nomeEditora,categories.get(item), imageBytes ,new Date().toString()});

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

    private String saveImage(Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        File directory = new File(Environment.getExternalStorageDirectory()+IMAGE_DIR);

        if(!directory.exists()){
            directory.mkdirs();
        }

        try{

            File f  = new File(directory, Calendar.getInstance().getTimeInMillis()+".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());

            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/jpeg"}, null );
            fo.close();
            return f.getAbsolutePath();

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return "";
    }


    public void fecharCadastro(View view) {
        Intent it = new Intent(this, AdministracaoLivros.class);
        startActivity(it);
        finish();
    }

    public void tirarFoto(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1);
    }

    public void selectImagem(View view) {
        if(ActivityCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestGaleriaPermission();
        }else {

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 0);

        }
    }

    private void requestGaleriaPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){

            ActivityCompat.requestPermissions(NovoLivro.this, new String []{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0 );
        } else {
            ActivityCompat.requestPermissions(NovoLivro.this, new String []{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0 );
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    //imagemBitmap = (Bitmap) data.getExtras().get("data");
                    //imagem.setImageBitmap(imagemBitmap);

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    foto = saveImage(bitmap);
                    imagem.setImageBitmap(bitmap);
                }
                break;
            case 0:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                            foto = saveImage(bitmap);
                        imagem.setImageBitmap(bitmap);
                        //imagem.setImageURI(selectedImage);
                        //imagemBitmap = ((BitmapDrawable)imagem.getDrawable()).getBitmap();

                    } catch (IOException e){

                    }
                }
                break;
        }
    }

}
