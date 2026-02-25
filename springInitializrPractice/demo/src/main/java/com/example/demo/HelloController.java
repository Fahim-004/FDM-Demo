package com.example.demo;
import java.util.HashMap;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/")
    public String home() {
        return "Spring Boot App is running!";
    }
    @GetMapping("/user")
    public Map<String, String> getUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Ravi");
        user.put("role", "Student");
        user.put("name", "uttam");
        user.put("role", "Trainer");
        return user;
    }
    @GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to SpringBoot!";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "Thankyou for visiting SpringBoot!";
	}
	@GetMapping("/plots")
    public List<Map<String, Object>> getPlots() {

        String sql = "SELECT plot_no, facing FROM plots";
        return jdbcTemplate.queryForList(sql);
    }
	@PostMapping("/saveplots")
	public String savePlot(
	@RequestParam String plotNo,
	@RequestParam String facing) {
	String sql = "INSERT INTO plots (plot_no, facing) VALUES (?,?)";
	jdbcTemplate.update(sql, plotNo, facing);
	return "redirect:/plots.html";
	}

}
