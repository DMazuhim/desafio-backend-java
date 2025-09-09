package br.com.desafio.gestaoclientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.desafio.gestaoclientes.dto.ViaCepResponse;
import br.com.desafio.gestaoclientes.model.Cliente;
import br.com.desafio.gestaoclientes.model.Endereco;
import br.com.desafio.gestaoclientes.repository.ClienteRepository;
import br.com.desafio.gestaoclientes.service.exception.CpfJaCadastradoException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.restTemplate = restTemplate;
    }

    public Cliente salvarCliente(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistente.isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado no sistema.");
        }

        if (cliente.getEndereco() != null && cliente.getEndereco().getCep() != null) {
            String cep = cliente.getEndereco().getCep().replaceAll("[^0-9]", "");
            String viaCepUrl = "https://viacep.com.br/ws/" + cep + "/json/";

            try {
                ViaCepResponse viaCepResponse = restTemplate.getForObject(viaCepUrl, ViaCepResponse.class);

                if (viaCepResponse != null && viaCepResponse.getCep() != null) {
                    Endereco endereco = cliente.getEndereco();
                    endereco.setLogradouro(viaCepResponse.getLogradouro());
                    endereco.setBairro(viaCepResponse.getBairro());
                    endereco.setCidade(viaCepResponse.getLocalidade());
                    endereco.setUf(viaCepResponse.getUf());
                }
            } catch (Exception e) {
                System.err.println("Erro ao consultar o ViaCEP: " + e.getMessage());
            }
        }

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}