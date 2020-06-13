package livraria.richardmartins.com.br.utilitarios;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import livraria.richardmartins.com.br.entidades.Livro;
import livraria.richardmartins.com.br.livraria.AdministracaoLivros;
import livraria.richardmartins.com.br.livraria.DetalheLivro;
import livraria.richardmartins.com.br.livraria.EditarLivro;
import livraria.richardmartins.com.br.livraria.R;

public class LivroAdapter extends BaseAdapter implements  View.OnClickListener, DialogInterface.OnClickListener {
	
	private AdministracaoLivros context;
	public List<Livro> livros;
	
	public LivroAdapter(AdministracaoLivros context, List<Livro> livros){
		this.context = context;
		this.livros = livros;
	}

	public LivroAdapter(AdministracaoLivros context){
		this.context = context;
	}
	
	@Override
	public int getCount() {		
		return livros.size();
	}

	@Override
	public Object getItem(int position) {		
		return livros.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}
	Livro livro;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Transformar o layout xml em um objeto (View)
		View item = LayoutInflater.from(context).inflate(R.layout.livro_item, null);
		
		//Acessando os objetos do layout (View)
		ImageView idFoto = (ImageView) item.findViewById(R.id.idFoto);
		TextView nome = (TextView) item.findViewById(R.id.titulo);
		TextView excluir = (TextView) item.findViewById(R.id.excluir);


		livro = livros.get(position);
		excluir.setHint(livro.getTitulo());
		excluir.setOnClickListener(this);

		//Obtendo o objeto aluno para atribui��o dos dados nos componentes
		
		//Preenchendo os componentes do item a ser exibido
		nome.setText(livro.getTitulo());
		// idFoto.setImageResource(livro.getIdFoto());
		
		return item;
	}

	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		SQLiteDatabase db = new DBCreator(context).getWritableDatabase();
		db.execSQL(Constants.DELETE_LIVRO, new String[]{ nome });
		for(int i2 = 0; i2< livros.size();i2++){
			if(livros.get(i2).getTitulo().equals(nome)){
				livros.remove(i2);
				break;
			}
		}
		notifyDataSetChanged();
	}

	String nome;

	@Override
	public void onClick(View view) {
		TextView titulo = (TextView) view.findViewById(R.id.excluir);
		if(titulo.getText() != null){
			nome = (String) titulo.getHint();
			AlertDialog.Builder alerta = new AlertDialog.Builder(context);
			alerta.setTitle("Cuidado!!");
			alerta.setMessage("Tem certeza que deseja excluir "+nome +" ?\n");
			alerta.setPositiveButton("Sim", this);
			alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
				}
			});
			alerta.show();


		}
	}
}












