package app.realm.gnt.com.realmdatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private BooksAdapter mBooksAdapter;
    private Realm mRealm;
    private FloatingActionButton mFloatingActionButton;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerview;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fab_floating_button);
        mRecyclerview = (RecyclerView)findViewById(R.id.rv_simple_view);

        //get realm instance
        this.mRealm = RealmController.with(this).getRealm();

        //set Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        setUpRecycler();
        if(!Prefs.with(this).getPreLoad()){
            setRealmData();
        }

        //refresh realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getBooks());

        Toast.makeText(this, "Press card item for edit, long press to remove item", Toast.LENGTH_LONG).show();

        //add new item
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutInflater = MainActivity.this.getLayoutInflater();
                View content = mLayoutInflater.inflate(R.layout.edit_item, null);
                final EditText et_edittitle = (EditText)findViewById(R.id.et_title);
                final EditText et_editauthor = (EditText)findViewById(R.id.et_author);
                final EditText et_editthumnail = (EditText)findViewById(R.id.et_thumbnail);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(content)
                        .setTitle("Add Book")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Book book = new Book();
                                book.setId((int) (RealmController.getInstance().getBooks().size()+System.currentTimeMillis()));
                                book.setTitle(et_edittitle.getText().toString());
                                book.setAuthor(et_editauthor.getText().toString());
                                book.setImageUrl(et_editthumnail.getText().toString());

                                if(et_edittitle.getText()==null||et_edittitle.getText().toString().equals("")||et_edittitle.getText().toString().equals("")){

                                    Toast.makeText(MainActivity.this,"Entry not saved:title not added",Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    //Persist data easily
                                    mRealm.beginTransaction();
                                    mRealm.copyToRealm(book);
                                    mRealm.commitTransaction();
                                    mBooksAdapter.notifyDataSetChanged();

                                    mRecyclerview.scrollToPosition(RealmController.getInstance().getBooks().size());
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void setRealmAdapter(RealmResults<Book> books) {

        RealmBooksAdapter realmAdapter = new RealmBooksAdapter(this.getApplicationContext(),books);
        //set the data and tell the Recyclerview to draw
        mBooksAdapter = new BooksAdapter(this);
        mRecyclerview.setAdapter(mBooksAdapter);

    }

    private void setRealmData() {
        ArrayList<Book>bookList = new ArrayList<>();

        Book book = new Book();
        book.setId((int) (1+System.currentTimeMillis()));
        book.setAuthor("Retro Mier");
        book.setTitle("Android 4 Application Development");
        book.setImageUrl("http://api.androidhive.info/images/realm/1.png");
        bookList.add(book);

        book = new Book();
        book.setId((int) (2 + System.currentTimeMillis()));
        book.setAuthor("Itzik Ben-Gan");
        book.setTitle("Microsoft SQL Server 2012 T-SQL Fundamentals");
        book.setImageUrl("http://api.androidhive.info/images/realm/2.png");
        bookList.add(book);

        book = new Book();
        book.setId((int) (3 + System.currentTimeMillis()));
        book.setAuthor("Magnus Lie Hetland");
        book.setTitle("Beginning Python: From Novice To Professional Paperback");
        book.setImageUrl("http://api.androidhive.info/images/realm/3.png");
        bookList.add(book);

        book = new Book();
        book.setId((int) (4 + System.currentTimeMillis()));
        book.setAuthor("Chad Fowler");
        book.setTitle("The Passionate Programmer: Creating a Remarkable Career in Software Development");
        book.setImageUrl("http://api.androidhive.info/images/realm/4.png");
        bookList.add(book);

        book = new Book();
        book.setId((int) (5 + System.currentTimeMillis()));
        book.setAuthor("Yashavant Kanetkar");
        book.setTitle("Written Test Questions In C Programming");
        book.setImageUrl("http://api.androidhive.info/images/realm/5.png");
        bookList.add(book);

        for(Book b:bookList){
            //persist data easily
            mRealm.beginTransaction();
            mRealm.copyToRealm(b);
            mRealm.commitTransaction();
        }

        Prefs.with(this).setPreLoad(true);
    }

    private void setUpRecycler(){

        mRecyclerview.setHasFixedSize(false);

        //use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);

        mBooksAdapter = new BooksAdapter(this);
        mRecyclerview.setAdapter(mBooksAdapter);
    }
}
