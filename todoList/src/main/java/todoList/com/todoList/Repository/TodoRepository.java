package todoList.com.todoList.Repository;

import com.example.todolist.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository<TodoItem> extends JpaRepository<TodoItem, Long> {
}