package todo.vo;

import java.util.Date;

public class Todo {
	protected int 		tno;
	protected String 	title;
	protected String 	content;
	protected int 		priority;
	protected int		per;

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}
}
