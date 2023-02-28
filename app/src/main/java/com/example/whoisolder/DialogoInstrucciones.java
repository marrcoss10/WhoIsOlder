package com.example.whoisolder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoInstrucciones extends DialogFragment {
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("El juego consisten en acertar qué personaje famoso es más mayor. Cada acierto suma un punto. Al fallar se finaliza la partida.");
        dialog.setCancelable(false);
        dialog.setTitle("Instrucciones");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        return dialog.create();
    }
}
