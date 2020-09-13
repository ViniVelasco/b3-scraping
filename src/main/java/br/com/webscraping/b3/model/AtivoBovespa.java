package br.com.webscraping.b3.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AtivoBovespa {
	
	@Id
	private String codigo;
	private String acao;
	private String tipo;
	private BigDecimal quantidadeTeorica;
	private BigDecimal participacao;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getQuantidadeTeorica() {
		return quantidadeTeorica;
	}
	public void setQuantidadeTeorica(BigDecimal quantidadeTeorica) {
		this.quantidadeTeorica = quantidadeTeorica;
	}
	public BigDecimal getParticipacao() {
		return participacao;
	}
	public void setParticipacao(BigDecimal participacao) {
		this.participacao = participacao;
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
		AtivoBovespa other = (AtivoBovespa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	public void setPorCodigo(Integer codigo, String valor)  {
		if(codigo == 0) {
			this.codigo = valor;
			return;
		}
		
		if(codigo == 1) {
			this.acao = valor;
			return;
		}
		
		if(codigo == 2) {
			this.tipo = valor;
			return;
		}
		
		if(codigo == 3) {
			this.quantidadeTeorica = recuperaString(valor);
			return;
		}
		
		if(codigo == 4) {
			this.participacao = recuperaString(valor);
			return;
		}
	}
	
	public BigDecimal recuperaString(String str)
	{
	        str = str.replace(".","");
	        str = str.replace(",", ".");
	        str = str.trim();

	        return new BigDecimal(str);
	}
	
	

}
