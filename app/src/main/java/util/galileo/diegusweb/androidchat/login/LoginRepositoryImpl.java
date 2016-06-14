package util.galileo.diegusweb.androidchat.login;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import util.galileo.diegusweb.androidchat.domain.FirebaseHelper;
import util.galileo.diegusweb.androidchat.lib.EventBus;
import util.galileo.diegusweb.androidchat.lib.GreenRobotEventBus;
import util.galileo.diegusweb.androidchat.login.entities.User;
import util.galileo.diegusweb.androidchat.login.events.*;

/**
 * Created by HP on 11/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImpl() {

        helper = FirebaseHelper.getInstance();
        dataReference = helper.getDataReference();
        myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {

        Log.e("LoginHelperImpl","signup");
        dataReference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignInSuccess);
                signIn(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void signIn(String email, String password) {

        Log.e("LoginHelperImpl","signIn");
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                iniSignIn();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkAlreadyAuthenticated() {

    }

    @Override
    public void checkSession() {

        Log.e("LoginHelperImpl","check Session");
        if(dataReference.getApp() != null){
            iniSignIn();

        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    private void iniSignIn(){
        dataReference = helper.getDataReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);


                if(currentUser == null){
                    String email = helper.getAuthUserEmail();
                    if(email != null){
                        registerNewUser();
                    }
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if(email != null){
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
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
