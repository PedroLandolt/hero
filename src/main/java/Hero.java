import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void moveUp() {y--;}
    public void moveDown() {y++;}
    public void moveLeft() {x--;}
    public void moveRight() {x++;}

    public void draw(Screen screen) throws IOException{
        screen.setCharacter(x, y, TextCharacter.fromCharacter('H')[0]);
    }
    
    private int x,y;
}
