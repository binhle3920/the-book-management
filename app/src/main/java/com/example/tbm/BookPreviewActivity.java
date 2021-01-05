package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookPreviewActivity extends AppCompatActivity {
    String curUser;
    String curBook;
    Database db;

    TextView bookTitle;
    TextView ratingNumber;
    TextView author;
    TextView cost;
    TextView description;
    ImageView cover;
    ImageButton backBtn, cartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_preview);

        db = Database.getInstance(this);

        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");

        if(getIntent() != null && getIntent().hasExtra("currentBook"))
            curBook = getIntent().getStringExtra("currentBook");

        connectView();
        displayBook();
    }

    private void connectView(){
        bookTitle = (TextView)findViewById(R.id.Title);
        author = (TextView)findViewById(R.id.AuthorName);
        cost = (TextView)findViewById(R.id.Cost);
        ratingNumber = (TextView)findViewById(R.id.RatingNumber);
        description = (TextView)findViewById(R.id.description);
        cover = (ImageView)findViewById(R.id.BookCover);

        Button ReadBtn = (Button) findViewById(R.id.readButton);
        ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookPreviewActivity.this, ReadingActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });

        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookPreviewActivity.this, HomepageActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });

        cartBtn = (ImageButton)findViewById(R.id.AddtoCartBtn);
        if(curUser.equals("admin")){
            cartBtn.setImageResource(R.drawable.edit_icon);
            cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BookPreviewActivity.this, ShoppingCartActivity.class);
                    intent.putExtra("currentUser", curUser);
                    intent.putExtra("currentBook", curBook);
                    startActivity(intent);
                }
            });
        }
        else if (curUser.equals("guest")){
            cartBtn.setImageResource(R.drawable.add_to_cart_icon);
            cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Please login first", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            cartBtn.setImageResource(R.drawable.add_to_cart_icon);
            cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db.addToCart(curUser, curBook, "1"))
                        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void displayBook(){
        String[] info = db.getBookInfo(curBook);

        Toast.makeText(this,  info[0], Toast.LENGTH_LONG).show();

        bookTitle.setText(info[1]);

        author.setText(info[2]);

        cost.setText(info[3] + " VND");

        ratingNumber.setText(info[4]);

        description.setText(info[5]);
        description.setMovementMethod(new ScrollingMovementMethod());

        String path = info[7];

        switch (path){
            case "MuonEmTheNaoCungKhongDu":
                cover.setImageResource(R.drawable.muonemthenaocungkhongdu_cover);
                break;
            case "ThachSanh":
                cover.setImageResource(R.drawable.thachsanh_cover);
                break;
            case "FairyTail":
                cover.setImageResource(R.drawable.fairytail_cover);
                break;
            case "Conan":
                cover.setImageResource(R.drawable.conan_cover);
                break;
            case "OnePiece":
                cover.setImageResource(R.drawable.onepiece_cover);
                break;
            case "TheBookOfMe":
                cover.setImageResource(R.drawable.thebestofme_cover);
                break;
            case "Java12Programming":
                cover.setImageResource(R.drawable.java12_cover);
                break;
            case "SoftwareEngineering9":
                cover.setImageResource(R.drawable.softwareengineering_cover);
                break;
            case "HtmlCss":
                cover.setImageResource(R.drawable.htmlcss_cover);
                break;
            case "AI":
                cover.setImageResource(R.drawable.ai_cover);
                break;
            case "Boruto":
                cover.setImageResource(R.drawable.boruto_cover);
                break;
            case "ThanDongDatViet":
                cover.setImageResource(R.drawable.thandongdatviet_cover);
                break;
            default:
                break;
        }

    }

}