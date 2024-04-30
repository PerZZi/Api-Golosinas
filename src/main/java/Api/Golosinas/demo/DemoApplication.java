package Api.Golosinas.demo;
import Api.Golosinas.demo.Repositories.GolosinaRepository;
import Api.Golosinas.demo.Repositories.PedidoRepository;
import Api.Golosinas.demo.Repositories.PedidoUsuarioRespotory;
import Api.Golosinas.demo.Repositories.UsuarioRepository;
import Api.Golosinas.demo.entidades.*;
import Api.Golosinas.demo.entidades.enums.EstadoPedido;
import Api.Golosinas.demo.entidades.enums.Rol;
import Api.Golosinas.demo.entidades.enums.TipoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class,args);}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(PedidoRepository pedidoRepository, PedidoUsuarioRespotory pedidoUsuarioRespotory, GolosinaRepository golosinaRepository, UsuarioRepository usuarioRepository){
		return args -> {

			Golosina golosina1 = new Golosina("Chocolate", 500.00, "Chocolate Blanco", 200);
			Golosina golosina2 = new Golosina("Chupetín", 100.00, "Sabor Frutilla", 400);
			Golosina golosina3 = new Golosina("Gomitas", 150.00, "Hechas con frutas", 170);


			Usuario fercho = new Usuario("Fernando", "fer.garcia@gmail.com", passwordEncoder.encode("Ferri"), Rol.CLIENTE);
			Usuario Emi = new Usuario("Emiliano", "emiliano@gmail.com", passwordEncoder.encode("Emi1"), Rol.RESPONSABLE);


			List<Golosina> listaGolosinas = new ArrayList<>();
			listaGolosinas.add(golosina1);
			listaGolosinas.add(golosina3);

			Pedido pedido1 = new Pedido(TipoPedido.MINORISTA, new ArrayList<>(), listaGolosinas, EstadoPedido.BORRADOR, LocalDate.now());
			PedidoUsuario pedidoUsuario = new PedidoUsuario();
			pedidoUsuario.setUsuario(fercho);
			pedidoUsuario.setPedido(pedido1);

			fercho.agregarPedidoUsuario(pedidoUsuario);

			golosinaRepository.save(golosina1);
			golosinaRepository.save(golosina2);
			golosinaRepository.save(golosina3);
			usuarioRepository.save(fercho);
			usuarioRepository.save(Emi);
			pedidoRepository.save(pedido1);
			pedidoUsuarioRespotory.save(pedidoUsuario);

			System.out.println(fercho);
			System.out.println(Emi);
			System.out.println(golosina1);
			System.out.println(pedido1);
			System.out.println(pedidoUsuario);

			pedido1.enviarAprobacion();
			pedidoRepository.save(pedido1);


			pedido1.aprobar(Emi);
			pedidoRepository.save(pedido1);

			System.out.println("Pedido después de la aprobación por Emi: " + pedido1);
		};
 	}

}

