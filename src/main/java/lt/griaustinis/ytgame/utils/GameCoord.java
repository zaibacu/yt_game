package lt.griaustinis.ytgame.utils;

public class GameCoord {
    private int x;
    private int y;

    public GameCoord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public GameCoord add(GameCoord other){
        return new GameCoord(x - other.getX(), y + other.getY());
    }
}
