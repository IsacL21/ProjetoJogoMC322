package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{
	
	private boolean upPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean zPressed;
	private boolean xPressed;
	private boolean cPressed;
	private boolean vPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//setta tecla como pressionada ao pressiona-la
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		
		if (code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_Z) {
			zPressed = true;
		}
		if (code == KeyEvent.VK_X) {
			xPressed = true;
		}
		if (code == KeyEvent.VK_C) {
			cPressed = true;
		}
		if (code == KeyEvent.VK_V) {
			vPressed = true;
		}
	}

	//setta tecla como nao pressionada ao solta-la
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_X) {
			xPressed = false;
		}
		if (code == KeyEvent.VK_Z) {
			zPressed = false;
		}
		if (code == KeyEvent.VK_C) {
			cPressed = false;
		}
		if (code == KeyEvent.VK_V) {
			vPressed = false;
		}
	}
	
	public void resetaValores() {
		
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		zPressed = false;
		xPressed = false;
		cPressed = false;
		vPressed = false;
		
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public boolean isZPressed() {
		return zPressed;
	}
	
	public boolean isXPressed() {
		return xPressed;
	}
	
	public boolean isCPressed() {
		return cPressed;
	}
	
	public boolean isVPressed() {
		return vPressed;
	}
	
	public void SetUpPressed(boolean valor) {
		upPressed = valor;
	}
	
	public void SetDownPressed(boolean valor) {
		downPressed = valor;
	}
	
	public void SetRightPressed(boolean valor) {
		rightPressed = valor;
	}
	
	public void SetLeftPressed(boolean valor) {
		leftPressed = valor;
	}
	
	public void SetisXPressed(boolean valor) {
		xPressed = valor;
	}
	
	public void SetisZPressed(boolean valor) {
		zPressed = valor;
	}
	
	public void SetisCPressed(boolean valor) {
		cPressed = valor;
	}
	
	public void SetisVPressed(boolean valor) {
		vPressed = valor;
	}

}
