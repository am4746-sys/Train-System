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
 * Project: Train Reservation System Component: System Manager (Administrator
 * Level) Logic: Binary Search Tree (BST) for Staff & Advanced Financial
 * Analytics
 */
public class Manager {

    // Inner class for Binary Search Tree nodes to manage Employees
    private class EmployeeNode {

        Employee employee;
        EmployeeNode left;
        EmployeeNode right;

        public EmployeeNode(Employee employee) {
            this.employee = employee;
            this.left = null;
            this.right = null;
        }
    }

    private EmployeeNode root;
    private List<String> trainSchedules;
    private Map<String, Double> revenueRecords;
    private List<String> systemActivityLogs;
    private final String managerRole = "TOP_LEVEL_ADMIN";

    public Manager() {
        this.root = null;
        this.trainSchedules = new ArrayList<>();
        this.revenueRecords = new LinkedHashMap<>(); // To keep insertion order for reports
        this.systemActivityLogs = new ArrayList<>();

        // Log system initialization
        addLog("System Manager Initialized. Role: " + managerRole);
        initializeDefaultSystemData();
    }

    // --- System Initialization ---
    private void initializeDefaultSystemData() {
        // Adding the required 6 employees into the BST structure
        insertEmployee(new Employee("Ali Hassan", 101, "Main Riyadh Station"));
        insertEmployee(new Employee("Sara Ahmed", 102, "North Plaza Station"));
        insertEmployee(new Employee("Omar Khaled", 103, "East Coast Terminal"));
        insertEmployee(new Employee("Lina Saad", 104, "Airport Link Hub"));
        insertEmployee(new Employee("Fahad Ali", 105, "Central Business District"));
        insertEmployee(new Employee("Nora Salem", 106, "Western Junction"));

        // Seed some initial schedules
        addTrainSchedule("Riyadh -> Dammam | 08:00 AM");
        addTrainSchedule("Jeddah -> Makkah | 10:30 AM");

        // Seed initial revenue for analytics demonstration
        recordRevenue("2026-04-01", 15200.50);
        recordRevenue("2026-04-02", 18400.75);
        addLog("Default system data successfully seeded.");
    }

    // --- Employee Management (BST Logic) ---
    public void insertEmployee(Employee employee) {
        if (employee == null) {
            addLog("ERROR: Attempted to insert null employee object.");
            return;
        }
        root = insertRecursive(root, employee);
        addLog("Employee Added: " + employee.getName() + " (ID: " + employee.getId() + ")");
    }

    private EmployeeNode insertRecursive(EmployeeNode current, Employee employee) {
        if (current == null) {
            return new EmployeeNode(employee);
        }

        if (employee.getId() < current.employee.getId()) {
            current.left = insertRecursive(current.left, employee);
        } else if (employee.getId() > current.employee.getId()) {
            current.right = insertRecursive(current.right, employee);
        } else {
            // ID already exists - system logic to handle duplicates
            addLog("WARNING: Duplicate Employee ID detected: " + employee.getId());
        }
        return current;
    }

    public List<Employee> getInOrderEmployees() {
        List<Employee> list = new ArrayList<>();
        traverseInOrder(root, list);
        return list;
    }

    private void traverseInOrder(EmployeeNode node, List<Employee> list) {
        if (node != null) {
            traverseInOrder(node.left, list);
            list.add(node.employee);
            traverseInOrder(node.right, list);
        }
    }

    // --- Schedule & Fleet Management ---
    public boolean addTrainSchedule(String schedule) {
        if (schedule == null || schedule.isEmpty()) {
            addLog("ERROR: Invalid schedule format.");
            return false;
        }

        if (detectScheduleConflict(schedule)) {
            addLog("CONFLICT: Schedule already exists for: " + schedule);
            return false;
        }

        trainSchedules.add(schedule);
        addLog("New Schedule Added: " + schedule);
        return true;
    }

    private boolean detectScheduleConflict(String newSchedule) {
        return trainSchedules.contains(newSchedule);
    }

    public List<String> getFilteredSchedules(String keyword) {
        return trainSchedules.stream()
                .filter(s -> s.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // --- Financial Analytics & Reporting ---
    public void recordRevenue(String date, double amount) {
        if (amount < 0) {
            addLog("SECURITY: Attempted to record negative revenue on " + date);
            return;
        }
        revenueRecords.put(date, revenueRecords.getOrDefault(date, 0.0) + amount);
        addLog("Revenue Recorded: SAR " + amount + " for date " + date);
    }

    public double calculateTotalAnnualRevenue() {
        double total = 0;
        for (double dailyAmount : revenueRecords.values()) {
            total += dailyAmount;
        }
        return total;
    }

    public Map<String, Double> getRevenueSummaryReport() {
        addLog("Manager generated a full financial report.");
        return Collections.unmodifiableMap(revenueRecords);
    }

    // --- System Logging & Maintenance ---
    private void addLog(String message) {
        String timestamp = LocalDateTime.now().toString();
        systemActivityLogs.add("[" + timestamp + "] " + message);

        // Maintain log size
        if (systemActivityLogs.size() > 500) {
            systemActivityLogs.remove(0);
        }
    }

    public void printFullSystemStatus() {
        System.out.println("========== SYSTEM STATUS REPORT ==========");
        System.out.println("Manager Role: " + managerRole);
        System.out.println("Total Employees Managed: " + getInOrderEmployees().size());
        System.out.println("Total Active Routes: " + trainSchedules.size());
        System.out.println("Current Total Revenue: SAR " + calculateTotalAnnualRevenue());
        System.out.println("System Health: OPTIMAL");
        System.out.println("==========================================");
    }

    public List<String> getSystemLogs() {
        return new ArrayList<>(systemActivityLogs);
    }

    // --- Data Validation Utilities ---
    public boolean validateEmployeeAccess(int id) {
        return searchEmployeeById(root, id) != null;
    }

    private Employee searchEmployeeById(EmployeeNode node, int id) {
        if (node == null || node.employee.getId() == id) {
            return (node != null) ? node.employee : null;
        }
        if (id < node.employee.getId()) {
            return searchEmployeeById(node.left, id);
        }
        return searchEmployeeById(node.right, id);
    }

}
