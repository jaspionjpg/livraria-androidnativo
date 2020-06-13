package livraria.richardmartins.com.br.utilitarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBCreator extends SQLiteOpenHelper {

    private static int VERSION = 9;
    private static String DB_NAME = "dbLivraria";

	public DBCreator(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        String tabUsuarios =
                "CREATE TABLE usuario (" +
                        "codigo integer PRIMARY KEY AUTOINCREMENT," +
                        "login text NOT NULL UNIQUE," +
                        "senha text NOT NULL)";

		String tabLivros =
				"CREATE TABLE livro (" +
						"codigo integer PRIMARY KEY AUTOINCREMENT," +
						"ISBN text NOT NULL UNIQUE," +
						"titulo text NOT NULL," +
						"subTitulo text NOT NULL," +
						"edicao text NOT NULL," +
						"autor text NOT NULL," +
						"paginas integer NOT NULL," +
						"anoPublicacao integer NOT NULL," +
						"nomeEditora text NOT NULL," +
						"categoria text NOT NULL," +
                        "imagem text," +
						"dataCadastro DATETIME NOT NULL)";

		String usuariosInsert = "INSERT INTO usuario (login, senha) VALUES(?, ?)" ;

        db.execSQL(tabUsuarios);
		db.execSQL(tabLivros);
		db.execSQL(usuariosInsert, new String[]{"richard", "richard"});
		db.execSQL(usuariosInsert, new String[]{"romulo", "romulo"});
		db.execSQL(usuariosInsert, new String[]{"ian", "ian"});
		db.execSQL(usuariosInsert, new String[]{"gordao", "gordao"});

		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"978-3-16-148410-0","Muito Além Do Inverno","Um romance que reflete sobre importantes questões da humanidade, com paixão, humor e sabedoria. Da mesma autora do best-seller A casa dos espíritos",1,"Allende,Isabel","323","2017","Bertrand Brasil","Infantis", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788565484336","Justiça – Edição Definitiva","Eles são conhecidos como “Os Maiores Super-Heróis do Mundo”",1,"Krueger, Jim / Ross, Alex / Braithwaite, Doug","208","2017","Panini Books","Aventura", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788547208592","Código Penal Comentado","o Professor Miguel Reale Júnior reúne diversos professores",1,"Júnior, Miguel Reale","1332","2017","Saraiva","Didaticos", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788595080348","Sr. G - Série Mosaico - Livro 1","Um livro legal",1,"Hecker, Sue","336","2017","Harpercollins","Auto ajuda", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788543105277","O Homem Mais Feliz da História","é um romance protagonizado pelo psiquiatra Marco Polo",1,"Cury,Augusto","140","2017","Sextante / Gmt","Aventura", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788582421611","Como Se Dar Muito Bem No Enem!","O chamado “Novo ENEM” veio para ficar.",3,"Nascimento,Alexandre Moreira","726","2016","Editora Foco","Didaticos", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788589987325","Cartilha Caminho Suave","A famosa cartilha chega a sua 132ª edição tendo contribuído para a alfabetização de mais de 40 milhões de brasileiros",1,"Lima,Branca Alves de","130","2015","Caminho Suave","Didaticos", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788560280940","It - A Coisa","Durante as férias xescolares de 1958",1,"King, Stephen","1104","2017","Suma De Letras","Jogos", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788547000413","Origem","De Onde Viemos? Para Onde Vamos?",1,"Brown, Dan","432","2017","Arqueiro","Historia", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788547000416","Na minha péle","Movido pelo desejo de viver num mundo",1,"Ramos, Nazaro","152","2017","Objetiva","Auto ajuda", "" ,new Date().toString()});
		db.execSQL(Constants.INSERT_LIVRO, new Object[]{"9788576087991","Linux - A Bíblia","o Mais Abrangente e Definitivo Guia Sobre Linux",1,"Negus,Christopher","852","2017","Alta Books","Manuais", "" ,new Date().toString()});
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS livro");
        onCreate(db);
	}
}
