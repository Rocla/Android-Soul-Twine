package com.romainclaret.android.soultwine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.content.Context;

public class DroidPuppet {

	static String ipAddress = "";

	public static void setName(String ip)
	{
		ipAddress = ip;
	}
	public static String getName()
	{
		return ipAddress;
	}
	
	public static void Sit(Context c){
		try{				
			Socket clientSocket;
			clientSocket = new Socket(ipAddress, 9559);
			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			ObjectInputStream in = new ObjectInputStream(c.getAssets().open("Sit.data"));
			ArrayList<byte[]> FinalMessageByteArray = (ArrayList<byte[]>) in.readObject();
			
			//Write to server
			for(int i = 0; i < 27 ; i++){
				//Send my Byte array to DroidPuppet
				outToServer.write(FinalMessageByteArray.get(i));
				outToServer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void Stand(Context c){
		try{		
			Socket clientSocket;
			clientSocket = new Socket(DroidPuppet.getName(), 9559);
			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			ObjectInputStream in = new ObjectInputStream(c.getAssets().open("Stand.data"));
			ArrayList<byte[]> FinalMessageByteArray = (ArrayList<byte[]>) in.readObject();

			//Write to server
			for(int i = 0; i < 38 ; i++){			

				//Send my Byte array to DroidPuppet
				outToServer.write(FinalMessageByteArray.get(i));
				outToServer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void Forward()
	{
		try {
			Socket clientSocket;
			clientSocket = new Socket(ipAddress, 9559);

			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//Package to make DroidPuppet Go Forward
			byte walkForward[] = {
					0x50, 0x4f, 0x53, 0x54, 0x20, 0x2f, 0x20, 0x48, 
					0x54, 0x54, 0x50, 0x2f, 0x31, 0x2e, 0x31, 0x0d, 
					0x0a, 0x48, 0x6f, 0x73, 0x74, 0x3a, 0x20, 0x31, 
					0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 
					0x2e, 0x31, 0x32, 0x33, 0x3a, 0x39, 0x35, 0x35, 
					0x39, 0x0d, 0x0a, 0x55, 0x73, 0x65, 0x72, 0x2d, 
					0x41, 0x67, 0x65, 0x6e, 0x74, 0x3a, 0x20, 0x67, 
					0x53, 0x4f, 0x41, 0x50, 0x2f, 0x32, 0x2e, 0x37, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x54, 0x79, 0x70, 0x65, 0x3a, 0x20, 
					0x61, 0x70, 0x70, 0x6c, 0x69, 0x63, 0x61, 0x74, 
					0x69, 0x6f, 0x6e, 0x2f, 0x64, 0x69, 0x6d, 0x65, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x4c, 0x65, 0x6e, 0x67, 0x74, 0x68, 
					0x3a, 0x20, 0x37, 0x32, 0x30, 0x0d, 0x0a, 0x43, 
					0x6f, 0x6e, 0x6e, 0x65, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x6b, 0x65, 0x65, 0x70, 0x2d, 
					0x61, 0x6c, 0x69, 0x76, 0x65, 0x0d, 0x0a, 0x53, 
					0x4f, 0x41, 0x50, 0x41, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x22, 0x22, 0x0d, 0x0a, 0x0d, 
					0x0a, 0x0e, 0x20, 0x00, 0x00, 0x00, 0x07, 0x00, 
					0x29, 0x00, 0x00, 0x02, (byte) 0x90, 0x63, 0x69, 0x64, 
					0x3a, 0x69, 0x64, 0x30, 0x00, 0x68, 0x74, 0x74, 
					0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 
					0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 
					0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 
					0x65, 0x6c, 0x6f, 0x70, 0x65, 0x2f, 0x00, 0x00, 
					0x00, 0x3c, 0x3f, 0x78, 0x6d, 0x6c, 0x20, 0x76, 
					0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d, 0x22, 
					0x31, 0x2e, 0x30, 0x22, 0x20, 0x65, 0x6e, 0x63, 
					0x6f, 0x64, 0x69, 0x6e, 0x67, 0x3d, 0x22, 0x55, 
					0x54, 0x46, 0x2d, 0x38, 0x22, 0x3f, 0x3e, 0x0a, 
					0x3c, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 
					0x70, 0x65, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 
					0x3a, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 
					0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 0x6d, 0x61, 
					0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 0x6f, 0x61, 
					0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x73, 0x6f, 
					0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 0x65, 0x6c, 
					0x6f, 0x70, 0x65, 0x2f, 0x22, 0x20, 0x78, 0x6d, 
					0x6c, 0x6e, 0x73, 0x3a, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x43, 0x3d, 0x22, 0x68, 0x74, 
					0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 
					0x65, 0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 
					0x73, 0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 
					0x2f, 0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 
					0x63, 0x6f, 0x64, 0x69, 0x6e, 0x67, 0x2f, 0x22, 
					0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 
					0x73, 0x69, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 
					0x3a, 0x2f, 0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 
					0x33, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 
					0x30, 0x31, 0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 
					0x68, 0x65, 0x6d, 0x61, 0x2d, 0x69, 0x6e, 0x73, 
					0x74, 0x61, 0x6e, 0x63, 0x65, 0x22, 0x20, 0x78, 
					0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 0x73, 0x64, 
					0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 
					0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 0x33, 0x2e, 
					0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 0x30, 0x31, 
					0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 
					0x73, 0x3a, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 
					0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 
					0x61, 0x6c, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x64, 0x65, 0x62, 0x61, 0x72, 0x61, 
					0x6e, 0x22, 0x3e, 0x3c, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x56, 0x3a, 0x42, 0x6f, 0x64, 
					0x79, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 
					0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 
					0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x61, 
					0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 
					0x6d, 0x6f, 0x64, 0x3e, 0x41, 0x4c, 0x4d, 0x6f, 
					0x74, 0x69, 0x6f, 0x6e, 0x3c, 0x2f, 0x61, 0x6c, 
					0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 
					0x6f, 0x64, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 
					0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 
					0x68, 0x3e, 0x77, 0x61, 0x6c, 0x6b, 0x54, 0x6f, 
					0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 0x68, 0x3e, 
					0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 
					0x72, 0x3a, 0x70, 0x3e, 0x3c, 0x69, 0x74, 0x65, 
					0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 
					0x70, 0x65, 0x3d, 0x22, 0x41, 0x72, 0x72, 0x61, 
					0x79, 0x22, 0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 
					0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 
					0x65, 0x3d, 0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 
					0x6c, 0x6f, 0x61, 0x74, 0x22, 0x3e, 0x31, 0x3c, 
					0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x69, 
					0x74, 0x65, 0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 
					0x74, 0x79, 0x70, 0x65, 0x3d, 0x22, 0x78, 0x73, 
					0x64, 0x3a, 0x66, 0x6c, 0x6f, 0x61, 0x74, 0x22, 
					0x3e, 0x30, 0x3c, 0x2f, 0x69, 0x74, 0x65, 0x6d, 
					0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 0x20, 0x78, 
					0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 0x65, 0x3d, 
					0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 0x6c, 0x6f, 
					0x61, 0x74, 0x22, 0x3e, 0x30, 0x3c, 0x2f, 0x69, 
					0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 0x69, 0x74, 
					0x65, 0x6d, 0x3e, 0x3c, 0x2f, 0x61, 0x6c, 0x62, 
					0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x70, 0x3e, 
					0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 0x4e, 
					0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x2f, 0x53, 
					0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 0x56, 0x3a, 
					0x42, 0x6f, 0x64, 0x79, 0x3e, 0x3c, 0x2f, 0x53, 
					0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 0x56, 0x3a, 
					0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 0x70, 0x65, 
					0x3e };

			//Write to server
			outToServer.write(walkForward);
			outToServer.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void Backward()
	{
		try {			
			Socket clientSocket;
			clientSocket = new Socket(ipAddress, 9559);

			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//Package to make DroidPuppet Go Backward
			byte walkBackward[] = {
					0x50, 0x4f, 0x53, 0x54, 0x20, 0x2f, 0x20, 0x48, 
					0x54, 0x54, 0x50, 0x2f, 0x31, 0x2e, 0x31, 0x0d, 
					0x0a, 0x48, 0x6f, 0x73, 0x74, 0x3a, 0x20, 0x31, 
					0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 
					0x2e, 0x31, 0x32, 0x33, 0x3a, 0x39, 0x35, 0x35, 
					0x39, 0x0d, 0x0a, 0x55, 0x73, 0x65, 0x72, 0x2d, 
					0x41, 0x67, 0x65, 0x6e, 0x74, 0x3a, 0x20, 0x67, 
					0x53, 0x4f, 0x41, 0x50, 0x2f, 0x32, 0x2e, 0x37, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x54, 0x79, 0x70, 0x65, 0x3a, 0x20, 
					0x61, 0x70, 0x70, 0x6c, 0x69, 0x63, 0x61, 0x74, 
					0x69, 0x6f, 0x6e, 0x2f, 0x64, 0x69, 0x6d, 0x65, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x4c, 0x65, 0x6e, 0x67, 0x74, 0x68, 
					0x3a, 0x20, 0x37, 0x32, 0x34, 0x0d, 0x0a, 0x43, 
					0x6f, 0x6e, 0x6e, 0x65, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x6b, 0x65, 0x65, 0x70, 0x2d, 
					0x61, 0x6c, 0x69, 0x76, 0x65, 0x0d, 0x0a, 0x53, 
					0x4f, 0x41, 0x50, 0x41, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x22, 0x22, 0x0d, 0x0a, 0x0d, 
					0x0a, 0x0e, 0x20, 0x00, 0x00, 0x00, 0x07, 0x00, 
					0x29, 0x00, 0x00, 0x02, (byte) 0x91, 0x63, 0x69, 0x64, 
					0x3a, 0x69, 0x64, 0x30, 0x00, 0x68, 0x74, 0x74, 
					0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 
					0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 
					0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 
					0x65, 0x6c, 0x6f, 0x70, 0x65, 0x2f, 0x00, 0x00, 
					0x00, 0x3c, 0x3f, 0x78, 0x6d, 0x6c, 0x20, 0x76, 
					0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d, 0x22, 
					0x31, 0x2e, 0x30, 0x22, 0x20, 0x65, 0x6e, 0x63, 
					0x6f, 0x64, 0x69, 0x6e, 0x67, 0x3d, 0x22, 0x55, 
					0x54, 0x46, 0x2d, 0x38, 0x22, 0x3f, 0x3e, 0x0a, 
					0x3c, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 
					0x70, 0x65, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 
					0x3a, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 
					0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 0x6d, 0x61, 
					0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 0x6f, 0x61, 
					0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x73, 0x6f, 
					0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 0x65, 0x6c, 
					0x6f, 0x70, 0x65, 0x2f, 0x22, 0x20, 0x78, 0x6d, 
					0x6c, 0x6e, 0x73, 0x3a, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x43, 0x3d, 0x22, 0x68, 0x74, 
					0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 
					0x65, 0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 
					0x73, 0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 
					0x2f, 0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 
					0x63, 0x6f, 0x64, 0x69, 0x6e, 0x67, 0x2f, 0x22, 
					0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 
					0x73, 0x69, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 
					0x3a, 0x2f, 0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 
					0x33, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 
					0x30, 0x31, 0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 
					0x68, 0x65, 0x6d, 0x61, 0x2d, 0x69, 0x6e, 0x73, 
					0x74, 0x61, 0x6e, 0x63, 0x65, 0x22, 0x20, 0x78, 
					0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 0x73, 0x64, 
					0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 
					0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 0x33, 0x2e, 
					0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 0x30, 0x31, 
					0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 
					0x73, 0x3a, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 
					0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 
					0x61, 0x6c, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x64, 0x65, 0x62, 0x61, 0x72, 0x61, 
					0x6e, 0x22, 0x3e, 0x3c, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x56, 0x3a, 0x42, 0x6f, 0x64, 
					0x79, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 
					0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 
					0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x61, 
					0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 
					0x6d, 0x6f, 0x64, 0x3e, 0x41, 0x4c, 0x4d, 0x6f, 
					0x74, 0x69, 0x6f, 0x6e, 0x3c, 0x2f, 0x61, 0x6c, 
					0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 
					0x6f, 0x64, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 
					0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 
					0x68, 0x3e, 0x77, 0x61, 0x6c, 0x6b, 0x54, 0x6f, 
					0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 0x68, 0x3e, 
					0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 
					0x72, 0x3a, 0x70, 0x3e, 0x3c, 0x69, 0x74, 0x65, 
					0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 
					0x70, 0x65, 0x3d, 0x22, 0x41, 0x72, 0x72, 0x61, 
					0x79, 0x22, 0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 
					0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 
					0x65, 0x3d, 0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 
					0x6c, 0x6f, 0x61, 0x74, 0x22, 0x3e, 0x2d, 0x31, 
					0x3c, 0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 
					0x69, 0x74, 0x65, 0x6d, 0x20, 0x78, 0x73, 0x69, 
					0x3a, 0x74, 0x79, 0x70, 0x65, 0x3d, 0x22, 0x78, 
					0x73, 0x64, 0x3a, 0x66, 0x6c, 0x6f, 0x61, 0x74, 
					0x22, 0x3e, 0x30, 0x3c, 0x2f, 0x69, 0x74, 0x65, 
					0x6d, 0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 0x20, 
					0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 0x65, 
					0x3d, 0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 0x6c, 
					0x6f, 0x61, 0x74, 0x22, 0x3e, 0x30, 0x3c, 0x2f, 
					0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 0x69, 
					0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 0x61, 0x6c, 
					0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x70, 
					0x3e, 0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 
					0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 
					0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x2f, 
					0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 0x56, 
					0x3a, 0x42, 0x6f, 0x64, 0x79, 0x3e, 0x3c, 0x2f, 
					0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 0x56, 
					0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 0x70, 
					0x65, 0x3e, 0x00, 0x00, 0x00 };

			//Write to server
			outToServer.write(walkBackward);
			outToServer.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void RotateLeft()
	{
		try {
			Socket clientSocket;
			clientSocket = new Socket(ipAddress, 9559);

			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//Package to make DroidPuppet Turn Left
			byte turnLeft[] = {
					0x50, 0x4f, 0x53, 0x54, 0x20, 0x2f, 0x20, 0x48, 
					0x54, 0x54, 0x50, 0x2f, 0x31, 0x2e, 0x31, 0x0d, 
					0x0a, 0x48, 0x6f, 0x73, 0x74, 0x3a, 0x20, 0x31, 
					0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 
					0x2e, 0x31, 0x32, 0x33, 0x3a, 0x39, 0x35, 0x35, 
					0x39, 0x0d, 0x0a, 0x55, 0x73, 0x65, 0x72, 0x2d, 
					0x41, 0x67, 0x65, 0x6e, 0x74, 0x3a, 0x20, 0x67, 
					0x53, 0x4f, 0x41, 0x50, 0x2f, 0x32, 0x2e, 0x37, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x54, 0x79, 0x70, 0x65, 0x3a, 0x20, 
					0x61, 0x70, 0x70, 0x6c, 0x69, 0x63, 0x61, 0x74, 
					0x69, 0x6f, 0x6e, 0x2f, 0x64, 0x69, 0x6d, 0x65, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x4c, 0x65, 0x6e, 0x67, 0x74, 0x68, 
					0x3a, 0x20, 0x37, 0x32, 0x34, 0x0d, 0x0a, 0x43, 
					0x6f, 0x6e, 0x6e, 0x65, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x6b, 0x65, 0x65, 0x70, 0x2d, 
					0x61, 0x6c, 0x69, 0x76, 0x65, 0x0d, 0x0a, 0x53, 
					0x4f, 0x41, 0x50, 0x41, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x22, 0x22, 0x0d, 0x0a, 0x0d, 
					0x0a, 0x0e, 0x20, 0x00, 0x00, 0x00, 0x07, 0x00, 
					0x29, 0x00, 0x00, 0x02, (byte) 0x92, 0x63, 0x69, 0x64, 
					0x3a, 0x69, 0x64, 0x30, 0x00, 0x68, 0x74, 0x74, 
					0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 
					0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 
					0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 
					0x65, 0x6c, 0x6f, 0x70, 0x65, 0x2f, 0x00, 0x00, 
					0x00, 0x3c, 0x3f, 0x78, 0x6d, 0x6c, 0x20, 0x76, 
					0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d, 0x22, 
					0x31, 0x2e, 0x30, 0x22, 0x20, 0x65, 0x6e, 0x63, 
					0x6f, 0x64, 0x69, 0x6e, 0x67, 0x3d, 0x22, 0x55, 
					0x54, 0x46, 0x2d, 0x38, 0x22, 0x3f, 0x3e, 0x0a, 
					0x3c, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 
					0x70, 0x65, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 
					0x3a, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 
					0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 0x6d, 0x61, 
					0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 0x6f, 0x61, 
					0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x73, 0x6f, 
					0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 0x65, 0x6c, 
					0x6f, 0x70, 0x65, 0x2f, 0x22, 0x20, 0x78, 0x6d, 
					0x6c, 0x6e, 0x73, 0x3a, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x43, 0x3d, 0x22, 0x68, 0x74, 
					0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 
					0x65, 0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 
					0x73, 0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 
					0x2f, 0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 
					0x63, 0x6f, 0x64, 0x69, 0x6e, 0x67, 0x2f, 0x22, 
					0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 
					0x73, 0x69, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 
					0x3a, 0x2f, 0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 
					0x33, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 
					0x30, 0x31, 0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 
					0x68, 0x65, 0x6d, 0x61, 0x2d, 0x69, 0x6e, 0x73, 
					0x74, 0x61, 0x6e, 0x63, 0x65, 0x22, 0x20, 0x78, 
					0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 0x73, 0x64, 
					0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 
					0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 0x33, 0x2e, 
					0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 0x30, 0x31, 
					0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 
					0x73, 0x3a, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 
					0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 
					0x61, 0x6c, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x64, 0x65, 0x62, 0x61, 0x72, 0x61, 
					0x6e, 0x22, 0x3e, 0x3c, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x56, 0x3a, 0x42, 0x6f, 0x64, 
					0x79, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 
					0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 
					0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x61, 
					0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 
					0x6d, 0x6f, 0x64, 0x3e, 0x41, 0x4c, 0x4d, 0x6f, 
					0x74, 0x69, 0x6f, 0x6e, 0x3c, 0x2f, 0x61, 0x6c, 
					0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 
					0x6f, 0x64, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 
					0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 
					0x68, 0x3e, 0x77, 0x61, 0x6c, 0x6b, 0x54, 0x6f, 
					0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 0x68, 0x3e, 
					0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 
					0x72, 0x3a, 0x70, 0x3e, 0x3c, 0x69, 0x74, 0x65, 
					0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 
					0x70, 0x65, 0x3d, 0x22, 0x41, 0x72, 0x72, 0x61, 
					0x79, 0x22, 0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 
					0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 
					0x65, 0x3d, 0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 
					0x6c, 0x6f, 0x61, 0x74, 0x22, 0x3e, 0x30, 0x3c, 
					0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x69, 
					0x74, 0x65, 0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 
					0x74, 0x79, 0x70, 0x65, 0x3d, 0x22, 0x78, 0x73, 
					0x64, 0x3a, 0x66, 0x6c, 0x6f, 0x61, 0x74, 0x22, 
					0x3e, 0x30, 0x3c, 0x2f, 0x69, 0x74, 0x65, 0x6d, 
					0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 0x20, 0x78, 
					0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 0x65, 0x3d, 
					0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 0x6c, 0x6f, 
					0x61, 0x74, 0x22, 0x3e, 0x30, 0x2e, 0x35, 0x3c, 
					0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 
					0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 0x61, 
					0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 
					0x70, 0x3e, 0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 
					0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 
					0x6c, 0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 
					0x2f, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x42, 0x6f, 0x64, 0x79, 0x3e, 0x3c, 
					0x2f, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 
					0x70, 0x65, 0x3e, 0x00, 0x00 };

			//Write to server
			outToServer.write(turnLeft);
			outToServer.flush();

			//read from server
			String modifiedSentence = inFromServer.readLine();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void RotateRight()
	{
		try {
			Socket clientSocket;
			clientSocket = new Socket(ipAddress, 9559);

			//Get DataOutput Stream to write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			//Get input stream to read from server
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//Package to make DroidPuppet Turn Right
			byte turnRight[] = {
					0x50, 0x4f, 0x53, 0x54, 0x20, 0x2f, 0x20, 0x48, 
					0x54, 0x54, 0x50, 0x2f, 0x31, 0x2e, 0x31, 0x0d, 
					0x0a, 0x48, 0x6f, 0x73, 0x74, 0x3a, 0x20, 0x31, 
					0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 
					0x2e, 0x31, 0x32, 0x33, 0x3a, 0x39, 0x35, 0x35, 
					0x39, 0x0d, 0x0a, 0x55, 0x73, 0x65, 0x72, 0x2d, 
					0x41, 0x67, 0x65, 0x6e, 0x74, 0x3a, 0x20, 0x67, 
					0x53, 0x4f, 0x41, 0x50, 0x2f, 0x32, 0x2e, 0x37, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x54, 0x79, 0x70, 0x65, 0x3a, 0x20, 
					0x61, 0x70, 0x70, 0x6c, 0x69, 0x63, 0x61, 0x74, 
					0x69, 0x6f, 0x6e, 0x2f, 0x64, 0x69, 0x6d, 0x65, 
					0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 
					0x74, 0x2d, 0x4c, 0x65, 0x6e, 0x67, 0x74, 0x68, 
					0x3a, 0x20, 0x37, 0x32, 0x34, 0x0d, 0x0a, 0x43, 
					0x6f, 0x6e, 0x6e, 0x65, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x6b, 0x65, 0x65, 0x70, 0x2d, 
					0x61, 0x6c, 0x69, 0x76, 0x65, 0x0d, 0x0a, 0x53, 
					0x4f, 0x41, 0x50, 0x41, 0x63, 0x74, 0x69, 0x6f, 
					0x6e, 0x3a, 0x20, 0x22, 0x22, 0x0d, 0x0a, 0x0d, 
					0x0a, 0x0e, 0x20, 0x00, 0x00, 0x00, 0x07, 0x00, 
					0x29, 0x00, 0x00, 0x02, (byte) 0x93, 0x63, 0x69, 0x64, 
					0x3a, 0x69, 0x64, 0x30, 0x00, 0x68, 0x74, 0x74, 
					0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 
					0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 
					0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 
					0x65, 0x6c, 0x6f, 0x70, 0x65, 0x2f, 0x00, 0x00, 
					0x00, 0x3c, 0x3f, 0x78, 0x6d, 0x6c, 0x20, 0x76, 
					0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d, 0x22, 
					0x31, 0x2e, 0x30, 0x22, 0x20, 0x65, 0x6e, 0x63, 
					0x6f, 0x64, 0x69, 0x6e, 0x67, 0x3d, 0x22, 0x55, 
					0x54, 0x46, 0x2d, 0x38, 0x22, 0x3f, 0x3e, 0x0a, 
					0x3c, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 
					0x70, 0x65, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 
					0x3a, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 0x4e, 
					0x56, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 
					0x2f, 0x2f, 0x73, 0x63, 0x68, 0x65, 0x6d, 0x61, 
					0x73, 0x2e, 0x78, 0x6d, 0x6c, 0x73, 0x6f, 0x61, 
					0x70, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x73, 0x6f, 
					0x61, 0x70, 0x2f, 0x65, 0x6e, 0x76, 0x65, 0x6c, 
					0x6f, 0x70, 0x65, 0x2f, 0x22, 0x20, 0x78, 0x6d, 
					0x6c, 0x6e, 0x73, 0x3a, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x43, 0x3d, 0x22, 0x68, 0x74, 
					0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x73, 0x63, 0x68, 
					0x65, 0x6d, 0x61, 0x73, 0x2e, 0x78, 0x6d, 0x6c, 
					0x73, 0x6f, 0x61, 0x70, 0x2e, 0x6f, 0x72, 0x67, 
					0x2f, 0x73, 0x6f, 0x61, 0x70, 0x2f, 0x65, 0x6e, 
					0x63, 0x6f, 0x64, 0x69, 0x6e, 0x67, 0x2f, 0x22, 
					0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 
					0x73, 0x69, 0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 
					0x3a, 0x2f, 0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 
					0x33, 0x2e, 0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 
					0x30, 0x31, 0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 
					0x68, 0x65, 0x6d, 0x61, 0x2d, 0x69, 0x6e, 0x73, 
					0x74, 0x61, 0x6e, 0x63, 0x65, 0x22, 0x20, 0x78, 
					0x6d, 0x6c, 0x6e, 0x73, 0x3a, 0x78, 0x73, 0x64, 
					0x3d, 0x22, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 
					0x2f, 0x77, 0x77, 0x77, 0x2e, 0x77, 0x33, 0x2e, 
					0x6f, 0x72, 0x67, 0x2f, 0x32, 0x30, 0x30, 0x31, 
					0x2f, 0x58, 0x4d, 0x4c, 0x53, 0x63, 0x68, 0x65, 
					0x6d, 0x61, 0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 
					0x73, 0x3a, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 
					0x22, 0x20, 0x78, 0x6d, 0x6c, 0x6e, 0x73, 0x3a, 
					0x61, 0x6c, 0x3d, 0x22, 0x75, 0x72, 0x6e, 0x3a, 
					0x61, 0x6c, 0x64, 0x65, 0x62, 0x61, 0x72, 0x61, 
					0x6e, 0x22, 0x3e, 0x3c, 0x53, 0x4f, 0x41, 0x50, 
					0x2d, 0x45, 0x4e, 0x56, 0x3a, 0x42, 0x6f, 0x64, 
					0x79, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 
					0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 0x6c, 0x6c, 
					0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 0x3c, 0x61, 
					0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 
					0x6d, 0x6f, 0x64, 0x3e, 0x41, 0x4c, 0x4d, 0x6f, 
					0x74, 0x69, 0x6f, 0x6e, 0x3c, 0x2f, 0x61, 0x6c, 
					0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 
					0x6f, 0x64, 0x3e, 0x3c, 0x61, 0x6c, 0x62, 0x72, 
					0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 
					0x68, 0x3e, 0x77, 0x61, 0x6c, 0x6b, 0x54, 0x6f, 
					0x3c, 0x2f, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 
					0x65, 0x72, 0x3a, 0x6d, 0x65, 0x74, 0x68, 0x3e, 
					0x3c, 0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 
					0x72, 0x3a, 0x70, 0x3e, 0x3c, 0x69, 0x74, 0x65, 
					0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 
					0x70, 0x65, 0x3d, 0x22, 0x41, 0x72, 0x72, 0x61, 
					0x79, 0x22, 0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 
					0x20, 0x78, 0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 
					0x65, 0x3d, 0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 
					0x6c, 0x6f, 0x61, 0x74, 0x22, 0x3e, 0x30, 0x3c, 
					0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x69, 
					0x74, 0x65, 0x6d, 0x20, 0x78, 0x73, 0x69, 0x3a, 
					0x74, 0x79, 0x70, 0x65, 0x3d, 0x22, 0x78, 0x73, 
					0x64, 0x3a, 0x66, 0x6c, 0x6f, 0x61, 0x74, 0x22, 
					0x3e, 0x30, 0x3c, 0x2f, 0x69, 0x74, 0x65, 0x6d, 
					0x3e, 0x3c, 0x69, 0x74, 0x65, 0x6d, 0x20, 0x78, 
					0x73, 0x69, 0x3a, 0x74, 0x79, 0x70, 0x65, 0x3d, 
					0x22, 0x78, 0x73, 0x64, 0x3a, 0x66, 0x6c, 0x6f, 
					0x61, 0x74, 0x22, 0x3e, 0x2d, 0x30, 0x2e, 0x35, 
					0x3c, 0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 
					0x2f, 0x69, 0x74, 0x65, 0x6d, 0x3e, 0x3c, 0x2f, 
					0x61, 0x6c, 0x62, 0x72, 0x6f, 0x6b, 0x65, 0x72, 
					0x3a, 0x70, 0x3e, 0x3c, 0x2f, 0x61, 0x6c, 0x62, 
					0x72, 0x6f, 0x6b, 0x65, 0x72, 0x3a, 0x63, 0x61, 
					0x6c, 0x6c, 0x4e, 0x61, 0x6f, 0x71, 0x69, 0x3e, 
					0x3c, 0x2f, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 
					0x4e, 0x56, 0x3a, 0x42, 0x6f, 0x64, 0x79, 0x3e, 
					0x3c, 0x2f, 0x53, 0x4f, 0x41, 0x50, 0x2d, 0x45, 
					0x4e, 0x56, 0x3a, 0x45, 0x6e, 0x76, 0x65, 0x6c, 
					0x6f, 0x70, 0x65, 0x3e, 0x00 };

			//Write to server
			outToServer.write(turnRight);
			outToServer.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}