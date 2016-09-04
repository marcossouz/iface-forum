package iface_forum;
public class Comments extends Answers{

	int typeOfComment;//type = 0 -> Comment and type = 1 -> Answer of Comment
		
	public Comments(int id, int idArticle, int idForum, int user, int TYPEOFCOMMENT, String comment){
		super(id,idArticle,idForum,user,comment);
		this.typeOfComment = TYPEOFCOMMENT;
	}
	
	public void setTypeOfComment(int type){
		this.typeOfComment = type;
	}
	
	public int getTypeOfComment(){
		return this.typeOfComment;
	}
}
