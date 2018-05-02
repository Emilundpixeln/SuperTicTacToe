package com.example.emil_2.supertictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emil_2 on 29.04.2018.
 */

// game state (for ai)
class State
{
    State(State s, Pos p, byte l, byte _nextMove)
    {
        // creates state from prevstate where it overwrites Pos p with l
        for (int i = 0; i < 9; i++)
            System.arraycopy(s.state[i], 0, state[i], 0, 9);

        state[p.y][p.x] = l;
        nextMove = _nextMove;
    }
    State(byte[][] s, byte _nextMove)
    {
        for (int i = 0; i < 9; i++)
            System.arraycopy(s[i], 0, state[i], 0, 9);
        nextMove = _nextMove;
    }

    byte[][] state = new byte[9][9];
    Map<Pos, State> moves;

    byte[] over;
    byte[] wins = new byte[2];
    byte draws = 0;
    byte nextMove = -1; // index of grid that has to be played. -1 if any is okay

    private void checkIfOver()
    {
        over = new byte[9];
        for (int i = 0; i < 9; i++)
        {
            byte[][] Istate = new byte[3][3];

            int index = 0;

            for (int y = 3 * (i / 3); y < 3 * (i / 3) + 3; y++)
                for (int x = 3 * (i % 3); x < 3 * (i % 3) + 3; x++) {
                    Istate[index / 3][index % 3] = state[y][x];
                    index++;
                }


            for (byte player = 1; player > -3; player -= 2) {
                // ver
                for (int x = 0; x < 3; x++) {
                    boolean is = true;
                    for (int y = 0; y < 3; y++) {
                        if (Istate[y][x] != player)
                            is = false;
                    }
                    if (is) {
                        over[i] = player;
                        return;
                    }
                }

                // hor
                for (int y = 0; y < 3; y++) {
                    boolean is = true;
                    for (int x = 0; x < 3; x++) {
                        if (Istate[y][x] != player)
                            is = false;
                    }
                    if (is) {
                        over[i] = player;
                        return;
                    }
                }

                //dia
                boolean is = true;
                for (int x = 0; x < 3; x++) {
                    if (Istate[x][x] != player)
                        is = false;
                }
                if (is) {
                    over[i] = player;
                    return;
                }
                is = true;
                for (int x = 0; x < 3; x++) {
                    if (Istate[2 - x][x] != player)
                        is = false;
                }
                if (is) {
                    over[i] = player;
                    return;
                }
            }
            //draw

            boolean full = true;
            for (int y = 0; y < 3; y++)
                for (int x = 0; x < 3; x++) {
                    if (Istate[y][x] == 0)
                        full = false;
                }
            if (full) {
                over[i] = 2;
            }
        }

        for (byte b : over)
        {
            if (b == 1)
                wins[0]++;
            else if(b == -1)
                wins[1]++;
            else if(b == 2)
                draws++;
        }
    }

    int rank()
    {

        if(over == null)
            checkIfOver();


        return 40*wins[0]
                - 40*wins[1]
                - 5*draws;

    }

    void genMoves(byte player)
    {
        if(over == null)
            checkIfOver();

        moves = new HashMap<>();


        if(nextMove == -1 || over[nextMove] != 0)
        {
            for (int i = 0; i < 9; i++)
            {
                if(over[i] != 0)
                    continue; // skip if already over

                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++)
                    {
                        Pos p = new Pos((byte)(3 * (i % 3) + x), (byte)(3 * (i / 3) + y));
                        if(state[p.y][p.x] == 0)
                        {
                            moves.put(p, new State(this, p, player, (byte)(3 * y + x)));
                        }
                    }
                }
            }
        }
        else
        {
            int i = nextMove;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++)
                {
                    Pos p = new Pos((byte)(3 * (i % 3) + x), (byte)(3 * (i / 3) + y));
                    if(state[p.y][p.x] == 0)
                    {
                        moves.put(p, new State(this, p, player, (byte)(3 * y + x)));
                    }
                }
            }
        }

        return;
    }
}
