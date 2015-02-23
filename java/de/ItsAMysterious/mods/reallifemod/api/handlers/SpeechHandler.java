package de.ItsAMysterious.mods.reallifemod.api.handlers;

import javax.sound.midi.VoiceStatus;

import com.sun.speech.freetts.FeatureProcessor;
import com.sun.speech.freetts.Item;
import com.sun.speech.freetts.ProcessException;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class SpeechHandler implements Runnable{
	
	public static double vVoice;
	public static float vPitch;
	public static float vPitchRange;
	public static float vPitchShift;
	public static String vSentence;

	
	public static void speechSynth(double vVoice2,float vPitch2,float vPitchRange2,float vPitchShift2,String vSentence2 ) {
		vVoice = vVoice2;
		vPitch = vPitch2;
		vPitchRange = vPitchRange2;
		vPitchShift=vPitchShift2;
		vSentence = vSentence2;
		System.out.println(" set the values to "+vVoice+" "+vPitch+" "+vPitchRange+" "+vPitchShift+" "+vSentence);
		Thread t1 = new Thread(new SpeechHandler());
		t1.start();
	}

	@Override
	public void run() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		System.out.println(" about to try "+vVoice+" "+vPitch+" "+vPitchRange+" "+vPitchShift+" "+vSentence);
		try {
			String VOICENAME = "kevin16";
			if (vVoice == 1) {VOICENAME = "kevin";}; //for now we wont use the low quality one, it would proberly not be used much and thus the removal of an augment will help players
			if (vVoice == 2) {VOICENAME = "kevin16";};
			if (vVoice == 3) {VOICENAME = "alan";}; //REMed out as alan can only talk about the time
			if (vVoice == 4) {VOICENAME = "alan";}; //REMed out as alan can only talk about the time
			Voice voice;
			VoiceManager vm= VoiceManager.getInstance();
			voice=vm.getVoice(VOICENAME);
			voice.allocate();
			voice.setPitch(vPitch);
			voice.setPitchRange(vPitchRange);
			voice.setPitchShift(vPitchShift);
			voice.setDetailedMetrics(true);
			voice.setRate(1000);
			voice.speak(vSentence);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}