package todo.controls;

import java.util.Map;

import todo.annotation.Component;
import todo.bind.DataBinding;
import todo.dao.TodoDao;
import todo.vo.Todo;

@Component("/todo/add.do")
public class TodoAddController implements Controller, DataBinding {
  TodoDao todoDao;
  
  public TodoAddController setTodoDao(TodoDao todoDao) {
    this.todoDao = todoDao;
    return this;
  }
  
  public Object[] getDataBinders() {
    return new Object[]{
        "todo", todo.vo.Todo.class
    };
  }
  
  @Override
  public String execute(Map<String, Object> model) throws Exception {
    Todo todo = (Todo)model.get("todo");
    if (todo.getTitle() == null) {
      return "/todo/TodoForm.jsp";
      
    } else {
      todoDao.insert(todo);
      return "redirect:list.do";
    }
  }
}
