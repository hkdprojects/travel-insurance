package com.example.travelinsurance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.travelinsurance.model.CoverageType;
import com.example.travelinsurance.repository.CoverageTypeRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CoverageTypeRepository coverageTypeRepository;

    @GetMapping("/coverages")
    public String listCoverages(Model model) {
        model.addAttribute("coverages", coverageTypeRepository.findAll());
        return "admin/coverages";
    }

    @GetMapping("/coverages/add")
    public String showAddForm(Model model) {
        model.addAttribute("coverage", new CoverageType());
        return "admin/coverage-form";
    }
    
    @PostMapping("/coverages/save")
    public String saveCoverage(@ModelAttribute CoverageType coverage) {
        coverageTypeRepository.save(coverage);
        return "redirect:/admin/coverages";
    }

    @GetMapping("/coverages/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CoverageType coverage = coverageTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coverage Id:" + id));
        model.addAttribute("coverage", coverage);
        return "admin/coverage-form";
    }

    @GetMapping("/coverages/delete/{id}")
    public String deleteCoverage(@PathVariable Long id) {
        coverageTypeRepository.deleteById(id);
        return "redirect:/admin/coverages";
    }
}