package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.dto.ClientDTO;

@Mapper
public interface ClientMapper {
	@Mapping(source ="documentType.dotyId" ,target = "dotyId")
	ClientDTO toClientDTO(Client client);
	@Mapping(target = "documentType.dotyId", source ="dotyId")
	Client toClient(ClientDTO clientDTO);
	
	List<ClientDTO> clientDTOs(List<Client> clients);
	
	List<Client> clients(List<ClientDTO> clientDTOs);
}
