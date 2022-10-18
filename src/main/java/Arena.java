import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
        walls = createWalls();
        coins = createCoins();
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#1F1F1F"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);

        for(Coin coin : coins)
            coin.draw(graphics);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Position moveUp() {
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
    }
    public Position moveDown() {
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
    }
    public Position moveLeft() {
        return new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
    }
    public Position moveRight() {
        return new Position(hero.getPosition().getX() + 1, hero.getPosition().getY());
    }

    public void moveHero(Position position) {
        if(canHeroMove(position))
            hero.setPosition(position);

        retrieveCoins();
    }

    private boolean canHeroMove(Position position) {
        return (position.getX() < width && position.getX() >= 0) &&
                (position.getY() < height && position.getY() >= 0) &&
                !walls.contains(new Wall(position.getX(), position.getY()));
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Coin ncoin = new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1);
            if(!coins.contains(ncoin) && !ncoin.getPosition().equals(hero.getPosition())){
                coins.add(ncoin);
            }
        }
        return coins;
    }

    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private Hero hero;

    private List<Wall> walls;

    private List<Coin> coins;
    private int width = 40, height = 20;

    private class Hero extends Element{
        public Hero(int x, int y){ super(x,y);}

        public void draw(TextGraphics graphics){
            graphics.setForegroundColor(TextColor.Factory.fromString("#E37D09"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"H");
        }
    }

}
