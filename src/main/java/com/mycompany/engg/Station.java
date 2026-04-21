/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
/**
 * Project: Train Reservation System Component: Station Node Logic: Doubly
 * Circular Linked List for Route Mapping
 */
public class Station {

    private String stationName;
    private String locationCode;
    private int platformCount;
    private boolean isHub;

    // Linked List Pointers
    protected Station nextStation;
    protected Station prevStation;

    public Station(String name, String code, int platforms) {
        this.stationName = name;
        this.locationCode = code;
        this.platformCount = platforms;
        this.isHub = platforms > 5;
    }

    // --- Navigation Methods ---
    public Station getNext() {
        return nextStation;
    }

    public Station getPrev() {
        return prevStation;
    }

    public void setNext(Station s) {
        this.nextStation = s;
    }

    public void setPrev(Station s) {
        this.prevStation = s;
    }

    public String getName() {
        return stationName;
    }

    public String getCode() {
        return locationCode;
    }

    // --- Station Services ---
    public void displayStationDetails() {
        System.out.println("----- Station Profile -----");
        System.out.println("Name: " + stationName + " (" + locationCode + ")");
        System.out.println("Platforms: " + platformCount);
        System.out.println("Type: " + (isHub ? "Main Hub" : "Standard Station"));
        System.out.println("Connectivity: Prev ["
                + (prevStation != null ? prevStation.getName() : "None")
                + "] | Next ["
                + (nextStation != null ? nextStation.getName() : "None") + "]");
        System.out.println("---------------------------");
    }

    public boolean canHandleHighTraffic() {
        return isHub && platformCount >= 8;
    }

    // منطق متقدم لحساب المسافة الافتراضية بين المحطات
    public int calculateEstimatedTravelTime(Station target) {
        if (target == this) {
            return 0;
        }
        // منطق بسيط: كل محطة تبعد 15 دقيقة
        return 15;
    }
}
