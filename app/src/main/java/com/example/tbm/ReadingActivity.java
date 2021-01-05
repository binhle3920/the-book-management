package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadingActivity extends AppCompatActivity {
    ImageButton BackBtn;
    String curBook, curUser, numberPage;
    ImageView page01, page02, page03, cover;
    TextView title, author;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading);

        title = (TextView)findViewById(R.id.BookTitle);
        author = (TextView)findViewById(R.id.Author);
        cover = (ImageView)findViewById(R.id.BookCover);
        page01 = (ImageView)findViewById(R.id.page01);
        page02 = (ImageView)findViewById(R.id.page02);
        page03 = (ImageView)findViewById(R.id.page03);

        db = Database.getInstance(this);

        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");

        if(getIntent() != null && getIntent().hasExtra("currentBook"))
            curBook = getIntent().getStringExtra("currentBook");

        BackBtn = (ImageButton) findViewById(R.id.backBtn);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingActivity.this, BookPreviewActivity.class);
                intent.putExtra("currentUser", curUser);
                intent.putExtra("currentBook", curBook);
                startActivity(intent);
            }
        });

        displayBook();
    }


    private void displayBook(){
        String[] info = db.getBookInfo(curBook);

        Toast.makeText(this,  info[0], Toast.LENGTH_LONG).show();

        title.setText(info[1]);
        author.setText(info[2]);
        numberPage = info[6];

        String path = info[7];

        switch (path){
            case "MuonEmTheNaoCungKhongDu":
                page01.setImageResource(R.drawable.muonemthenaocungkhongdu_01);
                page02.setImageResource(R.drawable.muonemthenaocungkhongdu_02);
                page03.setImageResource(R.drawable.muonemthenaocungkhongdu_03);
                cover.setImageResource(R.drawable.book01);
                break;
            case "ThachSanh":
                page01.setImageResource(R.drawable.thachsanh_01);
                page02.setImageResource(R.drawable.thachsanh_02);
                page03.setImageResource(R.drawable.thachsanh_03);
                cover.setImageResource(R.drawable.book02);
                break;
            case "FairyTail":
                page01.setImageResource(R.drawable.fairytail_01);
                page02.setImageResource(R.drawable.fairytail_02);
                page03.setImageResource(R.drawable.fairytail_03);
                cover.setImageResource(R.drawable.book03);
                break;
            case "Conan":
                page01.setImageResource(R.drawable.conan_01);
                page02.setImageResource(R.drawable.conan_02);
                page03.setImageResource(R.drawable.conan_03);
                cover.setImageResource(R.drawable.book04);
                break;
            case "OnePiece":
                page01.setImageResource(R.drawable.onepiece_01);
                page02.setImageResource(R.drawable.onepiece_02);
                page03.setImageResource(R.drawable.onepiece_03);
                cover.setImageResource(R.drawable.book05);
                break;
            case "TheBookOfMe":
                page01.setImageResource(R.drawable.thebestofme_01);
                page02.setImageResource(R.drawable.thebestofme_02);
                page03.setImageResource(R.drawable.thebestofme_03);
                cover.setImageResource(R.drawable.book06);
                break;
            case "Java12Programming":
                page01.setImageResource(R.drawable.java12_01);
                page02.setImageResource(R.drawable.java12_02);
                page03.setImageResource(R.drawable.java12_03);
                cover.setImageResource(R.drawable.book07);
                break;
            case "SoftwareEngineering9":
                page01.setImageResource(R.drawable.softwareengineering_01);
                page02.setImageResource(R.drawable.softwareengineering_02);
                page03.setImageResource(R.drawable.softwareengineering_03);
                cover.setImageResource(R.drawable.book08);
                break;
            case "HtmlCss":
                page01.setImageResource(R.drawable.htmlcss_cover);
                page02.setImageResource(R.drawable.htmlcss_02);
                page03.setImageResource(R.drawable.htmlcss_03);
                cover.setImageResource(R.drawable.book09);
                break;
            case "AI":
                page01.setImageResource(R.drawable.ai_01);
                page02.setImageResource(R.drawable.ai_02);
                page03.setImageResource(R.drawable.ai_03);
                cover.setImageResource(R.drawable.book10);
                break;
            case "Boruto":
                page01.setImageResource(R.drawable.boruto_01);
                page02.setImageResource(R.drawable.boruto_02);
                page03.setImageResource(R.drawable.boruto_03);
                cover.setImageResource(R.drawable.book13);
                break;
            case "ThanDongDatViet":
                page01.setImageResource(R.drawable.thandongdatviet_01);
                page02.setImageResource(R.drawable.thandongdatviet_02);
                page03.setImageResource(R.drawable.thachsanh_03);
                cover.setImageResource(R.drawable.book12);
                break;
            default:
                break;
        }


    }


}