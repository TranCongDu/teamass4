package vn.vanlanguni.ponggame;

import java.applet.Applet;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Sound extends Applet {	
		public static synchronized void play(final String fileName)
		{
			//note: use .wav files
			new Thread (new Runnable(){
				public void run(){
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
						clip.open(inputStream);
						clip.start();

					}catch (Exception e){
						System.out.println("play sound error: " + e.getMessage()+ "for" + fileName);
					}
				}
			}).start();
			
			

		}
	
		

		}



