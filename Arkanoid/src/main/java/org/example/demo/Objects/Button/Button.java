package org.example.demo.Objects.Button;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.example.demo.GameManager;
import org.example.demo.Objects.GameObject;
import org.example.demo.Renderer;

public class Button extends GameObject {

    protected Image imageDefault;
    protected Image imageHover;

    protected boolean isHovered;

    public Button(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void update(GameManager gameManager) {
        double mouseX = gameManager.getMouseX();
        double mouseY = gameManager.getMouseY();
        //System.out.println("mouseX: " + mouseX + " mouseY: " + mouseY);
        isHovered = mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void render(Renderer r) {
        if (isHovered) {
            r.draw(this, imageHover);
        } else {
            r.draw(this, imageDefault);
        }
    }

    public boolean isClicked(MouseEvent event) {
        return isHovered && event.getButton() == MouseButton.PRIMARY;
    }
}
