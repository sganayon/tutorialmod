package com.sganayon.tutorialmod.client;

public class ClientThirstData {
    private static int playerThirst;

    public static void set(int playerThirst){
        ClientThirstData.playerThirst = playerThirst;
    }

    public static int get(){
        return playerThirst;
    }
}
