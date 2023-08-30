package classicarkanoiadgame;
import java.awt.*;

public class MapGenerator {

    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int raw , int col ){
        map = new int[raw][col];


       for (int i = 0 ; i < map.length ; i++ ){
            for (int j = 0 ; j < map[0].length ; j++){
                 map[i][j]=1;
            }
        }

        brickWidth = 250/raw;
        brickHeight = 250/col;
    }

    public void draw(Graphics2D g){
        for (int i = 0 ; i < map.length ; i++ ) {
            for (int j = 0; j < map[0].length; j++) {
                     if(map [i][j] > 0){

                         g.setColor(new Color(240,128,128));
                         g.fill3DRect(j*brickWidth+80 ,i*brickHeight+80 ,brickWidth ,brickHeight , true );
                         g.setStroke(new BasicStroke(20));
                         g.setColor(new Color(180,128,128));
                         g.draw3DRect(j*brickWidth+80 ,i*brickHeight+80 ,brickWidth ,brickHeight , true );



                     }
            }
        }
    }
public void setbrickvalue(int value,int row,int col)
{
    map[row][col]=value;
}


}