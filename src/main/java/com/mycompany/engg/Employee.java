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
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Project: Train Reservation System Component: Employee (Station Staff) Logic:
 * Passenger Management & Ticket Issuance
 */
public class Employee {

    private String name;
    private int id;
    private String assignedStation;
    private List<Passenger> registeredPassengers;
    private Map<Integer, Ticket> activeTickets;
    private List<String> operationLogs;
    private boolean isOnShift;

    public Employee(String name, int id, String station) {
        this.name = name;
        this.id = id;
        this.assignedStation = station;
        this.registeredPassengers = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.operationLogs = new ArrayList<>();
        this.isOnShift = true;
        logAction("Staff profile initialized at station: " + station);
    }

    // --- Basic Getters and Setters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStation() {
        return assignedStation;
    }

    public boolean isShiftActive() {
        return isOnShift;
    }

    public void setShiftStatus(boolean status) {
        this.isOnShift = status;
    }

    // --- Passenger Registration & Management ---
    public boolean registerNewPassenger(Passenger p) {
        if (p == null) {
            logAction("FAILED: Attempted to register a null passenger.");
            return false;
        }

        // التحقق من عدم تكرار الهوية
        boolean exists = registeredPassengers.stream().anyMatch(existing -> existing.getId() == p.getId());
        if (exists) {
            logAction("REGISTRATION ERROR: ID " + p.getId() + " already exists.");
            return false;
        }

        registeredPassengers.add(p);
        logAction("Passenger Registered: " + p.getName() + " (ID: " + p.getId() + ")");
        return true;
    }

    public List<Passenger> searchPassengersByName(String query) {
        return registeredPassengers.stream()
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // --- Ticket Operations (The core of the system) ---
    public String issueTicket(Passenger passenger, TrainCabin cabin, int seatNum, String from, String to) {
        if (!isOnShift) {
            logAction("SECURITY: Blocked ticket issuance. Employee is OFF-SHIFT.");
            return "ERROR: Staff not on duty.";
        }

        try {
            // التحقق من توفر المقعد في الكابينة
            if (cabin.isSeatAvailable(seatNum)) {
                cabin.reserveSeat(seatNum);

                // حساب السعر بناءً على بيانات الراكب
                double finalPrice = passenger.calculatePrice(120.0); // 120 is base price

                String ticketCode = "TKT-" + id + "-" + System.currentTimeMillis() % 10000;
                Ticket newTicket = new Ticket(ticketCode, "Standard", String.valueOf(seatNum), from, to, "10:00 AM", finalPrice);

                passenger.assignSeat(seatNum);
                passenger.setTicket(ticketCode);

                activeTickets.put(passenger.getId(), newTicket);
                logAction("TICKET ISSUED: " + ticketCode + " for Passenger: " + passenger.getName());
                return ticketCode;
            } else {
                logAction("BOOKING FAILED: Seat " + seatNum + " is already occupied.");
                return "ERROR: Seat Taken.";
            }
        } catch (Exception e) {
            logAction("CRITICAL ERROR during issuance: " + e.getMessage());
            return "ERROR: System Failure.";
        }
    }

    public boolean voidTicket(int passengerId, TrainCabin cabin) {
        if (activeTickets.containsKey(passengerId)) {
            Ticket t = activeTickets.get(passengerId);
            int seat = Integer.parseInt(t.getSeatNumber());

            cabin.cancelSeat(seat);
            activeTickets.remove(passengerId);
            logAction("TICKET VOIDED: Passenger ID " + passengerId + " seat " + seat + " released.");
            return true;
        }
        return false;
    }

    // --- Shift & Reporting ---
    public void generateEndOfShiftReport() {
        System.out.println("---------- SHIFT REPORT: " + name + " ----------");
        System.out.println("Station: " + assignedStation);
        System.out.println("Total Tickets Issued: " + activeTickets.size());
        System.out.println("Total Revenue Handled: SAR "
                + activeTickets.values().stream().mapToDouble(Ticket::getPrice).sum());
        System.out.println("----------------------------------------------");
    }

    private void logAction(String action) {
        String entry = "[" + LocalDateTime.now() + "] " + action;
        operationLogs.add(entry);
    }

    public List<String> getRecentLogs() {
        return operationLogs.subList(Math.max(operationLogs.size() - 10, 0), operationLogs.size());
    }

    @Override
    public String toString() {
        return "Employee Profile [ID=" + id + ", Name=" + name + ", Station=" + assignedStation + "]";
    }

}
