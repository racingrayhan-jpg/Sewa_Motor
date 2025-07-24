package com.rentalmotor.controller;

import com.rentalmotor.model.Motor;
import com.rentalmotor.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motor")
public class MotorController {

    @Autowired
    private MotorRepository motorRepository;

    @GetMapping
    public String listMotor(Model model) {
        model.addAttribute("motorList", motorRepository.findAll());
        return "motor-list";
    }

    @GetMapping("/add")
    public String addMotorForm(Model model) {
        model.addAttribute("motor", new Motor());
        return "motor-form";
    }

    @PostMapping("/save")
    public String saveMotor(@ModelAttribute Motor motor) {
        // Periksa apakah ID sudah ada
        if (motor.getId() != null && motorRepository.existsById(motor.getId())) {
            // Update data yang ada
            Motor existingMotor = motorRepository.findById(motor.getId()).orElse(null);
            if (existingMotor != null) {
                existingMotor.setMerk(motor.getMerk());
                existingMotor.setJenis(motor.getJenis());
                existingMotor.setTahun(motor.getTahun());
                existingMotor.setHargaSewa(motor.getHargaSewa());
                motorRepository.save(existingMotor);
            }
        } else {
            // Tambahkan data baru
            motorRepository.save(motor);
        }
        return "redirect:/motor";
    }

    @GetMapping("/edit/{id}")
    public String editMotorForm(@PathVariable Long id, Model model) {
        model.addAttribute("motor", motorRepository.findById(id).orElse(null));
        return "motor-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMotor(@PathVariable Long id) {
        motorRepository.deleteById(id);
        return "redirect:/motor";
    }
}