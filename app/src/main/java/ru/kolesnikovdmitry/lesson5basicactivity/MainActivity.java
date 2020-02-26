package ru.kolesnikovdmitry.lesson5basicactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.Snackbar.Callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Чтобы импортировать класс Snackbar нужно в build.gradle добавить библиотеку 
    //implementation 'com.google.android.material:material:1.0.0' 
    
    
    Snackbar mSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab); //ВАЖНО! Чтобы ActionBar всплывал и не загораживал нижние элементы, нужно
        //прописать для файла content_main в androidx.constraintlayout.widget.ConstraintLayout атрибут app:layout_dodgeInsetEdges="bottom"
        //тогда акшнбар будет всплывать, а все элементы приподниматься вслед за ним
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSnackbar = Snackbar.make(view, "HELLO", Snackbar.LENGTH_INDEFINITE); //снекбар появляется на неопределенный срок,
                // пока не смахнешь его или не закроешь методом Dismiss(), так же можно свою длительность задавать.
                View snackbarView = mSnackbar.getView();
                mSnackbar.setAction("Exit", snackBarOnClickListener);//делаем кнопку на снекбаре, можно только одну кнопку.


                snackbarView.setBackgroundColor(Color.YELLOW); //меняем цвет фона actionBar
                TextView snackBarTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text); //получаем доступ к изменению текста в actionBar
                snackBarTextView.setTextColor(getResources().getColor(R.color.primary_light)); //меням цвет текста
                snackBarTextView.setText("GOODBYE"); //задаем текст программно
                mSnackbar.show();

            }
        });
    }



    View.OnClickListener snackBarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSnackbar.dismiss(); //закрываем  snackbar.
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAboutActivityMain:
                Toast.makeText(getApplicationContext(), "ЧТО НАДО?", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuItemSettingsActivityMain:
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickActivityMain(View view) {
        switch (view.getId()) {
            case R.id.buttonDismissActivityMain:
                mSnackbar.dismiss();
            case R.id.buttonNewSnackBar:
                //вызываем свой самодельный снекбар
                Snackbar kSnackbar = Snackbar.make(view, "Hello again", Snackbar.LENGTH_SHORT);
                SnackBarHelper.configSnackbar(getApplicationContext(), kSnackbar); //здесь мы изменяем внешний вид с помощью заранее заготовенного класса и разметки
                //в файле drawable/snack_bar_maket
                kSnackbar.setDuration(5000); //устанавливааем длительность
                kSnackbar.show();
        }
    }
}
