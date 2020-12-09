package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.*;

import javax.swing.*;
import java.awt.*;

public class DroolsTest{

    public static final void main(String[] args) {
    	JFrame f = new JFrame();
    	JButton b = new JButton("Zatwierdü");
    	JRadioButton r1 = new JRadioButton("1");
    	JRadioButton r2 = new JRadioButton("2");
    	JRadioButton r3 = new JRadioButton("3");
    	JLabel label = new JLabel("Pytanie");
        r3.setVisible(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
    	f.add(label);
    	f.add(r1);
    	f.add(r2);
    	f.add(r3);
    	f.add(b);
    	b.setHorizontalAlignment(JButton.CENTER);
    	f.setSize(300, 300);
    	f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
    	f.setVisible(true);
    	
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	KieRuntimeLogger kLogger = ks.getLoggers().newFileLogger(kSession, "test");
        	
        	kSession.setGlobal( "frame", f);
        	kSession.setGlobal( "label", label);
        	kSession.setGlobal( "r1", r1);
        	kSession.setGlobal( "r2", r2);
        	kSession.setGlobal( "r3", r3);
        	kSession.setGlobal("button", b);

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
