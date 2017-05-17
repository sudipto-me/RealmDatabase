package app.realm.gnt.com.realmdatabase;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by PC-05 on 5/17/2017.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }
    public static RealmController with(Fragment fragment){
        if(instance == null){
            instance = new RealmController(fragment.getActivity().getApplication());
        }

        return instance;
    }
    public static RealmController with (Activity activity){
        if(instance == null){
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }
    public static RealmController with(Application application){
        if(instance == null){
            instance = new RealmController(application);
        }
        return instance;
    }
    public static RealmController getInstance(){
        return instance;
    }
    public Realm getRealm(){
        return realm;
    }
    //refreshing the realm instance
    public void refresh(){
        realm.refresh();
    }
    //clear all objects from Books.class
    public void clearAll(){
        realm.beginTransaction();
        realm.delete(Book.class);//check it again
        realm.commitTransaction();
    }
    //Find all objects in the Book.class
    public RealmResults<Book>getBooks(){
        return realm.where(Book.class).findAll();
    }
    //query a single item with the given id
    public Book getBook(String id){
        return realm.where(Book.class).equalTo("id",id).findFirst();
    }
    //check if book.class is empty
   /*
    public boolean hasBooks(){
     return  !realm.where(Book.class).isEmpty(String.valueOf(0));
     }
     */



    //query Example
    public RealmResults<Book> queyedBooks(){
        return  realm.where(Book.class)
                .contains("author","Author 0")
                .or()
                .contains("titile","Realm")
                .findAll();
    }


}
