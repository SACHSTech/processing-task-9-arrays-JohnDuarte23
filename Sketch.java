import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] circleY = new float[30];
  float[] circleX = new float[30];
  boolean[] ballHideStatus = new boolean[30];

  float fltLife = 3;
  boolean blnAlive = true;
  boolean blnMouseClicked = false;
  float fltSnowSpeed = 2;

  public void mousePressed() {
    blnMouseClicked = true;
  }

  public void mouseReleased() {
    blnMouseClicked = false;
  }

  float fltEllipseX = 200;
  float fltEllipseY = 200;

  boolean blnUpPressed = false;
  boolean blnDownPressed = false;
  boolean blnLeftPressed = false;
  boolean blnRightPressed = false;  
  boolean blnUpArrowPressed = false;
  boolean blnDownArrowPressed = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0, 0, 0);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  } 

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0, 0, 0);

    // Generates hp bar
    fill(255, 0, 0);
    for (int i = 0; i < fltLife; i++) {
      rect(20 * i, 0, 20, 20);
    }

    // Generates player avatar
    fill(36, 86, 244);
    ellipse(fltEllipseX, fltEllipseY, 20, 20);
    
    // Allows for player avatar movement
    if (blnUpPressed){
      fltEllipseY -= 2;
    }
    if (blnRightPressed){
      fltEllipseX += 2;
    }
    if (blnDownPressed){
      fltEllipseY += 2;
    }
    if (blnLeftPressed){
      fltEllipseX -= 2;
    }

    // Checks to see if the player is alive
    if (blnAlive == true){
      for (int i = 0; i < circleY.length; i++) {
        // Checks to see if the user should be able to interact with the snowball in question
        if(ballHideStatus[i] == false){
          // Generates falling snowballs
          fill(255, 255, 255);
          ellipse(circleX[i], circleY[i], 30, 30);

          // Allows user to adjust snowfall speed
          if(blnDownArrowPressed == true){
            fltSnowSpeed = 3;
          }
          else if(blnUpArrowPressed == true){
            fltSnowSpeed = 1;
          }
          else{
            fltSnowSpeed = 2;
          }
          circleY[i] += fltSnowSpeed;

          // Resets the snowballs once they have fallen to the bottom of the screen
          if (circleY[i] > height) {
            circleY[i] = 0;
          }
  
          // Eliminates a snowball from the screen if the user clicks on it
          if(dist(mouseX, mouseY, circleX[i], circleY[i]) <= 15 && blnMouseClicked){
            ballHideStatus[i] = true;
          }

          // If the player is hit by a snowball, then they lose a life
          if (dist(circleX[i], circleY[i], fltEllipseX, fltEllipseY) <= 15) {
            fltLife --;
            ballHideStatus[i] = true;
            
            if(fltLife == 0){
              blnAlive = false;
            }
          }
        }
      }
    }
    // Ends the game if the player loses all lives
    else{
      background(255);
      fill(0);
      text("You Died", 170, 200);
    }
  }

    // Contributes to player movement
    public void keyPressed() {
      if (key == 'w') {
        blnUpPressed = true;
      }
      else if (key == 's') {
        blnDownPressed = true;
      }
      else if (key == 'a') {
        blnLeftPressed = true;
      }
      else if (key == 'd') {
        blnRightPressed = true;
      }
      else if (key == 'i') {
        blnUpArrowPressed = true;
      }
      else if (key == 'k') {
        blnDownArrowPressed = true;
      }
    }

    // Contributes to player movement
    public void keyReleased() {
      if (key == 'w') {
        blnUpPressed = false;
      }
      else if (key == 's') {
        blnDownPressed = false;
      }
      else if (key == 'a') {
        blnLeftPressed = false;
      }
      else if (key == 'd') {
        blnRightPressed = false;
      }
      else if (key == 'i') {
        blnUpArrowPressed = false;
      }
      else if (key == 'k') {
        blnDownArrowPressed = false;
      }
    }
}