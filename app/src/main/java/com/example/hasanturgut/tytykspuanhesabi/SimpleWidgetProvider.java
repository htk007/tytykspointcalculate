package com.example.hasanturgut.tytykspuanhesabi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by Hasan Turgut on 9.12.2017.
 */

//şimdilik bu yapı sadece widgette rastgele sayı generate ediyor, ancak bundan sonra uygulama viewin de girilen tarihi set eden
//ve ona göre gün olarak geri sayım şeklinde gösteren bir yapı olacak
//uygulama içerisinde de TYT ve YKS puan hesaplamaları olacaktır.
/*
* TYT için:
* Türkçe 40 soru
* Sosyal Bilimler 20 soru
* Temel Matematik 40 soru
* Fen Bilimleri 20 soru
*
* YKS için:
* Türk Dili Ve sosyal  40 soru
* Sosyal bilimler 40 soru
* Metematik 40 soru
* Fen Bilimleri 40 soru
* Yabancı Dil 80 soru
*
* Yukarıda belirtilmiş puanlara ek olarak Diploma Puanı(0-100) veya Ortaöğretim Başarı puanı da dahil edilebilir.(0-500)
*
*
*SONUC PUANLARI GOSTERİMİ:
*           #HamPuan# | #YerleştirmePuanı# | #DahaÖnceYerleştiysePuanı#
* TYT puanı:    "               "                       "
* YKS puanı:
*       EA:     "               "                       "
*      SÖZ:     "               "                       "
*      SAY:     "               "                       "
*      DİL:     "               "                       "
*
*      kaynakça: http://universitetercihleri.com/tyt-yks-puan-hesap
 *  */
public class SimpleWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_gosterge);

            //burada metin belirleme işlemimizi yaptık
            remoteViews.setTextViewText(R.id.textViewim, number);

            Intent intent = new Intent(context, SimpleWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}

