package todo.controls;

import java.util.Map;

import todo.annotation.Component;
import todo.bind.DataBinding;
import todo.dao.TodoDao;

@Component("/todo/delete.do")
public class TodoDeleteController implements Controller, DataBinding {
  TodoDao todoDao;
  
  public TodoDeleteController setTodoDao(TodoDao todoDao) {
    this.todoDao = todoDao;
    return this;
  }
  
  public Object[] getDataBinders() {
    return new Object[]{
        "no", Integer.class
    };
  }
  
  @Override
  public String execute(Map<String, Object> model) throws Exception {
    Integer no = (Integer)model.get("no");
    todoDao.delete(no);
    
    return "redirect:list.do";
  }
}
