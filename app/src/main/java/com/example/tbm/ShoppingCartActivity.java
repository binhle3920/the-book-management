package com.example.tbm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    private ImageButton HomeBtn;
    private ImageButton LibraryBtn;
    private ImageButton ProfileBtn;

    String curUser, curBook;
    Database db;
    int numBook = 0;
    LinearLayout content;
    int total_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        db = Database.getInstance(this);
        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");

        if(getIntent() != null && getIntent().hasExtra("currentBook"))
            curBook = getIntent().getStringExtra("currentBook");

        connectView();
    }

    private void connectView(){
        HomeBtn = (ImageButton) findViewById(R.id.homeBtn);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, HomepageActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

        LibraryBtn = (ImageButton) findViewById(R.id.libraryBtn);
        LibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, LibraryActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

        ProfileBtn = (ImageButton) findViewById(R.id.profileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, ProfileActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

        content = (LinearLayout)findViewById(R.id.booklist);
        List<String[]> book_list = db.getBookInCart(curUser);
        numBook = book_list.size();
        for(int i=0; i<numBook; i++){
            String[] book_info = book_list.get(i);  // id, quantity, title, author, price, num_page, path
            LayoutInflater inflater = LayoutInflater.from(this);
            View newLayout = inflater.inflate(R.layout.book_display, content,false);

            TextView quantity = (TextView) newLayout.findViewById(R.id.book_quantity);
            quantity.setText(book_info[1]);

            TextView title = (TextView) newLayout.findViewById(R.id.book_title);
            title.setText(book_info[2]);

            TextView author = (TextView) newLayout.findViewById(R.id.book_author);
            author.setText(book_info[3]);

            TextView price = (TextView) newLayout.findViewById(R.id.book_price);
            price.setText(book_info[4] + " VND");
            total_price += Integer.valueOf(book_info[4]);

            ImageView cover = (ImageView) newLayout.findViewById(R.id.book_cover);
            switch (book_info[6]){  //switch path
                case "MuonEmTheNaoCungKhongDu":
                    cover.setImageResource(R.drawable.book01);
                    break;
                case "ThachSanh":
                    cover.setImageResource(R.drawable.book02);
                    break;
                case "FairyTail":
                    cover.setImageResource(R.drawable.book03);
                    break;
                case "Conan":
                    cover.setImageResource(R.drawable.book04);
                    break;
                case "OnePiece":
                    cover.setImageResource(R.drawable.book05);
                    break;
                case "TheBookOfMe":
                    cover.setImageResource(R.drawable.book06);
                    break;
                case "Java12Programming":
                    cover.setImageResource(R.drawable.book07);
                    break;
                case "SoftwareEngineering9":
                    cover.setImageResource(R.drawable.book08);
                    break;
                case "HtmlCss":
                    cover.setImageResource(R.drawable.book09);
                    break;
                case "AI":
                    cover.setImageResource(R.drawable.book10);
                    break;
                case "Boruto":
                    cover.setImageResource(R.drawable.book13);
                    break;
                case "ThanDongDatViet":
                    cover.setImageResource(R.drawable.book12);
                    break;
                default:
                    break;
            }

            content.addView(newLayout);
        }

        TextView subTotal = (TextView) findViewById(R.id.subtotal);
        subTotal.setText(String.valueOf(total_price) + " VND");

    }
}