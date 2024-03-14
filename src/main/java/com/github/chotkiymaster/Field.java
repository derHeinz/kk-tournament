package com.github.chotkiymaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JComponent {

    public static final int SQUARE_SIZE = 23;
    public static final int GAP_BETWEEN_SQUARES = -2;
    public static final int OUTER_BORDER = 3;

    public Square[][] getSquares() {
        return squares;
    }

    private final Square[][] squares;

    public Field(int countX, int countY) {
        this.setSize(
                countX * (SQUARE_SIZE + GAP_BETWEEN_SQUARES) - GAP_BETWEEN_SQUARES,
                countY * (SQUARE_SIZE + GAP_BETWEEN_SQUARES) - GAP_BETWEEN_SQUARES
        );
        setBorder(BorderFactory.createEmptyBorder(OUTER_BORDER, OUTER_BORDER, OUTER_BORDER, OUTER_BORDER));

        this.squares = new Square[countX][countY];
        for (int y = 0; y < countY; y++){
            for (int x = 0; x < countX; x++){

                this.squares[x][y] = new Square(
                    x>0 ? this.squares[x-1][y].getRightWall() : new Wall(), 
                    new Wall(), 
                    new Wall(), 
                    y>0 ? this.squares[x][y-1].getUpperWall() : new Wall()
                    );

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
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("Mouse clicked at (%d, %d)%n", e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (graphics instanceof Graphics2D graphics2D) {
            var initialTransform = graphics2D.getTransform();
            int countX = squares.length;
            int countY = squares[0].length;

            graphics.setColor(Color.BLACK);
            for (int y = 0; y < countY; y++) {
                for (int x = 0; x < countX; x++) {
                    graphics2D.translate(OUTER_BORDER + x * (SQUARE_SIZE + GAP_BETWEEN_SQUARES), this.getHeight() - OUTER_BORDER - y * (SQUARE_SIZE + GAP_BETWEEN_SQUARES));
                    this.squares[x][y].paint(graphics2D);
                    graphics2D.setTransform(initialTransform);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        final var insets = getBorder().getBorderInsets(this);
        return new Dimension(getWidth() + insets.left + insets.right, getHeight() + insets.bottom + insets.top);
    }

    void nurso(MouseEvent ev) {
        processMouseEvent(ev);
    }
}
