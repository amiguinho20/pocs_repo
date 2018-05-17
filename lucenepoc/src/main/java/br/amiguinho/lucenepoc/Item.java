package br.amiguinho.lucenepoc;

public class Item implements Comparable<Item>{
	private String codigo;
	private String descricao;
	
	public Item(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", descricao=" + descricao + "]";
	}


	public int compareTo(Item item) {
		return this.getCodigo().compareTo(item.getCodigo());
	}
	
	
}
