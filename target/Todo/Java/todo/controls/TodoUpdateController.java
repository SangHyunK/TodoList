package todo.controls;

import java.util.Map;

import todo.annotation.Component;
import todo.bind.DataBinding;
import todo.dao.TodoDao;
import todo.vo.Todo;

@Component("/todo/update.do")
public class TodoUpdateController implements Controller, DataBinding {
  TodoDao todoDao;

  public TodoUpdateController setTodoDao(TodoDao todoDao) {
    this.todoDao = todoDao;
    return this;
  }
  
  public Object[] getDataBinders() {
    return new Object[]{
        "no", Integer.class,
        "todo", todo.vo.Todo.class
    };
  }
  
  @Override
  public String execute(Map<String, Object> model) throws Exception {
    Todo todo = (Todo)model.get("todo");
    
    if (todo.getTitle() == null) {
      Integer no = (Integer)model.get("no");
      Todo detailInfo = todoDao.selectOne(no);
      model.put("todo", detailInfo);
      return "/todo/TodoUpdateForm.jsp";

    } else {
      todoDao.update(todo);
      return "redirect:list.do";
    }
  }
}
