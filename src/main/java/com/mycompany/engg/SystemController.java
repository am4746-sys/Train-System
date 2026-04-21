/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.engg;

/**
 *
 * @author rawan
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // صفحة الموظف (دعم المحطة)
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // 🔥 المسار الجديد: صفحة العميل (بوابة المسافر)
    @GetMapping("/customer")
    public String customer() {
        // ملاحظة هامة: إذا كان اسم الملف في مجلد templates هو Customer.html (بحرف كبير)، 
        // يجب أن يكون الـ return "Customer" لأن الجافا حساسة لحالة الأحرف.
        return "Customer";
    }

    @GetMapping("/scheduling")
    public String scheduling() {
        return "scheduling";
    }

    @GetMapping("/analytics")
    public String analytics() {
        return "analytics";
    }
}
