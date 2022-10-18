import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(new Position(10,10));
    }

    public void draw(Screen screen){
        hero.draw(screen);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Position moveUp() {
        return new Position(hero.position.getX(), hero.position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(hero.position.getX(), hero.position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(hero.position.getX() - 1, hero.position.getY());
    }
    public Position moveRight() {
        return new Position(hero.position.getX() + 1, hero.position.getY());
    }

    public void moveHero(Position position) {
        if(canHeroMove(position)){
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position position) {
        return (position.getX() < width && position.getX() >= 0) &&
                (position.getY() < height && position.getY() >= 0);
    }

    private Hero hero;
    private int width = 40, height = 20;

    private class Hero {
        public Hero(Position position){
            this.position = position;
        }

        public void draw(Screen screen){
            screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('H')[0]);
        }
        private Position position;

        public void setPosition(Position position) {
            this.position = position;
        }
    }

}
