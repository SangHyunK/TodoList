package todo.dao;

import java.util.HashMap;
import java.util.List;

import todo.vo.Todo;

public interface TodoDao {
  List<Todo> selectList() throws Exception;
  int insert(Todo todo) throws Exception;
  Todo selectOne(int tno) throws Exception;
  int update(Todo todo) throws Exception;
  int delete(int tno) throws Exception;
}
