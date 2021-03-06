package DB;


public class MemberDTO  {
	String id;
	String name;
	String pwd;
	String adr;
	String cell;
	int lv;

	public MemberDTO(String id, String name, String pwd, String adr, String cell, int lv) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.adr = adr;
		this.cell = cell;
		this.lv = lv;
	}

	public MemberDTO(String id2, String pwd2) {
		this.id = id2;
		this.pwd = pwd2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public String toString() {
		return "(" + id + ", " + name + ", " + pwd + ", " + adr + ", " + cell + ", " + lv + ")";
	}

	public String toString2() {
		return "(" + id + ", " + pwd + ")";
	}
}
