package todo.controls;

import java.util.HashMap;
import java.util.Map;

import todo.annotation.Component;
import todo.dao.TodoDao;

@Component("/todo/list.do")
public class TodoListController implements Controller {
  TodoDao todoDao;
  
  public TodoListController setTodoDao(TodoDao todoDao) {
    this.todoDao = todoDao;
    return this;
  }

  @Override
  public String execute(Map<String, Object> model) throws Exception {
	HashMap<String, Object> paramMap = new HashMap<>();
    model.put("todoLists", todoDao.selectList());
    return "/todo/TodoList.jsp";
  }
}
