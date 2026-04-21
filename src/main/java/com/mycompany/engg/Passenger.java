/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
import java.util.*;
import java.util.*;
import java.util.stream.*;

/**
 * Project: Train Reservation System Component: Passenger Logic: Identity
 * management and dynamic pricing logic
 */
public class Passenger {

    private String name;
    private int id;
    private int age;
    private boolean isStudent;
    private String email;
    private String phoneNumber;

    private int assignedSeat;
    private String ticketId;
    private List<String> travelHistory;
    private double balance; // محفظة افتراضية

    public Passenger(String name, int id, int age, boolean isStudent) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.isStudent = isStudent;
        this.assignedSeat = -1;
        this.ticketId = null;
        this.travelHistory = new ArrayList<>();
        this.balance = 500.0; // الرصيد الافتراضي
    }

    // --- Getters & Setters with Validation ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public int getSeatNumber() {
        return assignedSeat;
    }

    public void assignSeat(int seat) {
        if (seat > 0 && seat <= 32) {
            this.assignedSeat = seat;
        }
    }

    public void setTicket(String ticket) {
        this.ticketId = ticket;
    }

    public void clearTicket() {
        this.ticketId = null;
        this.assignedSeat = -1;
    }

    // --- Advanced Pricing Logic (Business Rules) ---
    public double calculatePrice(double basePrice) {
        double finalPrice = basePrice;

        // خصم الأطفال (أقل من 12 سنة) بنسبة 50%
        if (age < 12) {
            finalPrice *= 0.5;
        } // خصم الطلاب بنسبة 20%
        else if (isStudent) {
            finalPrice *= 0.8;
        }

        // خصم إضافي للمسافرين الدائمين
        if (travelHistory.size() > 5) {
            finalPrice -= 10.0; // خصم 10 ريال مكافأة
        }

        return Math.max(finalPrice, 10.0); // الحد الأدنى للسعر 10 ريال
    }

    // --- Ticket & Travel Interaction ---
    public boolean processPayment(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addToHistory(String tripDetails) {
        travelHistory.add(tripDetails + " on " + new Date().toString());
    }

    public void displayDashboard() {
        System.out.println("=== PASSENGER DASHBOARD ===");
        System.out.println("Welcome, " + name);
        System.out.println("Active Ticket: " + (ticketId != null ? ticketId : "None"));
        System.out.println("Assigned Seat: " + (assignedSeat != -1 ? assignedSeat : "N/A"));
        System.out.println("Wallet Balance: SAR " + balance);
        System.out.println("Past Trips: " + travelHistory.size());
        System.out.println("===========================");
    }

    // منطق البحث عن الرحلات (يستدعي من واجهة المستخدم)
    public List<String> filterTripsByDestination(List<String> allTrips, String destination) {
        return allTrips.stream()
                .filter(trip -> trip.toLowerCase().contains(destination.toLowerCase()))
                .collect(Collectors.toList());
    }

    // التحقق من صحة البيانات قبل الحفظ
    public boolean isValidProfile() {
        return name != null && name.length() > 2 && age > 0 && id > 0;
    }
}
