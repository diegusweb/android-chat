package util.galileo.diegusweb.androidchat;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Map;

/**
 * Created by HP on 09/06/2016.
 */
public class FirebaseHelper {
    private Firebase dataReference;
    private final static String SEPARATOR = "____";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    private final static String CONTACTS_PATH = "contacts";
    private final static String FIREBASE_URL = "https://app-list-diegus.firebaseio.com";

    private static class SingletonHelper{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }
    public static FirebaseHelper getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public FirebaseHelper() {
        this.dataReference = new Firebase(FIREBASE_URL);

    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail(){
        AuthData authData = dataReference.getAuth();
        String email = null;
        if (authData != null){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }
}
