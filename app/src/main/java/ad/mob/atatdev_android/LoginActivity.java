package ad.mob.atatdev_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ad.mob.atatdev_android.firebase.ConfiguracaoFirebase;
import ad.mob.atatdev_android.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar, botaoRegistrar, botaoVoltar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //verificaUsuario();
        inicializarView();

        botaoVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }});
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if(!textEmail.isEmpty()|| !textSenha.isEmpty()){

                    usuario = new Usuario();
                    usuario.setLogin(textEmail);
                    usuario.setSenha(textSenha);
                    validarLogin(usuario);

                }else{
                    Toast.makeText( LoginActivity.this,"Preencha todos os campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(i);
            }
        });
    }

    public void verificaUsuario(){
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        if(autenticacao.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


    }


    public void  validarLogin(Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getLogin(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),Inicial.class));
                    finish();
                    Toast.makeText(getApplicationContext(),"Usuário Logado com Sucesso",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"ERRO : Usuario Incorreto",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });

    }

    public void abrirCadastro(){
        Intent i = new Intent(LoginActivity.this, CadastrarActivity.class);
        startActivity(i);
    }

    public void inicializarView(){
        campoEmail = findViewById(R.id.edt_email_login);
        campoSenha = findViewById(R.id.edt_senha_login);
        botaoEntrar = findViewById(R.id.btn_entrar);
        botaoRegistrar = findViewById(R.id.btn_registrar);
        botaoVoltar = findViewById(R.id.btn_voltar_login);

    }


}
