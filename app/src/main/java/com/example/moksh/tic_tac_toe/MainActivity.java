package com.example.moksh.tic_tac_toe;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int i = 0;
    int j = 0;
    int k=0;


    boolean isGameActive=true;


    public void put (View v){
        ImageView counter = (ImageView) v;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && isGameActive){
            gameState[tappedCounter] = i;
            counter.setTranslationY(-2000f);

            if(i==0) {
                animateY(v);
                i=1;
                System.out.println("yellow " + j++);
            }
            else if (i==1){
                animateR(v);
                i=0;
                System.out.println("Red " + k++);
            }

        }
        for (int[] winningPosition : winningPositions){

            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]]!=2){
                LinearLayout winner =  findViewById(R.id.playAgainLayout);
                winner.setVisibility(View.VISIBLE);
                isGameActive = false;
                if (gameState[winningPosition[0]]==0){
                //Toast.makeText(this, "Yellow has won!", Toast.LENGTH_SHORT).show();
                    TextView winnerT = findViewById(R.id.winnerText);
                winnerT.setText("Yellow has won!");
                winner.setBackgroundColor(Color.parseColor("#FFEB3B") );}
                else if (gameState[winningPosition[0]]==1){
                    TextView winnerT = findViewById(R.id.winnerText);
                    winner.setBackgroundColor(Color.parseColor("#F44336") );
                winnerT.setText("Red has won!");}

            }
        }

    }

    public void animateY (View v){
        ImageView counter = (ImageView) v;
        counter.setImageResource(R.drawable.yellow);
        counter.animate()
                .translationYBy(2000f)
                .rotation(2300)
                .setDuration(500);
    }

    public void animateR (View v){
        ImageView counter = (ImageView) v;
        counter.setImageResource(R.drawable.red);
        counter.animate()
                .translationYBy(2000f)
                .rotation(2300)
                .setDuration(500);
    }


    public void playAgain (View view) {

        isGameActive=true;
        LinearLayout winner =  findViewById(R.id.playAgainLayout);
        winner.setVisibility(View.INVISIBLE);

        i=0;
        for(int l=0; l<9; l++)
            gameState[l]=2;

        GridLayout gridLayout = (findViewById(R.id.gridLayout));
        for(int a=0; a<gridLayout.getChildCount(); a++)
            ((ImageView) gridLayout.getChildAt(a)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout winner =  findViewById(R.id.playAgainLayout);
        winner.setVisibility(View.INVISIBLE);
    }
}
