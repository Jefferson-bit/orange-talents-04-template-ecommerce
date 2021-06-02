package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import br.co.zupacademy.jefferson.mercadolivre.categoria.Categoria;
import br.co.zupacademy.jefferson.mercadolivre.usuario.SenhaLimpa;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

public class ProdutoFactory {

	public static Produto criaProduto() {
		SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
		Usuario usuario = new Usuario("alex@gmail.com", senhaLimpa);
		Categoria categoria = new Categoria(1L ,"Celulares");
		
		List<CaracteristicaRequest> of = List.of(
						new CaracteristicaRequest("Textura", "Muito macio"),
						new CaracteristicaRequest("Modelo", "Afromeize"),
						new CaracteristicaRequest("Cor", "Azul"));
		Set<CaracteristicaRequest> caracteristicas = Set.copyOf(of);
		
		return new Produto.ProdutoBuilder().nome("SAMSUNG GALAXY").quantidade(2).descricao("Um item deveramente bom")
				.valor(new BigDecimal(3000.0)).categoria(categoria).usuario(usuario).caracteristicas(caracteristicas).build();
	}
	
	public static ProdutoRequest criaProdutoRequest() {
		Produto criaProduto = criaProduto();
		List<CaracteristicaProduto> caracteristicaProduto = List.copyOf(criaProduto.getCaracteristicas());
		return new ProdutoRequest(criaProduto, caracteristicaProduto);
	}
}






