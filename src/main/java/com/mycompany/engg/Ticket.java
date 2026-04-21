/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Project: Train Reservation System Component: Ticket Model
 */
public class Ticket {

    private String ticketId;
    private String passengerType;
    private String seatNumber;
    private String fromStation;
    private String toStation;
    private String departureTime;
    private double price;
    private boolean isValid;
    private String securityHash;

    public Ticket(String id, String type, String seat, String from, String to, String time, double price) {
        this.ticketId = id;
        this.passengerType = type;
        this.seatNumber = seat;
        this.fromStation = from;
        this.toStation = to;
        this.departureTime = time;
        this.price = price;
        this.isValid = true;
        this.securityHash = UUID.randomUUID().toString().substring(0, 8);
    }

    // --- Getters ---
    public String getTicketId() {
        return ticketId;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    // --- Logic ---
    public void useTicket() {
        if (isValid) {
            this.isValid = false;
            System.out.println("Ticket " + ticketId + " has been scanned and used.");
        }
    }

    public String generateFullReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("********** TRAIN RECEIPT **********\n");
        receipt.append("TICKET ID: ").append(ticketId).append("\n");
        receipt.append("ROUTE: ").append(fromStation).append(" -> ").append(toStation).append("\n");
        receipt.append("SEAT: ").append(seatNumber).append(" | TYPE: ").append(passengerType).append("\n");
        receipt.append("TOTAL PAID: SAR ").append(String.format("%.2f", price)).append("\n");
        receipt.append("SECURITY CODE: ").append(securityHash).append("\n");
        receipt.append("***********************************");
        return receipt.toString();
    }

    public boolean isRefundable() {
        // التذاكر التي سعرها أكثر من 50 ريال قابلة للاسترجاع
        return price > 50.0 && isValid;
    }
}
