package com.example.tbm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database extends SQLiteOpenHelper {
    private static Database sInstance = null;
    public static synchronized Database getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new Database(context);
        }

//        create_admin();
//        createBookDatabase();
        return sInstance;
    }

    public Database(Context context) {
        super(context, "TBM.db", null, 1);
    }

//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//        if (!db.isReadOnly()) {
//            db.execSQL("PRAGMA foreign_keys=ON;");
//        }
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BOOK(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Author TEXT, Price LONG, Rating DOUBLE, Description TEXT, Num_page INTEGER, Path TEXT)");
        db.execSQL("CREATE TABLE USER(Username TEXT PRIMARY KEY, Email TEXT, Password TEXT, DisplayName TEXT)");
        db.execSQL("CREATE TABLE LIBRARY(Username TEXT, Book_ID TEXT, PRIMARY KEY(Username, Book_ID))");
        db.execSQL("CREATE TABLE CART(Username TEXT, Book_ID TEXT, Quantiy INTEGER, PRIMARY KEY(Username, Book_ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS BOOK");
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS LIBRARY");
        db.execSQL("DROP TABLE IF EXISTS CART");
        onCreate(db);
    }

    // create admin account
    public String create_admin(){
        if (checkUser("admin")) {
            // create default admin account
            SQLiteDatabase db = getWritableDatabase();

            String password = HashHelper.getMd5("admin") + ";";
//            String sql = "UPDATE USER SET Password = '21232f297a57a5a743894a0e4a801fc3' WHERE Username = 'admin'";
            String sql = "INSERT INTO USER VALUES('admin', 'TBM@gmail.com', " + password + " , 'Admin')";
            db.execSQL(sql);
            db.close();
            return sql;
        }
        return "Fail";
    }

    // create book database
    public void createBookDatabase(){
        SQLiteDatabase db = getWritableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM BOOK", null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();
        if (!empty)
            return;
        insertNewBook("Muốn em thế nào cũng không đủ", "Ngô Đồng Hạp Tử","50000", "4.0", "Cô cố gắng kìm nén bản thân, nhưng càng cố không nghĩ tới thì lại càng không thể dừng lại. Nhớ cánh tay rắn rỏi ấm áp của anh vây chặt lấy cô, muốn chiều dài của anh mạnh mẽ ra vào trong cô… Nghĩ đến đó, cô lại không thể khống chế được mình. Ngón tay mất tự chủ lướt xuống nơi riêng tư, tại chỗ lõm ở cửa huyệt nhấn nhẹ một cái, trong nháy mắt đũng quần ướt thành vũng, miệng nhỏ không nhịn được khoái cảm khẽ rên lên.\n" +
                "Bỗng ngoài cửa vang lên tiếng mở khóa, sau đó là tiếng mở cửa phòng cùng âm thanh cởi áo khoác. Cô vội vàng rụt tay về, kéo chăn lên im lặng nhắm mắt lại.", "86", "MuonEmTheNaoCungKhongDu");
        insertNewBook("Thạch Sanh", "Khuyết danh", "20000","4.5", "Tranh Truyện Dân Gian Việt Nam - Thạch Sanh\n" +
                "\n" +
                "Bộ truyện Tranh truyện dân gian Việt Nam gồm hơn 100 cuốn, tuyển chọn những câu chuyện dân gian giúp các em biết sống đẹp và trân trọng truyền thống cha ông.\n" +
                "\n" +
                "“Đàn kêu tích tịch tình tangAi mang công chúa dưới hang trở về!”\n" +
                "\n" +
                "Từ trong ngục sâu, tiếng đàn trách móc, oán thương  đã khiến công chúa cất lên tiếng nói. Thế rồi Thạch Sanh - người anh hùng diệt xà tinh được minh oan, còn Lý Thông - kẻ cướp công, hại người bị biến thành con bọ hung suốt ngày chui rúc ở nơi bẩn thỉu. Nhưng câu chuyện về chàng Thạch Sanh chưa kết thúc, vì vừa lúc đó, đại quân 18 nước láng giềng ầm ầm kéo đến vây kinh đô, đòi bắt công chúa…", "20", "ThachSanh");
        insertNewBook("Fairy Tail", "Mashima Hiro", "30000", "4.6", "Sê-ri theo sau những cuộc phiêu lưu của Lucy Heartfilia, một pháp sư trẻ tham gia hội pháp sư Fairy Tail và lập nhóm với Natsu Dragneel khi cậu đanh tìm kiếm một con rồng tên Igneel.", "50", "FairyTail");
        insertNewBook("Conan", "Aoyama Gosho", "25000", "4.7", "Kudo Shinichi là một thám tử sanh viên trung học phổ thông rất nổi tiếng, thường xuyên giúp cảnh sát phá án các vụ án khó khăn.Trong một lần khi đang theo dõi một vụ tống tiền, cậu đã bị thành viên của Tổ chức Áo đen phát hiện. Chúng đánh gục Shinichi, làm cậu bất tỉnh và ép cậu uống loại thuốc độc APTX 4869 mà Tổ chức vừa điều chế nhằm bịt đầu mối. Tuy vậy, thứ thuốc ấy không giết chết cậu mà lại khiến cậu teo nhỏ thành một đứa trẻ. Sau đó, cậu tự xưng là Edogawa Conan và được Mori Ran - bạn gái của cậu khi còn là Kudo Shinichi - nhận nuôi và mang về văn phòng thám tử của bố cô là Mori Kogoro. Xuyên suốt series, Conan đã âm thầm hỗ trợ ông Mori phá các vụ án. Đồng thời cậu cũng phải nhập học Tiểu học, nhờ đó mà kết thân với nhiều người và lập ra Đội thám tử nhí.\n" +
                "\n" +
                "Về sau một học sinh tiểu học bất đắc dĩ khác tên là Haibara Ai đã vào học lớp của Conan và tiết lộ rằng cô chính là thành viên của người đã tạo ra loại thuốc APTX 4869 mà Conan đã bị ép uống, vì muốn thoát khỏi Tổ chức Áo đen nên đã uống viên thuốc đó để tự sát. Kết quả là, thay vì chết thì cơ thể Haibara cũng bị teo nhỏ như Conan. Trong vài vụ án liên quan đến Tổ chức Áo đen, Conan đã hỗ trợ các điệp viên của FBI và CIA.", "64", "Conan");

        insertNewBook("One Piece", "Eiichiro Oda", "50000", "4.9", "One Piece là câu truyện kể về Luffy và các thuyền viên của mình. Khi còn nhỏ, Luffy ước mơ trở thành Vua Hải Tặc. Cuộc sống của cậu bé thay đổi khi cậu vô tình có được sức mạnh có thể co dãn như cao su, nhưng đổi lại, cậu không bao giờ có thể bơi được nữa. Giờ đây, Luffy cùng những người bạn hải tặc của mình ra khơi tìm kiếm kho báu One Piece, kho báu vĩ đại nhất trên thế giới.\n" +
                "\n" +
                "Trong One Piece, mỗi nhân vật trong đều mang một nét cá tính đặc sắc kết hợp cùng các tình huống kịch tính, lối dẫn truyện hấp dẫn chứa đầy các bước ngoặt bất ngờ và cũng vô cùng hài hước đã biến One Piece trở thành một trong những bộ truyện nổi tiếng nhất không thể bỏ qua. Hãy đọc One Piece để hòa mình vào một thế giới của những hải tặc rộng lớn, đầy màu sắc, sống động và thú vị, cùng đắm chìm với những nhân vật yêu tự do, trên hành trình đi tìm ước mơ của mình.", "72", "OnePiece");
        insertNewBook("The Best Of Me", "Nicholas Sparks", "120000", "4.2", "When I set out to write this novel, I knew I wanted to focus on middle-aged characters—people in their forties who are really beginning to confront the “what-if” questions, and who are starting to second-guess the choices they made when they were younger. For Amanda, this is asking herself what would’ve happened if she married the man she loved rather than the someone else?\n" +
                " \n" +
                "I actually first used a funeral to bring old friends together in an old, unfinished manuscript, but I used it again in The Best of Me because it was a natural fit with these characters. When someone dies, it really prompts those what-if questions—it makes you look long and hard at the life you are living in a way that I think is essential to Amanda’s and Dawson’s growth throughout the book. With these big questions in mind, the story began to develop in my head and, eventually, on the page.", "200", "TheBestOfMe");
        insertNewBook("Learn Java 12 Programming", "Nick Samoylov", "300000", "4.8", "Java is one of the preferred languages among developers, used in everything right from smartphones, and game consoles to even supercomputers, and its new features simply add to the richness of the language. This book on Java programming begins by helping you learn how to install the Java Development Kit. You will then focus on understanding object-oriented programming (OOP), with exclusive insights into concepts like abstraction, encapsulation, inheritance, and polymorphism, which will help you when programming for real-world apps. Next, you'll cover fundamental programming structures of Java such as data structures and algorithms that will serve as the building blocks for your apps. You will also delve into core programming topics that will assist you with error handling, debugging, and testing your apps. As you progress, you'll move on to advanced topics such as Java libraries, database management, and network programming, which will hone your skills in building professional-grade apps.\n" +
                "\n" +
                "Further on, you'll understand how to create a graphic user interface using JavaFX and learn to build scalable apps by taking advantage of reactive and functional programming.\n" +
                "\n" +
                "By the end of this book, you'll not only be well versed with Java 10, 11, and 12, but also gain a perspective into the future of this language and software development in general.", "320", "LearnJava12Programming");
        insertNewBook("Software Engineering", "Sommerville", "400000", "4.7", "A comprehensive textbook on software engineering\n" +
                "The tenth edition of my Software Engineering textbook was published in April 2015. The book is organized into four parts and focuses on the methods, tools and techniques used in the development of software systems. This edition is oriented towards systems engineering with new chapters on systems engineering, resilience engineering and systems of systems.\n" +
                "\n" +
                "‘Software Engineering’ has been designed to support both introductory and advanced courses in software engineering.\n" +
                "\n" +
                "GDPR compliance: I do not set cookies or store personal information about visitors to this site.", "450", "SoftwareEngineering");
        insertNewBook("HTML and CSS", "Jon Duckett", "350000", "4.8", "A full-color introduction to the basics of HTML and CSS from the publishers of Wrox!\n" +
                "Every day, more and more people want to learn some HTML and CSS. Joining the professional web designers and programmers are new audiences who need to know a little bit of code at work (update a content management system or e-commerce store) and those who want to make their personal blogs more attractive. Many books teaching HTML and CSS are dry and only written for those who want to become programmers, which is why this book takes an entirely new approach.\n" +
                "\n" +
                "Introduces HTML and CSS in a way that makes them accessible to everyone―hobbyists, students, and professionals―and it’s full-color throughout\n" +
                "Utilizes information graphics and lifestyle photography to explain the topics in a simple way that is engaging\n" +
                "Boasts a unique structure that allows you to progress through the chapters from beginning to end or just dip into topics of particular interest at your leisure\n" +
                "This educational book is one that you will enjoy picking up, reading, then referring back to. It will make you wish other technical topics were presented in such a simple, attractive and engaging way!", "520", "HtmlCss");
        insertNewBook("Ứng dụng trí tuệ nhân tạo để dẫn đầu", "Bernard Marr", "380000", "4.7", "Trong bối cảnh 95% dự án AI/ML (Artificial Inteligence/Machine Learning) thất bại, nếu bạn muốn biết những công ty công nghệ tiên phong và các công ty trong lĩnh vực bán lẻ, hàng tiêu dùng, thực phẩm, đồ uống, truyền thông, giải trí, viễn thông, dịch vụ, tài chính, chăm sóc sức khỏe, sản xuất, ô tô, không gian, và công nghiệp 4.0 trên thế giới áp dụng thực tế thành công AI/ML như thế nào, hãy đọc cuốn sách này.\n" +
                "\n" +
                "          Cuốn sách này sẽ mang lại cho độc giả những lợi thế cực kỳ to lớn cho việc nắm bắt cơ hội mà AI đang mang lại cho công việc kinh doanh và ngành công nghiệp của riêng bạn.", "230", "AI");
        insertNewBook("Boruto", "Masashi Kishimoto", "35000", "4.9", "Years have passed since Naruto and Sasuke teamed up to defeat Kaguya, the progenitor of chakra and the greatest threat the ninja world has ever faced. Times are now peaceful and the new generation of shinobi has not experienced the same hardships as its parents. Perhaps that is why Boruto would rather play video games than train. However, one passion does burn deep in this ninja boy’s heart, and that is the desire to defeat his father!", "75", "Boruto");
        insertNewBook("Thần đồng đất Việt", "Lê Linh", "20000", "4.9", "Truyện lấy bối cảnh là thời Hậu Lê, nhưng những sự kiện xảy ra trong truyện không trùng lặp với những sự kiện xảy ra trên thực tế. Tuy vậy, hầu hết những sự kiện chính xảy ra trong Thần Đồng Đất Việt đều dựa trên những câu truyện, điển tích lịch sử có thật của Việt Nam. Tác phẩm này kể lại những câu chuyện về cuộc đời của Lê Tí, một Trạng nguyên của Đại Việt cùng với những người bạn thân của cậu là Sửu Ẹo, Dần Béo và Cả Mẹo. Sự ra đời của Trạng Tí cũng không bình thường. Kiếp trước cậu vốn là một bậc thần tiên có kiến thức uyên bác trên Thiên Đình, sau đó được đầu thai xuống trần gian để giúp đỡ Đại Việt. Mẹ của Tí là bà Hai hậu, sau khi đi cày về mệt mỏi đã ngồi lên một hòn đá để nghỉ ngơi và có mang rồi sau đó sinh ra cậu.\n" +
                "\n" +
                "Từ nhỏ, Tí đã thể hiện mình là một người con hiếu thảo, ham học và có trí thông minh hơn người. Ngay cả Đồ Kiết, thầy dạy của cậu cũng phải ngạc nhiên về kiến thức của cậu. Ở làng Phan Thị, với tài trí của mình, cậu cũng đã giúp mẹ, các bạn của mình và những người dân trong làng giải quyết nhiều vấn đề khó khăn. Vượt qua ba kì thi Hương, Hội, Đình một cách xuất sắc, cậu trở thành trạng nguyên nhỏ tuổi nhất của Đại Việt. Sau đó, cậu cũng được Đại Minh công nhận là Lưỡng Quốc Trạng nguyên.\n" +
                "\n" +
                "Tí cùng Sửu, Dần và Cả Mẹo cũng đã có công lớn trong việc phò trợ vua Lê chống lại sự xâm lược của Đại Minh và đối phó với các sứ thần mà Đại Minh cử sang. Trong triều đình, cậu là một vị quan thanh liêm chính trực nên được công chúa Phương Thìn yêu mến, nhưng cũng chính vì vậy mà cậu luôn bị Tể Tướng Tào Hống và những người trong gia đình là hai đứa con ông coi là cái gai trong mắt và tìm mọi cách để hạ nhục cậu, tuy nhiên trong phần lớn các câu chuyện cậu là người chiến thắng. Cũng nhờ tài trí vượt bậc, Trạng Tí được vua tin tưởng giao trọng trách đi sứ Bắc Quốc nhiều lần. Ở đó, Tí cũng gặp phải nhiều khó khăn do vua Bắc Quốc và Vương Thừa Tướng tạo ra nhằm ám hại cậu và làm tổn hại uy tín Đại Việt, thậm chí có lần suýt chút nữa thì cậu thiệt mạng. Tuy đã ra làm quan nhưng đôi lúc cậu vẫn được vua cho phép về quê để chăm sóc mẹ và giúp đỡ dân làng. Và tất nhiên, tài trí và sự giúp đỡ nhiệt tình từ những người bạn tốt đã giúp những người dân rất nhiều.", "65", "ThanDongDatViet");

        db.close();
    }

    // check if user exists
    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where Username =?", new String[]{username}); //, new String[]{username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //check if email exists
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where Email = ?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //get password
    public String getPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where Username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("Password"));
        }
        else {
            return "wrong";
        }
    }

    // insert new user
    public boolean insertNewUser(String username, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Username", username);
        cv.put("Email", email);
        cv.put("Password", password);
        cv.put("DisplayName", username);

        long ins =  db.insert("USER", null, cv);
        db.close();
        return (ins != -1);
    }

    // remove user
    public boolean deleteUser(String name) {
        SQLiteDatabase db = getWritableDatabase();
        long ins =  db.delete("USER", "Username =?", new String[]{name});
        db.close();
        return (ins > 0);
    }

    // insert new book
    public boolean insertNewBook(String title, String author, String price, String rating, String description, String num_page, String path){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put("ID", null);
        cv.put("Title", title);
        cv.put("Author", author);
        cv.put("Price", price);
        cv.put("Rating", rating);
        cv.put("Description", description);
        cv.put("Num_page", num_page);
        cv.put("Path", path);

        long ins =  db.insert("BOOK", null, cv);
        db.close();
        return (ins != -1);
    }

    //getBookInfo
    public String[] getBookInfo(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BOOK WHERE ID = ?", new String[]{id});
        String[] ans = new String[8];
        if (cursor.moveToFirst()){
            ans[0] = String.valueOf(cursor.getInt(0));
            ans[1] = cursor.getString(1);
            ans[2] = cursor.getString(2);
            ans[3] = String.valueOf(cursor.getLong(3));
            ans[4] = String.valueOf(cursor.getDouble(4));
            ans[5] = cursor.getString(5);
            ans[6] = String.valueOf(cursor.getInt(6));
            ans[7] = cursor.getString(7);
        }
        db.close();
        return ans;
    }

    //add a book to cart
    public boolean addToCart(String username, String book_id, String quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", username);
        cv.put("Book_ID", book_id);
        cv.put("Quantiy", quantity);    // misspell

        long ins = db.insert("CART", null, cv);
        db.close();
        return (ins != -1);
    }

    // get user' list book in cart
    public List<String[]> BookInCart(String username){
        List<String[]> ans = new ArrayList<String[]>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM CART WHERE Username =?", new String[]{username});
        while (cur.moveToNext()) {
            String[] temp = new String[2];
            temp[0] = cur.getString(1);  // book id
            temp[1] = cur.getString(2); // quantity

            ans.add(temp);
        }
        cur.close();

        return ans;
    }

    //get book information(title, author, price, num_page, path) in cart
    public String[] getInfoCartBook(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BOOK WHERE ID = ?", new String[]{id});
        String[] ans = new String[5];
        if (cursor.moveToFirst()){
            ans[0] = cursor.getString(1);   //title
            ans[1] = cursor.getString(2);   //author
            ans[2] = String.valueOf(cursor.getLong(3)); //price
            ans[3] = String.valueOf(cursor.getInt(6)); //num_page
            ans[4] = cursor.getString(7);   //path
        }
        db.close();
        return ans;
    }

    public List<String[]> getBookInCart(String username){
        List<String[]> ans = new ArrayList<>();

        List<String[]> books = BookInCart(username);
        int numBook = books.size();

        for(int i=0; i<numBook; i++){
            String[] temp = new String[7];
            String id = books.get(i)[0];
            String quantity = books.get(i)[1];

            String[] info = getInfoCartBook(id);

            temp[0] = id;
            temp[1] = quantity;
            temp[2] = info[0];  // title
            temp[3] = info[1];  // author
            temp[4] = info[2];  // price
            temp[5] = info[3];  // num_page
            temp[6] = info[4];  // path

            ans.add(temp);
        }
        return ans;
    }

}
