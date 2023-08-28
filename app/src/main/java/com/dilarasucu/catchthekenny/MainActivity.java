package com.dilarasucu.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.ListPreference;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timetext;
    TextView scoretext;

    int score;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;
    Handler handler;

    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timetext=findViewById(R.id.timeText);
        scoretext=findViewById(R.id.scoreText);
        score=0;

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        //burada bir imageviem adında bir dizi tanımladık ve bu diziye deger atadık
        //bunu bir donguye almak için yaptık

        hideImages();


        new CountDownTimer(10000,1000){//verdiğiğmiz sayı degerlerıyle geri sayım yapmak için kullanılan bir metot
            //burada 10 saniyeden geri saymaya baslayacak.ve burada kullanmak ıstedııgımız zaman cinsinden ifadeleri milisaniyeye donusturerek vermemız gerekıyor
//bu metot ıkı parametre alır birincisi ne kadar sure geri sayılacagı ikincisi ne sıklıkla sayılacak.yani burada 10 saniyeyi birer birer geri say dedik

            @Override
            public void onTick(long l) { //bu metot countdowntimerin metodudur.
                // bu metot her bir sanıyede ne yapılacagını verir.yanı bu metotu burada her geri sayılan saniyede kalan zamanı yazdırmak ıcın kullandık
                timetext.setText("Time:" + l/1000);

            }

            @Override
            public void onFinish() {//bu metot countdowntimerin metodudur.bu da zaman bitince ne olacagını verir.
                timetext.setText("time off");//burada zaman bittiginde time off diye yazdırır
                handler.removeCallbacks(runnable); //runnablryi durdurmak için kullanılır
                for (ImageView image: imageArray){//image eger dizideki imagelerden biriyse ne olacagını dondurur
                    image.setVisibility(View.INVISIBLE);
                    //burada setvisibility metodu bir gorunumun gorunurluk durumunu ayarlamak için kullanılır
                    //view.invisible, setvisibility ile gorunumu ayaralamak için kullanılan durumlardan biridir.bu durum görunumun görunmez olmasını saglar
                    //bunu finishin altında kullanırsak zaman bittiginde kennyleri gorunmez hale getirir

                    AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);//kullanıcıya bilgi, uyarı veya seçenekler sunmak için kullanılır.
                    alert.setTitle("restart?");
                    alert.setMessage("are you sure to restart game");
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {//burada yes butonuna tıklanınca ne olacagını verir
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=getIntent();
                            //normalde intent activityler arasında gecişi saglar ama burda baska bir actıvity olmadıgı için tekrar bu activityi acar
                            finish(); // uygulamayı cok yormamak için guncel activityi tamamen bitirecek ve yeniden baslatmak için kullanılır
                            startActivity(intent);// burda intenti baslattık

                        }
                    });

                    alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"game over!",Toast.LENGTH_LONG).show();
                        }
                    });
                    alert.show();
                }

            }
        }.start();
    }

    public void increaseScore(View view){//burada her  kennylere tıklandıgında scoreyı bir arttırır
        score++;
        scoretext.setText("Score:" + score);

    }

    public void hideImages(){ //bu metodu imageleri saklamak için actık
        handler = new Handler();

        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int i=random.nextInt(9); //0-8 arası (0 ve 8 dahil 9 tane sayı ) rastgele olarak sayılar gelir
                imageArray[i].setVisibility(View.VISIBLE);
                // biz aldıgımız bu rastgele sayıları diziye veriyoruz ve dizi gelen rastgele sayıyı index olarak alıyor ve view.visible ile
                // o gorunumu gorunur hale getiriyor
                //yani biz tum gorunumu gorunmez hale getirdik sonra biz gorunumde 9 tane foto koyduk ve bunları 1den 9a kadar numaralarla isimlendirdik
                //sonra kenny yi anlık olarak yakalamak için ve rastgele sayılar olusturduk ve bu sayılari diziye verdik dizi gelen rastgele sayıdaki
                //indexindeki fotoyu anlık olarak gorunur hale getirdik

                handler.postDelayed(this,500); //bu gorunumlerin ne kadar zaman içinde tekrar gorunur olmasını saglamk için  kulanılır
                //yani her yarım saniyede bir rastgele olarak gelen sayının dizideki indexini gorunur yapar

            }
        };

        handler.post(runnable);// runnableyi calıstırmak için kulllanılır





    }
}