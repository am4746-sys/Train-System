/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
public class TrainSystemLogic {

    public double calculatePrice(String type, int age, boolean isStudent) {
        double base = 20.0;
        if (age < 10) {
            return 0.0;
        }
        if (type.equalsIgnoreCase("VIP")) {
            base = 50.0;
        }
        if (isStudent) {
            return base * 0.8;
        }
        return base;
    }

    public boolean checkTimeConflict(String time1, String time2) {
        // ظ…ظ†ط·ظ‚ ط¨ط³ظٹط· ظ„ظ„طھط­ظ‚ظ‚ ظ…ظ† ط§ظ„طھط¹ط§ط±ط¶ (ظٹظ…ظƒظ† طھط·ظˆظٹط±ظ‡ ظ„ظ…ظ‚ط§ط±ظ†ط© ط§ظ„ط³ط§ط¹ط§طھ)
        return time1.equals(time2);
    }

    public void printMaintenanceSchedule() {
        System.out.println("Maintenance Schedule: Weekly on Friday 02:00 AM");
    }

}
