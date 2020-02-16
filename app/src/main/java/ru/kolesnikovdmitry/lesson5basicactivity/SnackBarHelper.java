package ru.kolesnikovdmitry.lesson5basicactivity;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.ContentView;
import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarHelper {

    static void configSnackbar(Context context, Snackbar snackbar) {
        addMargins(snackbar);
        setRoundBourderBg(context, snackbar);
        //ViewCompat.setElevation(snackbar.getView(), 6f); //хз что за параметр и нафиг он нужен, ибо с ним ничего не меняется
    }


    private static void addMargins(Snackbar snackbar) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)  snackbar.getView().getLayoutParams(); //мы призываем элемент класса
        // ViewGroup.MarginLayoutParam - params, чтобы присвоить ему параметры в след строке
        params.setMargins(12, 12, 100, 12); //это насколько будет отступать снек бар от краев экрана
        snackbar.getView().setLayoutParams(params);
    }

    private static void setRoundBourderBg(Context context, Snackbar snackbar) {
        snackbar.getView().setBackground(context.getDrawable(R.drawable.snack_bar_maket)); //здесь мы назначаем цвет нашему снекбару
    }
}
