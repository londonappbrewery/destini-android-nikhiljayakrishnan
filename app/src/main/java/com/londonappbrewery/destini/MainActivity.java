package com.londonappbrewery.destini;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView textDisplay;
    private Button topButton,bottomButton,exitButton;
    int storyId;
    private int mIndex;
    private StoryBoard[] mStoryBoards={

            new StoryBoard(R.string.T1_Story,true),
            new StoryBoard(R.string.T2_Story,true),
            new StoryBoard(R.string.T3_Story,true),
            new StoryBoard(R.string.T4_End,true),
            new StoryBoard(R.string.T5_End,true),
            new StoryBoard(R.string.T6_End,true),
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
            textDisplay=(TextView)findViewById(R.id.storyTextView);
            topButton=(Button)findViewById(R.id.buttonTop);
            bottomButton=(Button)findViewById(R.id.buttonBottom);
            exitButton=(Button)findViewById(R.id.exitButton);



        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
            topButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"press it",Toast.LENGTH_SHORT).show();

                    answerCheck(true);
                    update(mIndex);
                }
            });
        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
            bottomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"press bottom",Toast.LENGTH_SHORT).show();

                    answerCheck(false);
                    update(mIndex);
                }
            });
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mIndex>2){check();}else{
                        Toast.makeText(getApplicationContext(),"you cant leave now without a choice",Toast.LENGTH_SHORT).show();;
                    }
                }
            });







    }
    public void update(int index){
        storyId=mStoryBoards[index].getStoryId();
        textDisplay.setText(storyId);
        if(mIndex<3){
            //exitButton.setVisibility(View.GONE);
        }

    }
    public void check(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Game Over");
        alert.setCancelable(false);
        alert.setMessage("Movie ends here");
        alert.setPositiveButton("Exit application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            finish();
            }
        });
        alert.show();
    }
    public void answerCheck(boolean userChoice) {
        //boolean choice = mStoryBoards[mIndex].isAnswer();

        if (userChoice && mIndex == 0) {


            //textDisplay.setText(storyId);
            topButton.setText(R.string.T3_Ans1);
            bottomButton.setText(R.string.T3_Ans2);
            //Toast.makeText(getApplicationContext(),"mIndex"+storyId,Toast.LENGTH_SHORT).show();
            mIndex = 2;
        } else if (userChoice && mIndex == 2) {
            mIndex = 5;
            topButton.setVisibility(View.GONE);
            bottomButton.setVisibility(View.GONE);
            //Toast.makeText(getApplicationContext(),"mIndex"+mIndex,Toast.LENGTH_SHORT).show();
        } else if (!userChoice && mIndex == 0) {
            mIndex = 1;
            //textDisplay.setText(R.string.T2_Story);
            topButton.setText(R.string.T2_Ans1);
            bottomButton.setText(R.string.T2_Ans2);
            //Toast.makeText(getApplicationContext(), "mIndex" + mIndex, Toast.LENGTH_SHORT).show();
        } else if (userChoice && mIndex == 1) {
            //textDisplay.setText(R.string.T6_End);
            mIndex = 5;
            topButton.setVisibility(View.GONE);
            bottomButton.setVisibility(View.GONE);
            //Toast.makeText(getApplicationContext(), "mIndex" + mIndex, Toast.LENGTH_SHORT).show();
        } else if (!userChoice && mIndex == 1) {
            mIndex = 3;
            topButton.setVisibility(View.GONE);
            bottomButton.setVisibility(View.GONE);
        } else if (!userChoice && mIndex == 2) {
            mIndex = 4;
            topButton.setVisibility(View.GONE);
            bottomButton.setVisibility(View.GONE);

        }

    }
    public static class StoryBoard {


        private boolean mAnswer;
        private int storyId;

        public  StoryBoard(int storyResId,boolean TrueOrFasle){
            mAnswer=TrueOrFasle;
            storyId=storyResId;
        }
        public boolean isAnswer() {
            return mAnswer;
        }

        public void setAnswer(boolean answer) {
            mAnswer = answer;
        }

        public int getStoryId() {
            return storyId;
        }

        public void setStoryId(int storyId) {
            this.storyId = storyId;
        }
    }
}
