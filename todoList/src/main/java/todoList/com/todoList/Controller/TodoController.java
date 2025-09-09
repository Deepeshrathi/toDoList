package todoList.com.todoList.Controller;

import com.example.todolist.model.TodoItem;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos") // Base path for all endpoints in this controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    // GET all tasks
    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.getAllTodoItems();
    }

    // GET a single task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        return todoService.getTodoItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new task
    @PostMapping
    public TodoItem createTodo(@RequestBody TodoItem todoItem) {
        return todoService.createTodoItem(todoItem);
    }

    // PUT (update) a task by ID
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id, @RequestBody TodoItem todoDetails) {
        return todoService.updateTodoItem(id, todoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoService.deleteTodoItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}