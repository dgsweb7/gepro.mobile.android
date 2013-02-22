package gepro.mobile.android.holder;

public class MenuEntry {
	
	int id;
	int ImageId;
	String Nome;
	String Descricao;
	
	public MenuEntry()
	{
		
		
	}

	public MenuEntry(int id, int image_id, String nome, String descricao ){
		this.id = id;
		this.ImageId = image_id;
		this.Nome = nome;
		this.Descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImageId() {
		return ImageId;
	}
	public void setImageId(int imageId) {
		ImageId = imageId;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	
}
