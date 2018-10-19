package com.example.allan.unidade2;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

public class CreateItemDialog extends DialogFragment {


    private String name;
    private EditText edtText;
    private OnTextListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Itens");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(listener!=null){
                    String txt = edtText.getText().toString();

                }
            }
        });

        View view = getActivity().getLayoutInflater().inflate(R.layout.edit_dialog, null);
        edtText = view.findViewById(R.id.edtText);
        edtText.setText(name);
        builder.setView(view);
        return builder.create();
    }

    public static void show(FragmentManager fragmentManager, OnTextListener listener){
        CreateItemDialog dialog = new CreateItemDialog();
        dialog.listener = listener;
        dialog.show(fragmentManager, "Teste");
    }


    public interface OnTextListener{
        public void onSetText(String name);
    }

}
