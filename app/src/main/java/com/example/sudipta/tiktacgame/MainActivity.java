package com.example.sudipta.tiktacgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    //0: yello, 1: red, 2:empty
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer =0;
    boolean gameactive=true;

    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tapcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tapcounter] == 2 && gameactive) {
            gamestate[tapcounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningposition : winningposition) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] &&
                        gamestate[winningposition[1]] == gamestate[winningposition[2]] &&
                        gamestate[winningposition[0]] != 2) {
                    //some one has won!

                    gameactive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + " has Won!", Toast.LENGTH_SHORT).show();
                    Button playAgainButton =(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView =(TextView)findViewById(R.id.winnertextView);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);
                }
            }
        }
    }
    public  void playAgain(View view){
        Button playAgainButton =(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView =(TextView)findViewById(R.id.winnertextView);
        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);

        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gamestate.length; i++) {
            gamestate[i] = 2;
        }
        activePlayer = 0;
        gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}