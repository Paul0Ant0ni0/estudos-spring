package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void populate(){
        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerenciar a empresa", 30000.0);
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerenciar um setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico Geral", "Resolve os chamados urgentes", 12000.0);

        // Integer id, String nome, String email, String cpf, String senha, String foto, Cargo cargo
        Funcionario f1 = new Funcionario(null, "Renato Pereira", "renatopereira@gmail.com",
                "25206783284", this.encoder.encode("12345"), null, c1);
        f1.setPerfil(Perfil.ADMIN); // Perfil aministrador para gerenciar a aplicação
        Funcionario f2 = new Funcionario(null, "Victor Icoma", "victoricoma@gmail.com",
                "51123497325", this.encoder.encode("88484"), null, c2);

        Cliente cliente1 = new Cliente(null, "Emanuelly Allana Lavínia Rocha", "emanuelly_rocha@yohoo.com.br",
                "52908366673", this.encoder.encode("98InWpjGnt"), "8428291599");

        Cliente cliente2 = new Cliente(null, "Marina Maria Costa", "marina_costa@jsagromecanica.com.br",
                "29773751201", this.encoder.encode("cPB16aJCDG"), "8125469575");

        Chamado ch1 = new Chamado(null, "Primeiro chamado do sistema", "Revisar as entidades criadas");
        ch1.setCliente(cliente1);
        Chamado ch2 = new Chamado(null, "Ativar VPN do sistema", "Conectar aos servidores remotos");
        ch2.setCliente(cliente2);
        ch2.setFuncionario(f1);
        ch2.setStatus(StatusChamado.ATRIBUIDO);

        // vamos persistir as entidades = salvar no banco
        this.cargoRepository.saveAll(List.of(c1, c2, c3)); // INSERT INTO
        this.funcionarioRepository.saveAll(List.of(f1, f2));
        this.clienteRepository.saveAll(List.of(cliente1, cliente2));
        this.chamadoRepository.saveAll(List.of(ch1, ch2));

    }


}

// O objetivo desta classe é inserir no banco, dados ficticios (de teste)
// OIC = Inversion of Control = Inversão de Controle
// Container = é o local onde o Spring guarda os objetos anotados
// @Service = indica que uma classe é um serviço
// Injeção de Dependências = quando o Spring injeta os objetos na classe