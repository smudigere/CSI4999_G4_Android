package com.gptm.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gptm.app.controller.HoleCount;
import com.gptm.app.controller.ScoreTrackMap;
import com.gptm.app.model.Hole;
import com.gptm.app.utility.Functions;

import static android.view.Gravity.CENTER;

public class GridScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_score);

        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new ScoreAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GridScoreActivity.this, "" + position,Toast.LENGTH_SHORT).show();
            }
        });

        String teeName;
        switch (Functions.mCourseInfo.getmSelectedTee()) {
            case 0:
            default:
                teeName = "Red";
                break;
            case 1:
                teeName = "Gold";
                break;
            case 2:
                teeName = "Black";
                break;
            case 3:
                teeName = "White";
                break;
        }

        Glide.with(this).load("https://hdwallpaperim.com/wp-content/uploads/2017/08/24/107938-golf-green-field.jpg").into((ImageView) findViewById(R.id.image_view));


        TextView course_info_text_view = findViewById(R.id.course_info_text_view);
        course_info_text_view.setText(Functions.mCourseInfo.getmName() +
                "\nTee: " + teeName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.end_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())   {
            case R.id.item1:        //Score Card

                //Intent mIntent = new Intent(this, ScoreActivity.class);
                Intent mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ScoreAdapter extends BaseAdapter  {
        private Context mContext;

        public ScoreAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 144;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                textView = new TextView(mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(2, 2, 2, 2);
            } else
                textView = (TextView) convertView;

            try {
                printText(textView, position);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            textView.setGravity(CENTER);
            textView.setTextSize(14);
            textView.setTextColor(Color.DKGRAY);

            return textView;
        }


        private void printText(TextView textView, int position) throws Exception    {

            if (position <= 11)
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            if (position >=72 && position <= 83)
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            Hole[] holes = Functions.mCourseInfo.getmHoles();
            int counter = 0;
            switch (position)   {

                case 0:
                    textView.setText("Hole->");
                    break;
                case 1:
                    textView.setText(String.valueOf(position));
                    break;
                case 2:
                    textView.setText(String.valueOf(position));
                    break;
                case 3:
                    textView.setText(String.valueOf(position));
                    break;
                case 4:
                    textView.setText(String.valueOf(position));
                    break;
                case 5:
                    textView.setText(String.valueOf(position));
                    break;
                case 6:
                    textView.setText(String.valueOf(position));
                    break;
                case 7:
                    textView.setText(String.valueOf(position));
                    break;
                case 8:
                    textView.setText(String.valueOf(position));
                    break;
                case 9:
                    textView.setText(String.valueOf(position));
                    break;
                case 10:
                    textView.setText("Out");
                    break;
                case 11:
                    textView.setText("Total");
                    break;
                case 12:
                    textView.setText("Par->");
                    break;
                case 22:
                    counter = 0;
                    for (int i = 0; i < 9; i++)
                        counter += holes[i].getmPar();
                    textView.setText(String.valueOf(counter));
                    break;
                case 23:
                    counter = 0;
                    if (HoleCount.getInstance().getHoleCount() < 10)    {
                        for (int i = 0; i < 9; i++)
                            counter += holes[i].getmPar();
                    } else {
                        for (int i = 0; i < 18; i++)
                            counter += holes[i].getmPar();
                    }
                    textView.setText(String.valueOf(counter));
                    break;
                case 24:
                    textView.setText(Functions.getPlayers(mContext)[0].getPlayerName());
                    break;
                case 36:
                    textView.setText(Functions.getPlayers(mContext)[1].getPlayerName());
                    break;
                case 48:
                    textView.setText(Functions.getPlayers(mContext)[2].getPlayerName());
                    break;
                case 60:
                    textView.setText(Functions.getPlayers(mContext)[3].getPlayerName());
                    break;
            }

            if (position > 12 && position < 22)
                textView.setText(String.valueOf(holes[position - 12].getmPar()));

            if (position >= 25 && position <= 33)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 26) + 1).get(0).toString()
                        )
                );


            if (position >= 37 && position <= 45)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 38) + 1).get(1).toString()
                        )
                );

            if (position >= 49 && position <= 57)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 50) + 1).get(2).toString()
                        )
                );

            if (position >= 61 && position <= 69)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 62) + 1).get(3).toString()
                        )
                );

            if (position >= 73 && position <= 81)
                textView.setText(String.valueOf(position - 63));

            switch (position)   {
                case 72:
                    textView.setText("Hole->");
                    break;
                case 82:
                    textView.setText("Out");
                    break;
                case 83:
                    textView.setText("Total");
                    break;
                case 84:
                    textView.setText("Par->");
                    break;
                case 94:
                    counter = 0;
                    for (int i = 9; i < 18; i++)
                        counter += holes[i].getmPar();
                    textView.setText(String.valueOf(counter));
                    break;
                case 95:
                    counter = 0;
                    for (int i = 0; i < 18; i++)
                            counter += holes[i].getmPar();
                    textView.setText(String.valueOf(counter));
                    break;
                case 96:
                    textView.setText(Functions.getPlayers(mContext)[0].getPlayerName());
                    break;
                case 108:
                    textView.setText(Functions.getPlayers(mContext)[1].getPlayerName());
                    break;
                case 120:
                    textView.setText(Functions.getPlayers(mContext)[2].getPlayerName());
                    break;
                case 132:
                    textView.setText(Functions.getPlayers(mContext)[3].getPlayerName());
                    break;
            }

            if (position >= 85 && position <= 93)
                textView.setText(String.valueOf(holes[position - 85].getmPar()));

            if (position >= 97 && position <= 105)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 89) + 1).get(0).toString()
                        )
                );


            if (position >= 109 && position <= 117)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 101) + 1).get(1).toString()
                        )
                );

            if (position >= 121 && position <= 129)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 113) + 1).get(2).toString()
                        )
                );

            if (position >= 133 && position <= 141)
                textView.setText(
                        String.valueOf(
                                ScoreTrackMap.getInstance().getmScoreMapList().get((position - 125) + 1).get(3).toString()
                        )
                );
        }
    }
}
