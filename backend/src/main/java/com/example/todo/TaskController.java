package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @PostMapping
    public Task create(@RequestBody Task task) {
        return repository.save(task);
    }

    @GetMapping
    public List<Task> list() {
        return repository.findTop5ByCompletedFalseOrderByCreatedAtDesc();
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<?> markDone(@PathVariable Long id) {
        Optional<Task> optional = repository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.setCompleted(true);
            repository.save(task);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

