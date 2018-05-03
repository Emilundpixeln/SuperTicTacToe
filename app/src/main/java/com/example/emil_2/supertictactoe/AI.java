package com.example.emil_2.supertictactoe;

import android.util.Pair;

import java.util.Map;

/**
 * Created by Emil_2 on 29.04.2018.
 */

public class AI
{

    static Pos nextMove(byte[][] s, byte nextMove, byte player)
    {
        int depth = 2;
        State state = new State(s, nextMove);
        state.genMoves(player);
        Pos bestMove = new Pos((byte)-1, (byte)-1);

        if(player == 1)
        {
            int max = Integer.MIN_VALUE;
            for (Map.Entry<Pos, State> e: state.moves.entrySet())
            {
                int eval = minimax(e.getValue(), depth, (byte)-1);

                if(eval > max)
                {
                    max = eval;
                    bestMove = e.getKey();
                }
            }

        }
        else
        {
            System.out.println("ran ai as -1");
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Pos, State> e: state.moves.entrySet())
            {
                int eval = minimax(e.getValue(), depth, (byte)1);
                System.out.println(e.getKey().x + ", " + e.getKey().y + " got " + eval);
                if(eval < min)
                {
                    min = eval;
                    bestMove = e.getKey();
                }
            }

        }

        return bestMove;

    }

    private static int minimax(State s, int depth, byte player)
    {
        if(depth == 0)
            return s.rank();


        s.genMoves(player);
        if(player == 1)
        {
            int max = Integer.MIN_VALUE;
            for (Map.Entry<Pos, State> e: s.moves.entrySet())
            {
                int eval = minimax(e.getValue(), depth - 1, (byte)-1);
                max = Math.max(max, eval);
            }
            if(max == Integer.MIN_VALUE)
                max = s.rank();
            return max;
        }
        else
        {
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Pos, State> e: s.moves.entrySet())
            {
                int eval = minimax(e.getValue(), depth - 1, (byte)1);
                min = Math.min(min, eval);
            }
            if(min == Integer.MAX_VALUE)
                min = s.rank();
            return min;
        }
    }
}
