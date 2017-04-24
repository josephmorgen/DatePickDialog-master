package com.example.administrator.datepickdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class main extends AppCompatActivity {
    TextView showdate;
    Button setdate;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showdate = (TextView) findViewById(R.id.showdate);
        setdate = (Button) findViewById(R.id.setdate);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        Date date = new Date();
        c.setTime(date);
        showdate.setText("当前日期:" + year + "年" + (month + 1) + "月" + day + "日");
        setdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(main.this, DateListener, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    private DatePickerDialog.OnDateSetListener DateListener=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker v, int y, int m, int d) {
            Calendar c = Calendar.getInstance(Locale.CHINA);
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String AlterTime = y + "-" + m + "-" + d;
            String NowTime = year + "-" + month + "-" + day;
            Date AlterDate = null;
            Date NowDate = null;
            try {
                AlterDate = date.parse(AlterTime);
                NowDate = date.parse(NowTime);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            if (AlterDate.compareTo(NowDate) ==-1) {
                year = y;
                month = m;
                day = d;
                updateDate();
            }else{
                showdate.setText("所修改的时间比当前时间大，续命失败，请珍惜你的时间-1s，请重新续");
            }
        }


        private void updateDate() {
            showdate.setText("当前日期:" + year + "年" + (month + 1) + "月" + day + "日");
        }
    };
}



