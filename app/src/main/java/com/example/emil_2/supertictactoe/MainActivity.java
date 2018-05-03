package com.example.emil_2.supertictactoe;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {


    final boolean debug = false;
    Button[][] buttons = new Button[9][9];

    void getButtons()
    {
        buttons[0][0] = findViewById(R.id.y0x0);
        buttons[0][1] = findViewById(R.id.y0x1);
        buttons[0][2] = findViewById(R.id.y0x2);
        buttons[0][3] = findViewById(R.id.y0x3);
        buttons[0][4] = findViewById(R.id.y0x4);
        buttons[0][5] = findViewById(R.id.y0x5);
        buttons[0][6] = findViewById(R.id.y0x6);
        buttons[0][7] = findViewById(R.id.y0x7);
        buttons[0][8] = findViewById(R.id.y0x8);
        buttons[1][0] = findViewById(R.id.y1x0);
        buttons[1][1] = findViewById(R.id.y1x1);
        buttons[1][2] = findViewById(R.id.y1x2);
        buttons[1][3] = findViewById(R.id.y1x3);
        buttons[1][4] = findViewById(R.id.y1x4);
        buttons[1][5] = findViewById(R.id.y1x5);
        buttons[1][6] = findViewById(R.id.y1x6);
        buttons[1][7] = findViewById(R.id.y1x7);
        buttons[1][8] = findViewById(R.id.y1x8);
        buttons[2][0] = findViewById(R.id.y2x0);
        buttons[2][1] = findViewById(R.id.y2x1);
        buttons[2][2] = findViewById(R.id.y2x2);
        buttons[2][3] = findViewById(R.id.y2x3);
        buttons[2][4] = findViewById(R.id.y2x4);
        buttons[2][5] = findViewById(R.id.y2x5);
        buttons[2][6] = findViewById(R.id.y2x6);
        buttons[2][7] = findViewById(R.id.y2x7);
        buttons[2][8] = findViewById(R.id.y2x8);
        buttons[3][0] = findViewById(R.id.y3x0);
        buttons[3][1] = findViewById(R.id.y3x1);
        buttons[3][2] = findViewById(R.id.y3x2);
        buttons[3][3] = findViewById(R.id.y3x3);
        buttons[3][4] = findViewById(R.id.y3x4);
        buttons[3][5] = findViewById(R.id.y3x5);
        buttons[3][6] = findViewById(R.id.y3x6);
        buttons[3][7] = findViewById(R.id.y3x7);
        buttons[3][8] = findViewById(R.id.y3x8);
        buttons[4][0] = findViewById(R.id.y4x0);
        buttons[4][1] = findViewById(R.id.y4x1);
        buttons[4][2] = findViewById(R.id.y4x2);
        buttons[4][3] = findViewById(R.id.y4x3);
        buttons[4][4] = findViewById(R.id.y4x4);
        buttons[4][5] = findViewById(R.id.y4x5);
        buttons[4][6] = findViewById(R.id.y4x6);
        buttons[4][7] = findViewById(R.id.y4x7);
        buttons[4][8] = findViewById(R.id.y4x8);
        buttons[5][0] = findViewById(R.id.y5x0);
        buttons[5][1] = findViewById(R.id.y5x1);
        buttons[5][2] = findViewById(R.id.y5x2);
        buttons[5][3] = findViewById(R.id.y5x3);
        buttons[5][4] = findViewById(R.id.y5x4);
        buttons[5][5] = findViewById(R.id.y5x5);
        buttons[5][6] = findViewById(R.id.y5x6);
        buttons[5][7] = findViewById(R.id.y5x7);
        buttons[5][8] = findViewById(R.id.y5x8);
        buttons[6][0] = findViewById(R.id.y6x0);
        buttons[6][1] = findViewById(R.id.y6x1);
        buttons[6][2] = findViewById(R.id.y6x2);
        buttons[6][3] = findViewById(R.id.y6x3);
        buttons[6][4] = findViewById(R.id.y6x4);
        buttons[6][5] = findViewById(R.id.y6x5);
        buttons[6][6] = findViewById(R.id.y6x6);
        buttons[6][7] = findViewById(R.id.y6x7);
        buttons[6][8] = findViewById(R.id.y6x8);
        buttons[7][0] = findViewById(R.id.y7x0);
        buttons[7][1] = findViewById(R.id.y7x1);
        buttons[7][2] = findViewById(R.id.y7x2);
        buttons[7][3] = findViewById(R.id.y7x3);
        buttons[7][4] = findViewById(R.id.y7x4);
        buttons[7][5] = findViewById(R.id.y7x5);
        buttons[7][6] = findViewById(R.id.y7x6);
        buttons[7][7] = findViewById(R.id.y7x7);
        buttons[7][8] = findViewById(R.id.y7x8);
        buttons[8][0] = findViewById(R.id.y8x0);
        buttons[8][1] = findViewById(R.id.y8x1);
        buttons[8][2] = findViewById(R.id.y8x2);
        buttons[8][3] = findViewById(R.id.y8x3);
        buttons[8][4] = findViewById(R.id.y8x4);
        buttons[8][5] = findViewById(R.id.y8x5);
        buttons[8][6] = findViewById(R.id.y8x6);
        buttons[8][7] = findViewById(R.id.y8x7);
        buttons[8][8] = findViewById(R.id.y8x8);
    }


    ArrayDeque<State> prevStates = new ArrayDeque<>();
    int maxQueueSize = Integer.MAX_VALUE;
    State curState;


    byte[] over = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};


    boolean[][] isclickable = new boolean[9][9];

    ConstraintLayout cl;

    byte wonByPlayer = 0;
    byte playerTurn = 1;
    Button backButton;


    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButtons();
        backButton = findViewById(R.id.backButton);
        tv = findViewById(R.id.playerDisplay);
        tv.setText(playerTurn == 1 ? R.string.player0 : R.string.player1);
        tv.setTextColor(getResources().getColor(
                playerTurn == 1 ? R.color.Player0 : R.color.Player1));
        byte[][] state = new byte[9][9];
        for(int y = 0; y < 9; y++)
            for(int x = 0; x < 9; x++)
            {
                isclickable[y][x] = true;
                state[y][x] = 0;
                final int yI = y;
                final int xI = x;
                buttons[y][x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonclicked(yI, xI, false);
                }});
            }

        curState = new State(state, (byte)-1);

        for(int i = 0; i < 9; i++)
            setBoardColor(i , R.color.Highlight);
        cl = findViewById(R.id.backgrond);
        cl.setBackgroundResource(R.color.DarkBackground);
    }

    public void backButton(View v)
    {
        if(!prevStates.isEmpty()) {
            curState = prevStates.removeLast();
            playerTurn *= -1;
        }

        render();
    }

    void render()
    {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                switch (curState.state[y][x]) {
                case 1:
                    buttons[y][x].setText(R.string.player0);
                    break;
                case -1:
                    buttons[y][x].setText(R.string.player1);
                    break;
                case 0:
                    buttons[y][x].setText(R.string.empty);
                    break;
                }
            }
        }

        //highlight
        //reset highlighting
        for(int i = 0; i < 9; i++) {
            setBoardColor(i, R.color.Transparent);
            setBoardClickable(i, false);
        }


        Arrays.fill(over, (byte)0);
        //highlight already won board
        for (int i = 0; i < 9; i++) {
            checkIfOver(i);
        }


        //check if board at next pos is finished
        if(curState.nextMove != -1 && over[curState.nextMove] == 0)
        {
            setBoardColor(curState.nextMove, R.color.Highlight);
            setBoardClickable(curState.nextMove, true);
            stateCheck(curState.nextMove);

        }
        else
        {
            //highlight every thing
            for(int i = 0; i < 9; i++)
            {
                setBoardColor(i, R.color.Highlight);
                setBoardClickable(i, true);
                stateCheck(i);
            }
        }

        for(int i = 0; i < 9; i++)
        {
            if (over[i] == 1)
            {
                setBoardColor(i, R.color.Player0);
                setBoardClickable(i, false);
            }
            if (over[i] == -1)
            {
                setBoardColor(i, R.color.Player1);
                setBoardClickable(i, false);
            }
            if (over[i] == 2)
            {
                setBoardColor(i, R.color.Draw);
                setBoardClickable(i, false);
            }
        }

        tv.setText(playerTurn == 1 ? R.string.player0 : R.string.player1);
        tv.setTextColor(getResources().getColor(
                playerTurn == 1 ? R.color.Player0 : R.color.Player1));


    }

    void buttonclicked(int y, int x, boolean runByAi)
    {

        if(!isclickable[y][x])
            return;

        prevStates.add(new State(curState));
        if(prevStates.size() > maxQueueSize)
            prevStates.removeFirst();


        curState.state[y][x] = playerTurn;



        curState.nextMove = (byte)(3 * (y % 3) + (x % 3));
        playerTurn *= -1;
        render();

        if(debug)
            for(int i = 0; i < 9; i++)
            {
                setBoardClickable(i, true);
                stateCheck(i);
            }



       /* String s = "";
        for(y = 0; y < 9; y++) {
            for (x = 0; x < 9; x++)
                s += isclickable[y][x] ? "O" : "_";
            s += "\n";
        }
        System.out.println(s);*/

       // game end
        checkEnd();
        if(wonByPlayer != 0)
        {
            for(int i = 0; i < 9; i++)
            {
                if(over[i] == 0)
                    setBoardColor(i, R.color.DarkBackground);
                setBoardClickable(i, false);
            }

            if(wonByPlayer == 1)
                cl.setBackgroundResource(R.color.Player0);
            if(wonByPlayer == -1)
                cl.setBackgroundResource(R.color.Player1);
            if(wonByPlayer == 2)
                cl.setBackgroundResource(R.color.Draw);


        }


    }

    void setBoardColor(int i, int color)
    {
        for(int y = 3 * (i / 3); y < 3 * (i / 3) + 3; y++)
            for(int x = 3 * (i % 3); x < 3 * (i % 3) + 3; x++)
            {
                buttons[y][x].setBackgroundColor(getResources().getColor(color));

            }
    }

    void setBoardClickable(int i, boolean clickable)
    {

        for(int y = 3 * (i / 3); y < 3 * (i / 3) + 3; y++)
            for(int x = 3 * (i % 3); x < 3 * (i % 3) + 3; x++)
            {
                isclickable[y][x] = clickable;

            }
    }

    void checkIfOver(int i)
    {
        byte[][] Istate = new byte[3][3];
        int index = 0;

        for(int y = 3 * (i / 3); y < 3 * (i / 3) + 3; y++)
            for(int x = 3 * (i % 3); x < 3 * (i % 3) + 3; x++)
            {
                Istate[index /3][index % 3] = curState.state[y][x];
                index++;
            }



        for(byte player = 1; player > -3; player -= 2)
        {
            // ver
            for(int x = 0; x < 3; x++)
            {
                boolean is = true;
                for (int y = 0; y < 3; y++)
                {
                    if(Istate[y][x] != player)
                        is = false;
                }
                if(is)
                {
                    over[i] = player;
                    return;
                }
            }

            // hor
            for(int y = 0; y < 3; y++)
            {
                boolean is = true;
                for (int x = 0; x < 3; x++)
                {
                    if(Istate[y][x] != player)
                        is = false;
                }
                if(is)
                {
                    over[i] = player;
                    return;
                }
            }

            //dia
            boolean is = true;
            for (int x = 0; x < 3; x++)
            {
                if(Istate[x][x] != player)
                    is = false;
            }
            if(is)
            {
                over[i] = player;
                return;
            }
            is = true;
            for (int x = 0; x < 3; x++)
            {
                if(Istate[2 - x][x] != player)
                    is = false;
            }
            if(is)
            {
                over[i] = player;
                return;
            }
        }
        //draw

        boolean full = true;
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
            {
                if(Istate[y][x] == 0)
                    full = false;
            }
        if(full)
        {
            over[i] = 2;
        }
    }
    public void reset_(View v)
    {
        reset();
    }
    void reset()
    {
        //check if state is only zero if so don't reset
        boolean notZero = false;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (curState.state[y][x] != 0)
                {
                    notZero = true;
                    break;
                }
            }
            if(notZero)
                break;
        }
        if(!notZero)
            return;



        prevStates.add(new State(curState));
        if(prevStates.size() > maxQueueSize)
            prevStates.removeFirst();
        curState.nextMove = -1;
        playerTurn = 1;
        Arrays.fill(over, (byte)0);
        tv.setText(playerTurn == 1 ? R.string.player0 : R.string.player1);
        tv.setTextColor(getResources().getColor(
                playerTurn == 1 ? R.color.Player0 : R.color.Player1));
        for(byte[] i : curState.state)
            Arrays.fill(i, (byte)0);
        for(boolean[] i : isclickable)
            Arrays.fill(i, true);

        for(int i = 0; i < 9; i++)
            setBoardColor(i , R.color.Highlight);
        cl.setBackgroundResource(R.color.DarkBackground);

        for(int y = 0; y < 9; y++)
            for(int x = 0; x < 9; x++)
                buttons[y][x].setText(R.string.empty);

        wonByPlayer = 0;
    }

    void stateCheck(int i)
    {
        for(int y = 3 * (i / 3); y < 3 * (i / 3) + 3; y++)
            for(int x = 3 * (i % 3); x < 3 * (i % 3) + 3; x++)
            {
                if(curState.state[y][x] != 0)
                {
                    isclickable[y][x] = false;

                    buttons[y][x].setBackgroundColor(getResources().getColor(R.color.Transparent));
                }
            }
    }

    void checkEnd()
    {
        byte[][] Istate = new byte[3][3];
        int index = 0;

        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
            {
                Istate[y][x] = over[index++];
            }



        for(byte player = 1; player > -3; player -= 2)
        {
            // ver
            for(int x = 0; x < 3; x++)
            {
                boolean is = true;
                for (int y = 0; y < 3; y++)
                {
                    if(Istate[y][x] != player)
                        is = false;
                }
                if(is)
                {
                    wonByPlayer = player;
                    return;
                }
            }

            // hor
            for(int y = 0; y < 3; y++)
            {
                boolean is = true;
                for (int x = 0; x < 3; x++)
                {
                    if(Istate[y][x] != player)
                        is = false;
                }
                if(is)
                {
                    wonByPlayer = player;
                    return;
                }
            }

            //dia
            boolean is = true;
            for (int x = 0; x < 3; x++)
            {
                if(Istate[x][x] != player)
                    is = false;
            }
            if(is)
            {
                wonByPlayer = player;
                return;
            }
            is = true;
            for (int x = 0; x < 3; x++)
            {
                if(Istate[2 - x][x] != player)
                    is = false;
            }
            if(is)
            {
                wonByPlayer = player;
                return;
            }
        }
        //draw

        boolean full = true;
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
            {
                if(Istate[y][x] == 0)
                    full = false;
            }
        if(full)
        {
            wonByPlayer = 2;
        }
    }
}
