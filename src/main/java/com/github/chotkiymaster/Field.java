package com.github.chotkiymaster;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.Map;

public class Field {
    public Square[][] getSquares() {
        return squares;
    }

    private Square[][] squares;

    public Field(int countX, int countY) {
        //TODO
        this.squares = new Square[countX][countY];
        //Wall[] walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()};
        //Square square1 = new Square(walls[0], walls[1], walls[2], walls[3]);
        //Square square2 = new Square();
        //square2.setLeftWall(walls[0]);
        for (int y = 0; y < countY; y++){
            for (int x = 0; x < countX; x++){

                this.squares[x][y] = new Square(
                    x>0 ? this.squares[x-1][y].getRightWall() : new Wall(), 
                    new Wall(), 
                    new Wall(), 
                    y>0 ? this.squares[x][y-1].getUpperWall() : new Wall()
                    );

                
                /*this.squares[x][y] = new Square(walls[0], walls[1], walls[2], walls[3]);
                this.squares[x][y].setRightWall(this.squares[x+1][y].getLeftWall());*/

                if(x == 0){
                    this.squares[x][y].getLeftWall().setClosed(true);
                }
                if(x == countX - 1){
                    this.squares[x][y].getRightWall().setClosed(true);
                }
                if(y == countY - 1){
                    this.squares[x][y].getUpperWall().setClosed(true);
                }
                if(y == 0){
                    this.squares[x][y].getBottomWall().setClosed(true);
                }
                //square.setName("square" + x);
            }
        }
        
    }

    void draw() {
        Graphics2D graphics2D = new BufferedImage(this.squares.length * 23 +3, this.squares[0].length * 23 + 3, BufferedImage.TYPE_BYTE_GRAY).createGraphics();
        graphics2D.drawLine(0,0, 20, 50);
        graphics2D.draw(new Rectangle2D.Double(30., 30., 50., 40.));
        graphics2D.fill(new Rectangle2D.Double(70., 70., 90., 80.));
        graphics2D.drawString("A", 40, 60);

        var sndImage = graphics2D;
    }
}
