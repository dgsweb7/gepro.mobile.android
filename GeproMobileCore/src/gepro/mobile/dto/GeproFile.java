package gepro.mobile.dto;

public class GeproFile {

	String nome;
	String PastaID;
	byte[] fileContent;
	String descricao;
	String palaavraChave;
	int Tipo;
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPalaavraChave() {
		return palaavraChave;
	}

	public void setPalaavraChave(String palaavraChave) {
		this.palaavraChave = palaavraChave;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		nome = name;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public String getPastaID() {
		return PastaID;
	}

	public void setPastaID(String pastaID) {
		PastaID = pastaID;
	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		Tipo = tipo;
	}
}
