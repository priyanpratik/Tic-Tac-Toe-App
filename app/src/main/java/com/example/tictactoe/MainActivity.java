package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    // Player Representations
    // 0 - X
    // 1 - O
    int activePlayer=0;

    int[] gameState={2,2,2,2,2,2,2,2,2};
    // State meanings
    //    0 - X
    //    1 - O
    //    2 - NULL

    // 2D array to represent total 8 winning positions
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}};


    public void playerTap(View view){  // view is passed as an argument
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());  // it will receive the tag mentioned in XML file for each image


        if(gameState[tappedImage]==2){
            gameState[tappedImage]=activePlayer;
            if(activePlayer==0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status=findViewById(R.id.status);
                status.setText("O's turn");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's turn");
            }
        }

        // check winning conditions
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2 ){

                // winning position found
                String winnerStr;
                if(gameState[winPosition[0]]==0){
                    winnerStr="X Won!!! Now X's Turn";
                }
                else{
                    winnerStr="O Won!!! Now X's Turn";
                }

                // Updating TextView for winner
                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);
                gameReset(view);
            }
        }

        int flag=0;
        for(int i=0;i<9;i++){
            if(gameState[i]==2) {
                flag = 1;
                break;
            }
        }

        if(flag==0){
            TextView status=findViewById(R.id.status);
            status.setText("Match Tied!!! X's turn");
            gameReset(view);
        }

    }




    public void gameReset(View view){
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}