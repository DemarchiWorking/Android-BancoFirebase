package ad.mob.atatdev_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.login.Login;

public class MainActivity extends AppCompatActivity {

    Button logar,cadastrar,logarfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicilizar();

        logarfb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginFacebook.class));
                finish();
            }});
        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CadastrarActivity.class));
                finish();
            }});

        logar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }});


    }


    public void inicilizar(){
        logar = findViewById(R.id.btn_Login);
        cadastrar = findViewById(R.id.btn_Cadastrar);
        logarfb = findViewById(R.id.btn_Logarfb);
    }
}
