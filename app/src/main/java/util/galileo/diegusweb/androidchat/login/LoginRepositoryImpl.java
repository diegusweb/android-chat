package util.galileo.diegusweb.androidchat.login;

import android.util.Log;

import com.firebase.client.Firebase;

import util.galileo.diegusweb.androidchat.domain.FirebaseHelper;

/**
 * Created by HP on 11/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        Log.e("LoginHelperImpl","signup");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginHelperImpl","signIn");
    }

    @Override
    public void checkAlreadyAuthenticated() {

    }

    @Override
    public void checkSession() {
        Log.e("LoginHelperImpl","check Session");
    }
}
