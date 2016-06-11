package util.galileo.diegusweb.androidchat.login;

/**
 * Created by HP on 10/06/2016.
 */
public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

    void checkSession();
}