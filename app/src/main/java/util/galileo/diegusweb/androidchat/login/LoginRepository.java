package util.galileo.diegusweb.androidchat.login;

/**
 * Created by HP on 10/06/2016.
 */
public interface LoginRepository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
    void checkSession();
}
