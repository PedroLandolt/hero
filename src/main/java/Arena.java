import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        hero = new Hero(new Position(10,10));
        walls = createWalls();
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#1F1F1F"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);
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

    private Hero hero;

    private List<Wall> walls;
    private int width = 40, height = 20;

    private class Hero {
        public Hero(Position position){
            this.position = position;
        }

        public void draw(TextGraphics graphics){
            graphics.setForegroundColor(TextColor.Factory.fromString("#E38C09"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(position.getX(), position.getY()),"H");
        }
        private Position position;

        public void setPosition(Position position) {
            this.position = position;
        }
    }

}
