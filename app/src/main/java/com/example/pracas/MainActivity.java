package com.example.pracas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button Rock;
    Button Paper;
    Button Scissors;
    ImageView imageView;
    ImageView imageView2;
    TextView scores;
    TextView UserChoice;
    TextView ComputerChoice;
    int Systemscore, Humanscore = 0;

    DatabaseReference db;
    List<TotalScore> scrs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rock = (Button) findViewById(R.id.rock);
        Paper = (Button) findViewById(R.id.paper);
        Scissors = (Button) findViewById(R.id.scissors);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        scores = (TextView) findViewById(R.id.scores);
        UserChoice = (TextView) findViewById(R.id.userchoice);
        ComputerChoice = (TextView) findViewById(R.id.computerchoice);

        db = FirebaseDatabase.getInstance().getReference("Scores");
        scrs = new ArrayList<>();


        Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.paper);
                String message = play_turn("Paper");
                String msg = "Score: Human" + (Humanscore) + "Computer" + (Systemscore);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                scores.setText("Score: Human" + (Humanscore) + "Computer" + (Systemscore));
                String id = db.push().getKey();
                TotalScore ts = new TotalScore(id, msg);
                db.child(id).setValue(ts);
            }
        });
        Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.rock);
                String message = play_turn("Rock");
                String msg = "Score: Human" + (Humanscore) + "Computer" + (Systemscore);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                scores.setText("Score: Human" + (Humanscore) + "Computer" + (Systemscore));
                String id = db.push().getKey();
                TotalScore ts = new TotalScore(id, msg);
                db.child(id).setValue(ts);
            }


        });
        Scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.scissors);
                String message = play_turn("Scissors");
                String msg = "Score: Human" + (Humanscore) + "Computer" + (Systemscore);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                scores.setText("Score: Human" + (Humanscore) + "Computer" + (Systemscore));
                String id = db.push().getKey();
                TotalScore ts = new TotalScore(id, msg);
                db.child(id).setValue(ts);
            }

        });


    }

    public String play_turn(String player_choice) {
        String computer_choice = "";
        Random r = new Random();
        int computer_choice_number = r.nextInt(3) + 1;
        if (computer_choice_number == 1) {
            computer_choice = "Rock";
        } else if (computer_choice_number == 2) {
            computer_choice = "Paper";
        } else if (computer_choice_number == 3) {
            computer_choice = "Scissors";
        }


        if (computer_choice == "Rock") {
            imageView2.setImageResource(R.drawable.rock);
        } else if (computer_choice == "Paper") {
            imageView2.setImageResource(R.drawable.paper);
        } else if (computer_choice == "Scissors") {
            imageView2.setImageResource(R.drawable.scissors);
        }


        if (computer_choice == player_choice) {
            return "Draw";
        } else if (computer_choice == "Rock" && player_choice == "Scissors") {
            Systemscore++;
            return "Rock crushes scissors,System wins";
        } else if (computer_choice == "Scissors" && player_choice == "Paper") {
            Systemscore++;
            return "scissors cut paper,System wins";
        } else if (computer_choice == "Paper" && player_choice == "Rock") {
            Systemscore++;
            return "Paper covers rock,System wins";
        } else if (player_choice == "Paper" && computer_choice == "Rock") {
            Humanscore++;
            return "Paper covers rock,You win";
        } else if (player_choice == "Scissors" && computer_choice == "Paper") {
            Humanscore++;
            return "scissors cuts paper ,You win";
        } else if (player_choice == "Rock" && computer_choice == "Scissors") {
            Humanscore++;
            return "Rock crushes scissors,You win";
        } else
            return "Not clear";


    }


}