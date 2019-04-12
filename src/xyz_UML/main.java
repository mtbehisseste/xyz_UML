package xyz_UML;

import java.util.ArrayList;

public class main {
	public static int sideButtonNum = 6;
	public static ArrayList<xyzButton> buttonList = new ArrayList<xyzButton>(sideButtonNum);
	public static ArrayList<component> allComponents = new ArrayList<component>();

	public static void main(String args[]) {
		new baseGUI();
	}
}
