package util.galileo.diegusweb.androidchat.login;

import util.galileo.diegusweb.androidchat.login.events.LoginEvent;

/**
 * Created by HP on 10/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}

