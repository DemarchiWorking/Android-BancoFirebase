package ad.mob.atatdev_android.model;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import ad.mob.atatdev_android.CadastrarActivity;
import ad.mob.atatdev_android.firebase.ConfiguracaoFirebase;

import static com.facebook.share.internal.DeviceShareDialogFragment.TAG;

public class Usuario {
    private String id;
    private String nome;
    private String login;
    private String senha;
    private String cpf;

    public Usuario() {
    }
    public void salvar(String id){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuariosRef = firebaseRef.child("usuarios/"+id);
        usuariosRef.setValue(this);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
