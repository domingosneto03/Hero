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

    boolean GameRunning = true;
    private Screen screen;

     Hero hero = new Hero(10,10);
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(hero.getX(), hero.getY(), TextCharacter.fromCharacter('X')[0]);
        screen.refresh();

    }

    private void processKey(KeyStroke key) throws IOException {
        switch(key.getKeyType()) {
            case ArrowUp:
                hero.moveUp();
                break;
            case ArrowDown:
                hero.moveDown();
                break;
            case ArrowRight:
                hero.moveRight();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            case EOF:
                GameRunning = false;
                break;
            case Character:
                switch(key.getCharacter()) {
                    case 'q':
                        screen.close();
                        GameRunning = false;
                        break;
                }
        }
    }

    public void run() throws IOException {
        while(GameRunning) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
        }

    }

}
