package todo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import todo.annotation.Component;
import todo.vo.Todo;

@Component("todoDao")
public class MySqlTodoDao implements TodoDao {
  SqlSessionFactory sqlSessionFactory;

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	  this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Todo> selectList() throws Exception {
	  SqlSession sqlSession = sqlSessionFactory.openSession();
	  
	  try {
		  return sqlSession.selectList("todo.dao.TodoDao.selectList");
	  } finally {
		  sqlSession.close();
	  }
  }

  public int insert(Todo todo) throws Exception  {
	 SqlSession sqlSession = sqlSessionFactory.openSession();
	 
	 try {
		int count = sqlSession.insert("todo.dao.TodoDao.insert", todo);
		sqlSession.commit();
		
		return count;
	 } finally {
		 sqlSession.close();
	 }
  }
  
  public Todo selectOne(int tno) throws Exception {
	  SqlSession sqlSession = sqlSessionFactory.openSession();
	  
	  try {
		  return sqlSession.selectOne("todo.dao.TodoDao.selectOne", tno);
	  } finally {
		  sqlSession.close();
	  }
  }

  public int update(Todo todo) throws Exception {
	  SqlSession sqlSession = sqlSessionFactory.openSession();
	  
	  try {
		  Todo original = sqlSession.selectOne("todo.dao.TodoDao.selectOne", todo.getTno());
		  HashMap<String, Object> paramMap = new HashMap<String, Object>();
		  
		  if(!todo.getTitle().equals(original.getTitle()))
			  paramMap.put("title", todo.getTitle());
		  
		  if(!todo.getContent().equals(original.getContent()))
			  paramMap.put("content", todo.getContent());
		  
		  if(todo.getPriority() != original.getPriority())
			  paramMap.put("todo", todo.getPriority());

		  if(todo.getPer() != original.getPer())
			  paramMap.put("tags", todo.getPer());
		  
		  // 수정 된 것이 있다면
		  if(paramMap.size() > 0) {
			  paramMap.put("no", todo.getTno());
			  int count = sqlSession.update("todo.dao.TodoDao.update", paramMap);
			  sqlSession.commit();
			  
			  return count;
		  } else {
			  return 0;
		  }
	  } finally {
		  sqlSession.close();
	  }
  }  

  public int delete(int tno) throws Exception {
	  SqlSession sqlSession = sqlSessionFactory.openSession();
	  
	  try {
		  int count = sqlSession.delete("todo.dao.TodoDao.delete", tno);
		  sqlSession.commit();
		  
		  return count;
	  } finally {
		  sqlSession.close();
	  }
  }
   
}
