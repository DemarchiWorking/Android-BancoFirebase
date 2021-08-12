package ad.mob.atatdev_android.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
        private  static DatabaseReference referenciaFirebase;
        private static FirebaseAuth autenticacao;

        public static FirebaseAuth getAutenticacao(){
            if(autenticacao == null){
                autenticacao = FirebaseAuth.getInstance();
            }
            return autenticacao;
        }


        public static DatabaseReference getFirebase() {
            if (referenciaFirebase == null) {
                referenciaFirebase = FirebaseDatabase.getInstance().getReference();
            }
            return referenciaFirebase;
        }
}
