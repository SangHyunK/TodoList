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
    Todo todo = (Todo)model.get("project");
    
    if (project.getTitle() == null) { 
      Integer no = (Integer)model.get("no");
      Project detailInfo = projectDao.selectOne(no);
      model.put("project", detailInfo);
      return "/project/ProjectUpdateForm.jsp";

    } else { 
      projectDao.update(project);
      return "redirect:list.do";
    }
  }
}
