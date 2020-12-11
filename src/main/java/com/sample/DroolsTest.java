package com.sample;

import org.kie.api.KieServices;
import java.util.ArrayList;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.*;

import javax.swing.*;
import java.awt.*;

public class DroolsTest{
	private static JPanel panel;
	private static ArrayList<JRadioButton> buttons;
	private static JLabel label;
	private static ButtonGroup bg;
	
    public static final void main(String[] args) {
    	panel = new JPanel();
    	buttons = new ArrayList<JRadioButton>();
    	label = new JLabel("Pytanie");
    	panel.add(label);
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        bg = new ButtonGroup();
        //ShowQuestion(panel);
        
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	KieRuntimeLogger kLogger = ks.getLoggers().newFileLogger(kSession, "test");
            // go !
            kSession.fireAllRules();
            kLogger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static int ShowQuestion(String question, ArrayList<String> answers) {
    	// Show message dialog with question and anwsers
    	for(JRadioButton button : buttons) {
    		panel.remove(button);
    		bg.remove(button);
    	}
    	buttons.clear();
    	label.setText(question);
    	for(String answer : answers) {
    		buttons.add(new JRadioButton(answer));
    	}
    	for(JRadioButton button : buttons) {
    		panel.add(button);
    		bg.add(button);
    	}
    	JOptionPane.showMessageDialog(null, panel, "Pytanie", JOptionPane.PLAIN_MESSAGE);
    	for(int i = 0; i < buttons.size(); i++) {
    		if(buttons.get(i).isSelected()) {
    			return i;
    		}
    	}
    	return -1;
    }
}
