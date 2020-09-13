package br.com.webscraping.b3.api;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.webscraping.b3.domain.AtivoBovespaRepository;
import br.com.webscraping.b3.model.AtivoBovespa;


@RestController
@RequestMapping(value = "/scraping", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebScrappingB3Controller {
	
	@Autowired
	private WebDriver driver;
	
	@Autowired
	private AtivoBovespaRepository ativoBovespaRepository;
	
	@GetMapping
	public void list() {
		driver.get("http://www.b3.com.br/pt_br/market-data-e-indices/indices/indices-amplos/indice-ibovespa-ibovespa-composicao-da-carteira.htm");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bvmf_iframe")));
		
		WebElement iframe = driver.findElement(By.cssSelector("#bvmf_iframe"));
		driver.switchTo().frame(iframe);
		WebElement tabelaCotacaoBovespa = driver.findElement(By.id("ctl00_contentPlaceHolderConteudo_grdResumoCarteiraTeorica_ctl00"));
		
		WebElement tBodyCotacaoIbovespa = tabelaCotacaoBovespa.findElement(By.tagName("tbody"));
		
		List<WebElement> trsCotacaoIbovespa = tBodyCotacaoIbovespa.findElements(By.tagName("tr"));
		
		for (WebElement element : trsCotacaoIbovespa) {
            List<WebElement> dadosEmpresas = element.findElements(By.tagName("td"));
            
            for(WebElement dado: dadosEmpresas) {
            	System.out.println(dado.getText());
            }
        }
	}
	
	//TODO: Criar ENUM com strings e rodar um outro método em todos os índices para importar todos os ativos da b3.
	@GetMapping("/indice/{indice}")
	public ArrayList<AtivoBovespa> importarPorIndice(@PathVariable String indice) {
		ArrayList<AtivoBovespa> ativos = new ArrayList<>();

		driver.get(String.format("http://bvmf.bmfbovespa.com.br/indices/ResumoCarteiraTeorica.aspx?Indice=%s&idioma=pt-br", indice));
		WebElement tabelaCotacaoBovespa = driver.findElement(By.id("ctl00_contentPlaceHolderConteudo_grdResumoCarteiraTeorica_ctl00"));
		
		WebElement tBodyCotacaoIbovespa = tabelaCotacaoBovespa.findElement(By.tagName("tbody"));
		
		List<WebElement> trsCotacaoIbovespa = tBodyCotacaoIbovespa.findElements(By.tagName("tr"));
		
		for (WebElement element : trsCotacaoIbovespa) {
            List<WebElement> dadosEmpresas = element.findElements(By.tagName("td"));
            
            int atual = 0;
        	AtivoBovespa ativo = new AtivoBovespa();
            for(WebElement dado: dadosEmpresas) {
            	ativo.setPorCodigo(atual, dado.getText());
            	ativos.add(ativo);
            	atual++;
            }
            ativoBovespaRepository.save(ativo);
        }
		
		return ativos;
		
	}
}
