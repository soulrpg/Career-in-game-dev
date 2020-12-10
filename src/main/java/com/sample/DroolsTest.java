package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.*;

import javax.swing.*;
import java.awt.*;

public class DroolsTest{

    public static final void main(String[] args) {
    	JPanel panel = new JPanel();
    	JRadioButton r1 = new JRadioButton("1");
    	JRadioButton r2 = new JRadioButton("2");
    	JRadioButton r3 = new JRadioButton("3");
    	JLabel label = new JLabel("Pytanie");
    	panel.add(label);
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
    	panel.add(r1);
    	panel.add(r2);
    	panel.add(r3);
        r3.setVisible(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        //ShowQuestion(panel);
        
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	KieRuntimeLogger kLogger = ks.getLoggers().newFileLogger(kSession, "test");
        	
        	kSession.setGlobal( "panel", panel);
        	kSession.setGlobal( "label", label);
        	kSession.setGlobal( "r1", r1);
        	kSession.setGlobal( "r2", r2);
        	kSession.setGlobal( "r3", r3);

            // go !
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
            kLogger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static int ShowQuestion(JPanel panel, JRadioButton r1, JRadioButton r2, JRadioButton r3) {
    	// Show message dialog with question and anwsers
    	JOptionPane.showMessageDialog(null, panel, "Pytanie", JOptionPane.PLAIN_MESSAGE);
    	if(r1.isSelected()) {
    		return 1;
    	}
    	else if(r2.isSelected()) {
    		return 2;
    	}
    	else if(r3.isSelected()) {
    		return 3;
    	}
    	else
    		return 0;
    	
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
    

}
