package util.galileo.diegusweb.androidchat.login;

import android.util.Log;

import com.firebase.client.Firebase;

import util.galileo.diegusweb.androidchat.domain.FirebaseHelper;
import util.galileo.diegusweb.androidchat.lib.EventBus;
import util.galileo.diegusweb.androidchat.lib.GreenRobotEventBus;
import util.galileo.diegusweb.androidchat.login.events.*;

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

    private void postEvent(int type, String errorMessage){
        util.galileo.diegusweb.androidchat.login.events.LoginEvent loginEvent = new util.galileo.diegusweb.androidchat.login.events.LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMesage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
