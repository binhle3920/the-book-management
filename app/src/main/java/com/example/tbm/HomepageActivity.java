package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    ViewPager viewPager;
    EditText input_field;
    private ImageButton SearchBtn;
    private ImageButton CartBtn;
    private EditText SearchBar;
    private ImageButton HomeBtn;
    private ImageButton LibraryBtn;
    private ImageButton ProfileBtn;

    private ImageButton SelectedBook;
    private List<ImageButton> book_list = new ArrayList<ImageButton>();

    Database db;
    String curUser = "guest";
    String curBook = "";
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
//        viewPager.setAdapter(viewPagerAdapter);
//
//        ImageButton img_9 = (ImageButton) findViewById(R.id.imageButton9);
//        img_9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), BookPreviewActivity.class);
//                startActivity(intent);
//            }
//        }

        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");
//        Toast.makeText(this, "Logged in as " + curUser, Toast.LENGTH_LONG).show();
        connectView();

        db = Database.getInstance(this);
        db.create_admin();
        db.createBookDatabase();
    }

    private void connectView(){
        // Search Screen
        SearchBtn = (ImageButton) findViewById(R.id.SearchBtn);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_field = (EditText)findViewById(R.id.SearchBar);
                String search_input = input_field.getText().toString();

                Intent intent = new Intent(HomepageActivity.this, SearchResultActivity.class);

                intent.putExtra("MyDatabase", (Parcelable) db);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("searchInput", search_input);
                startActivity(intent);
            }
        });

        // Cart Screen or Upload Screen
        CartBtn = (ImageButton) findViewById(R.id.CartBtn);
        if (curUser.equals("admin")){
            CartBtn.setImageResource(R.drawable.upload_icon);
            CartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomepageActivity.this, "Upload a new book", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            CartBtn.setImageResource(R.drawable.cart_icon);
            CartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomepageActivity.this, ShoppingCartActivity.class);

                    intent.putExtra("currentUser", curUser);

                    startActivity(intent);
                }
            });
        }

        // Library Screen
        LibraryBtn = (ImageButton) findViewById(R.id.libraryBtn);
        LibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, LibraryActivity.class);

//                intent.putExtra("MyDatabase", (Parcelable) db);
                intent.putExtra("currentUser", curUser);

                startActivity(intent);
            }
        });

        // Profile Screen
        ProfileBtn = (ImageButton) findViewById(R.id.profileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);

//                intent.putExtra("MyDatabase", (Parcelable) db);
                intent.putExtra("currentUser", curUser);

                startActivity(intent);
            }
        });

        //show book list
        initBookList();
//        for(index=0; index<book_list.size(); index++){
//
//            ImageButton temp = book_list.get(index);
//            temp.get(0).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    curBook = String.valueOf(0);
//                    Toast.makeText(HomepageActivity.this, curUser + " " + curBook, Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
//                    intent.putExtra("currentUser", curUser);
//                    intent.putExtra("currentBook", curBook);
//                    startActivity(intent);

                    // another solution
//                        button.setSelected(!button.isSelected());
//
//                        if (button.isSelected()) {
//                            //Handle selected state change
//                        } else {
//                            //Handle de-select state change
//                        }
//                }
//            });
//        }
    }

    private void initBookList(){
        book_list.add((ImageButton) findViewById(R.id.GoodBook01));
        book_list.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(1);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.GoodBook02));
        book_list.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(2);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.GoodBook03));
        book_list.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(3);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.GoodBook04));
        book_list.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(4);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.GoodBook05));
        book_list.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(5);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.GoodBook06));
        book_list.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(6);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });

        book_list.add((ImageButton) findViewById(R.id.RcmBook01));
        book_list.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(7);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.RcmBook02));
        book_list.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(8);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.RcmBook03));
        book_list.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(9);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.RcmBook04));
        book_list.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(10);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.RcmBook05));
        book_list.get(10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(11);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
        book_list.add((ImageButton) findViewById(R.id.RcmBook06));
        book_list.get(11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curBook = String.valueOf(12);
                Intent intent = new Intent(HomepageActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });
    }

}