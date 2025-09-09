package todoList.com.todoList.Service;

import com.example.todolist.model.TodoItem;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoItem> getAllTodoItems() {
        return todoRepository.findAll();
    }

    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoRepository.findById(id);
    }

    public TodoItem createTodoItem(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    public Optional<TodoItem> updateTodoItem(Long id, TodoItem todoDetails) {
        return todoRepository.findById(id).map(existingItem -> {
            existingItem.setTitle(todoDetails.getTitle());
            existingItem.setCompleted(todoDetails.isCompleted());
            return todoRepository.save(existingItem);
        });
    }

    public boolean deleteTodoItem(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}