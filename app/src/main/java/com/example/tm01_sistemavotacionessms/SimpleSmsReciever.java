package com.example.tm01_sistemavotacionessms;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.core.content.ContextCompat;

public class SimpleSmsReciever extends BroadcastReceiver {
    protected ControllerDB control;
    ArrayList<Competitor> listC;
    Context c;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        control = new ControllerDB(context);
        c = context;

        if (bundle != null) {
            Object[] mensajes = (Object[]) bundle.get("pdus");

            for (int indice = 0; indice < mensajes.length; indice++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) mensajes[indice]);

                String numeroTelefono = smsMessage.getOriginatingAddress();
                String mensaje = smsMessage.getMessageBody().trim();

                Competitor competitorA = validate(mensaje);
                if (competitorA != null) {
                    int votes = competitorA.getVotes();
                    votes += 1;
                    control.increaseVotes(competitorA.getId(), votes);
                    writeMessage(numeroTelefono, "Gracias por tu parcticipación");
                } else {
                    Toast.makeText(context, "No existe :(", Toast.LENGTH_LONG).show();
                    //writeMessage(numeroTelefono, "Participante no existente");

                }

              /*  if (mensaje.matches("#[\\w]{10,15}")) {

                } else
                    Toast.makeText(context, "No existe candidato con ese alias", Toast.LENGTH_SHORT).show();*/
            }
        }
    }

    public void savePhone(String phone, String body, Context c) {
        TelephoneNumber number = new TelephoneNumber(phone);

        long id = control.newTelephoneNumber(number);
        if (id == -1) {
            Toast.makeText(c, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(c, "guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();

        }
    }

    public Competitor validate(String nickname) {
        listC = control.getListCompetitors();
        for (int ccount = 0; ccount < listC.size(); ccount++) {
            if (nickname.equalsIgnoreCase(listC.get(ccount).getNickname())) {
                return listC.get(ccount);
            }
        }

        return null;
    }


    private void writeMessage(String phone, String message) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(c, "Send SMS", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //Toast.makeText(c, "No es un nombre válido", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}