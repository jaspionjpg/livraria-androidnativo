package livraria.richardmartins.com.br.livraria;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class EquipeDesenvolvimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.equipe_desenvolvimento);
    }

    public void fechar(View view) {
        finish();
    }
}
