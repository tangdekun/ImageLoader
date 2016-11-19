package realm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by John on 2016/11/8.
 */

public class User extends RealmObject {
    @PrimaryKey
    int id;
    @Required
    String name;
    RealmList<Dog> mDogList;

}
