import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    public Game(){
        try{
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        }
        catch (IOException e){
            e.printStackTrace();
        }

        arena = new Arena( 40, 20);
    }

    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen);
        screen.refresh();
    }

    public void run() throws IOException{
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();}
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
        }
    }

    private void processKey(KeyStroke key){
        System.out.println(key);
        switch (key.getKeyType()){
            case ArrowUp -> arena.moveHero(arena.moveUp());
            case ArrowDown -> arena.moveHero(arena.moveDown());
            case ArrowLeft -> arena.moveHero(arena.moveLeft());
            case ArrowRight -> arena.moveHero(arena.moveRight());
        }
    }
    private Arena arena;
    private Screen screen;

}
