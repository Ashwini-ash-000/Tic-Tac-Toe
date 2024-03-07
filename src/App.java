import board.Board;
import player.Player;
import game.Game;

public class App {
    public static void main(String[] args) throws Exception {
       System.out.println("TIC TAC Toe");

       Board b=new Board(3,'-');

       b.printBoardConfig();

       Player p1=new Player();
       p1.setPlayerDetails("Akash", 'X');

       Player p2=new Player();
       p2.setPlayerDetails("Appu", 'O');

       p1.getPlayerDetails();
       p2.getPlayerDetails();

       Game g=new Game(new Player[] {p1,p2}, b);
       g.play();
    }
}
