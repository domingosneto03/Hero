import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y){
        super(x,y);
    }
    public Position move(){
        Random random = new Random();
        int n = random.nextInt(4);
        switch(n){
            case 0:
                position.setX(position.getX()-1);
                break;
            case 1:
                position.setX(position.getX()+1);
                break;
            case 2:
                position.setY(position.getY()-1);
                break;
            case 3:
                position.setY(position.getY()+1);
                break;
        }
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}
