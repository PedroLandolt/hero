import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    public Hero(Position position){
        this.position = position;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public void draw(Screen screen) throws IOException{
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('H')[0]);
    }

    private Position position;

    public void setPosition(Position position) {
        this.position = position;
    }
}
