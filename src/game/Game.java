package game;

import board.Board;
import player.Player;
import java.util.Scanner;

public class Game {
    Player [] players;
    Board board;
    int turn;
    int noOfMoves;
    Boolean gameOver;
    String zero;
    String cross;

    public Game(Player [] players,Board board)
    {
        this.board = board;
        this.players=players;
        this.turn = 0;
        this.noOfMoves = 0;
        this.gameOver = false;

        StringBuilder z=new StringBuilder();
        StringBuilder c=new StringBuilder();

        for(int i=0;i<board.size;i++)
        {
            z.append('O');
            c.append('X');
        }

        zero=z.toString();
        cross=c.toString();
    }

    public void play()
    {
        int sz=board.size;
        printBoardConfig();

        while(!gameOver)
        {
            noOfMoves++;

            int idx=getidx();

            int row = idx/sz;
            int col = idx%sz;

            board.matrix[row][col] = players[turn].getPlayerSymbol();

            if(noOfMoves >= sz*sz)
            {
                System.out.println("Game Draw");
                return;
            }

            if(noOfMoves >= 2*sz-1 && getCombination())
            {
                gameOver=true;
                printBoardConfig();
                System.out.println(players[turn].getPlayerName() + " is Winner ");
                return;
            }

            turn = (turn + 1)% 2;
            printBoardConfig();
        }
    }

    public int getidx()
    {
        while (true) 
        {
            System.out.println(players[turn].getPlayerName()+" please enter the position: ");
            Scanner scn=new Scanner(System.in);
            
            int pos = scn.nextInt()-1;
            int sz = board.size;

            int row = pos/sz;
            int col=pos%sz;

            if(row < 0 || row >= sz || col <0 || col>=sz)
            {
                System.out.println("Invalid Entry, Please enter correct position :)");
                continue;
            }

            if(board.matrix[row][col] != '-')
            {
                System.out.println("Position is occupied already");
                continue;
            }

            return pos;
        }
    }

    public void printBoardConfig()
    {
        int sz=this.board.size;
        for(int i=0;i<sz;i++)
        {
            for(int j=0;j<sz;j++)
            {
                System.out.print(board.matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public Boolean getCombination()
    {
        int sz=board.size;

        for(int i=0;i<sz;i++)
        {
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<sz;j++)
            {
                sb.append(board.matrix[i][j]);
            }
            String pattern = sb.toString();

        if(pattern.equals(zero) || pattern.equals(cross))
        {
            return true;
        }
        }
        

        for(int i=0;i<sz;i++)
        {
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<sz;j++)
            {
                sb.append(board.matrix[j][i]);
            }
            String pattern = sb.toString();

        if(pattern.equals(zero) || pattern.equals(cross))
        {
            return true;
        }
        }
        

        int i,j;
        i=0;
        j=0;
        StringBuilder sb=new StringBuilder();

        while(i < sz)
        {
            sb.append(board.matrix[i][j]);
            i++;
            j++;
        }
        String pattern = sb.toString();

        if(pattern.equals(zero) || pattern.equals(cross))
        {
            return true;
        }

        i=0;
        j=sz-1;
        sb=new StringBuilder();

        while(j >=0 )
        {
            sb.append(board.matrix[i][j]);
            i++;
            j--;
        }
        pattern = sb.toString();

        if(pattern.equals(zero) || pattern.equals(cross))
        {
            return true;
        }

        return false;

    }
}
