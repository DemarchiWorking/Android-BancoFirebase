package ad.mob.atatdev_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ad.mob.atatdev_android.firebase.ConfiguracaoFirebase;
import ad.mob.atatdev_android.model.Usuario;

public class CadastrarActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    private Button botaoCadastrarUsuario, botaoVoltar;
    private EditText txtNome, txtLogin, txtSenha, txtConfSenha, txtCPF;
    private ProgressBar progressBar;

    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        inicializar();

        botaoVoltar.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
          }});
        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            
            @Override
            public void onClick(View v) {
             progressBar.setVisibility(View.VISIBLE);

                if (!txtNome.toString().isEmpty() || !txtLogin.toString().isEmpty() || !txtSenha.toString().isEmpty() ||
                        !txtCPF.toString().isEmpty()) {

                    String campoNome = txtNome.getText().toString();
                    String campoLogin = txtLogin.getText().toString();
                    String campoSenha = txtSenha.getText().toString();
                    String campoCPF = txtCPF.getText().toString();

                    usuario = new Usuario();
                    usuario.setNome(campoNome);
                    usuario.setLogin(campoLogin);
                    usuario.setSenha(campoSenha);
                    usuario.setCpf(campoCPF);
                    cadastrarUsuario(usuario);

                } else if (txtSenha != txtConfSenha) {
                    Toast.makeText(CadastrarActivity.this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastrarActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void cadastrarUsuario(final Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getLogin(),
                usuario.getSenha())
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()){
                            try{
                                progressBar.setVisibility(View.GONE);

                                String idUsuario = task.getResult().getUser().getUid();
                                usuario.setId( idUsuario );
                                usuario.salvar(idUsuario);

                                startActivity(new Intent(getApplicationContext(),Inicial.class));
                                finish();

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                                }else {

                            Toast.makeText(CadastrarActivity.this, "Erro, tente novamente:"+task.toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
    public void inicializar(){
        botaoCadastrarUsuario = findViewById(R.id.btn_cadastrarusuario);
        botaoVoltar = findViewById(R.id.btn_voltar_login);
        txtNome = findViewById(R.id.edt_nome);
        txtLogin = findViewById(R.id.edt_login);
        txtSenha = findViewById(R.id.edt_senha);
        txtConfSenha = findViewById(R.id.edt_confirmarsenha);
        txtCPF = findViewById(R.id.edt_cpf);
        progressBar = findViewById(R.id.barr_cadastro);

    }
}
