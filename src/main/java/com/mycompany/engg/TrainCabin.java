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

/**
 * Project: Train Reservation System Component: Train Cabin (Visual Mapping)
 * Logic: Seat Matrix Management (32 Seats)
 */
public class TrainCabin {

    private final int MAX_CAPACITY = 32;
    private Map<Integer, String> seatMap;
    private String cabinClass; // First Class, Economy

    // الألوان المتوافقة مع متطلبات التصميم
    public static final String STATUS_AVAILABLE = "BEIGE";
    public static final String STATUS_RESERVED = "BURGUNDY";
    public static final String STATUS_LOCKED = "GRAY";

    public TrainCabin(String cabinClass) {
        this.cabinClass = cabinClass;
        this.seatMap = new HashMap<>();
        initializeCabinLayout();
    }

    private void initializeCabinLayout() {
        for (int i = 1; i <= MAX_CAPACITY; i++) {
            seatMap.put(i, STATUS_AVAILABLE);
        }
    }

    // --- Seat Operations ---
    public boolean isSeatAvailable(int seatNum) {
        validateSeatNumber(seatNum);
        return seatMap.get(seatNum).equals(STATUS_AVAILABLE);
    }

    public void reserveSeat(int seatNum) {
        validateSeatNumber(seatNum);
        if (isSeatAvailable(seatNum)) {
            seatMap.put(seatNum, STATUS_RESERVED);
        } else {
            throw new IllegalStateException("Seat " + seatNum + " is not available for booking.");
        }
    }

    public void cancelSeat(int seatNum) {
        validateSeatNumber(seatNum);
        if (seatMap.get(seatNum).equals(STATUS_RESERVED)) {
            seatMap.put(seatNum, STATUS_AVAILABLE);
        }
    }

    // --- Grid Logic for HTML Display ---
    public Map<Integer, String> getCabinLayout() {
        return Collections.unmodifiableMap(seatMap);
    }

    public List<Integer> getAvailableSeatList() {
        List<Integer> available = new ArrayList<>();
        seatMap.forEach((num, status) -> {
            if (status.equals(STATUS_AVAILABLE)) {
                available.add(num);
            }
        });
        return available;
    }

    // --- Analytics for Manager ---
    public double calculateOccupancyRate() {
        long reservedCount = seatMap.values().stream()
                .filter(status -> status.equals(STATUS_RESERVED))
                .count();
        return (double) reservedCount / MAX_CAPACITY * 100;
    }

    public void maintenanceLock() {
        // قفل الكابينة بالكامل للصيانة
        for (int i = 1; i <= MAX_CAPACITY; i++) {
            if (seatMap.get(i).equals(STATUS_AVAILABLE)) {
                seatMap.put(i, STATUS_LOCKED);
            }
        }
    }

    private void validateSeatNumber(int n) {
        if (n < 1 || n > MAX_CAPACITY) {
            throw new IllegalArgumentException("Seat number must be between 1 and 32.");
        }
    }

    public String getCabinSummary() {
        return String.format("Cabin [%s]: %d/%d Seats Occupied (%.1f%%)",
                cabinClass,
                MAX_CAPACITY - getAvailableSeatList().size(),
                MAX_CAPACITY,
                calculateOccupancyRate());
    }
}
