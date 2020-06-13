package livraria.richardmartins.com.br.utilitarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import livraria.richardmartins.com.br.entidades.Livro;
import livraria.richardmartins.com.br.livraria.R;

public class LivroSimplesAdapter  extends BaseAdapter {


    private Context context;
    public List<Livro> livros;

    public LivroSimplesAdapter(Context context, List<Livro> livros){
        this.context = context;
        this.livros = livros;
    }

    public LivroSimplesAdapter(Context context){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Transformar o layout xml em um objeto (View)
        View item = LayoutInflater.from(context).inflate(R.layout.livro_simples_item, null);

        //Acessando os objetos do layout (View)
        ImageView idFoto = (ImageView) item.findViewById(R.id.idFoto);
        TextView nome = (TextView) item.findViewById(R.id.titulo);
        TextView autor = (TextView) item.findViewById(R.id.autor);

        //Obtendo o objeto aluno para atribui��o dos dados nos componentes
        Livro livro = livros.get(position);

        //Preenchendo os componentes do item a ser exibido
        nome.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        // idFoto.setImageResource(livro.getIdFoto());

        return item;
    }

}
