package com.niksaen.progersim.myClass;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.niksaen.progersim.R;

public class CustomDialog{
    Context context;

    View main;
    TextView titleView,messageView;
    Button buttonOk,buttonCancel;
    public CustomDialog(Context context) {
        this.context = context;
        main = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout,null);
        titleView = main.findViewById(R.id.title);
        messageView = main.findViewById(R.id.message);
        buttonCancel = main.findViewById(R.id.buttonCancel);
        buttonOk = main.findViewById(R.id.buttonOk);
    }

    String buttonOkText;
    View.OnClickListener buttonOkOnClickListener;
    String buttonCancelText;
    View.OnClickListener buttonCancelOnClickListener;
    Boolean buttonOkVisible = false;
    Boolean buttonCancelVisible = false;

    public void setButtonOkVisible(Boolean buttonOkVisible) { this.buttonOkVisible = buttonOkVisible; }
    public void setButtonOkOnClickListener(View.OnClickListener buttonOkOnClickListener) { this.buttonOkOnClickListener = buttonOkOnClickListener; }
    public void setButtonOkText(String buttonOkText) { this.buttonOkText = buttonOkText; }

    public void setButtonCancelVisible(Boolean buttonCancelVisible) { this.buttonCancelVisible = buttonCancelVisible; }
    public void setButtonCancelOnClickListener(View.OnClickListener buttonCancelOnClickListener) { this.buttonCancelOnClickListener = buttonCancelOnClickListener; }
    public void setButtonCancelText(String buttonCancelText) { this.buttonCancelText = buttonCancelText; }

    String title;
    String message;
    public void setTitle(CharSequence title) { this.title = title.toString();}
    public void setMessage(CharSequence message) { this.message = message.toString(); }

    boolean cancelable = true;
    public void setCancelable(boolean cancelable) { this.cancelable = cancelable; }

    public void create() {
        titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"), Typeface.BOLD);
        messageView.setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"));
        buttonCancel.setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"));
        buttonOk.setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"));

        titleView.setText(title);
        messageView.setText(message);
        buttonOk.setText(buttonOkText);
        buttonCancel.setText(buttonCancelText);

        if(buttonOkVisible){ buttonOk.setOnClickListener(buttonOkOnClickListener); }
        else { buttonOk.setVisibility(View.GONE); }

        if(buttonCancelVisible){ buttonCancel.setOnClickListener(buttonCancelOnClickListener); }
        else { buttonCancel.setVisibility(View.GONE); }
    }

    AlertDialog dialog;
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(main);
        dialog = builder.create();
        dialog.show();
    }
    public void dismiss(){
        dialog.dismiss();
    }
}
