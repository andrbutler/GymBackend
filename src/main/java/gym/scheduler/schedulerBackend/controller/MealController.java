/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.Exercise;
import gym.scheduler.schedulerBackend.model.entity.Meal;
import gym.scheduler.schedulerBackend.model.repository.MealRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author andrb
 */
@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    MealRepository mealRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/all")
    public List<Meal> retrieveAllMeals() {
        return mealRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public Meal retrieveById(@PathVariable(name = "id") int id) {
        return mealRepository.findByMealId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addMeal(@RequestBody Meal request) {
        if (mealRepository.findByMealName(request.getMealName()).isPresent()) {
            return new ResponseEntity(new ApiResponse(false, "meal " + request.getMealName() + " already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Meal m = new Meal(request.getMealName(), request.getRecipeLink(), request.getCalories());

        Meal result = mealRepository.save(m);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/meals/{mealname}")
                .buildAndExpand(result.getMealName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "meal added successfully"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> Meal(@PathVariable(name = "id") int id, @RequestBody Meal request) {

        Meal m = mealRepository.findByMealId(id);

        if (mealRepository.findByMealName(request.getMealName()).isPresent()
                && !(request.getMealName().equals(m.getMealName()))) {
            return new ResponseEntity(new ApiResponse(false, "meal " + request.getMealName() + " already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        m.setMealName(request.getMealName());
        m.setRecipeLink(request.getRecipeLink());
        m.setCalories(request.getCalories());

        Meal result = mealRepository.save(m);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/meals/{mealname}")
                .buildAndExpand(result.getMealName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "meal updated successfully"));
    }

}
