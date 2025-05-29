package br.com.cotiinformatica.configurations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.dtos.TarefaRequestDTO;
import br.com.cotiinformatica.dtos.TarefaResponseDTO;
import br.com.cotiinformatica.entities.Tarefa;

@Configuration
public class ModelMapperConfiguration {

	@Bean
    public ModelMapper modelMapper() {
		
        ModelMapper modelMapper = new ModelMapper();

        //Mapeamento para copiar os dados de 'TarefaRequestDto' para 'Tarefa'
        modelMapper.typeMap(TarefaRequestDTO.class, Tarefa.class).addMappings(mapper -> {          
            mapper.skip(Tarefa::setId);
        	mapper.using(ctx -> {
            	TarefaRequestDTO dto = (TarefaRequestDTO) ctx.getSource();
                try {
                    String dataHoraStr = dto.getData() + " " + dto.getHora();
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dataHoraStr);
                } catch (Exception e) {
                	 throw new IllegalArgumentException("Data e hora inválidas. Use o formato: yyyy-MM-dd HH:mm");
                }
            }).map(src -> src, Tarefa::setDataHora);
        });
        
      //Mapeamento para copiar os dados de 'Tarefa' para 'TarefaResponseDto'
        modelMapper.addMappings(new PropertyMap<Tarefa, TarefaResponseDTO>() {
            @Override
            protected void configure() {     
            	
                using(ctx -> {
                    Date dataHora = (Date) ctx.getSource();
                    return new SimpleDateFormat("yyyy-MM-dd").format(dataHora);
                }).map(source.getDataHora(), destination.getData());

              
                using(ctx -> {
                    Date dataHora = (Date) ctx.getSource();
                    return new SimpleDateFormat("HH:mm").format(dataHora);
                }).map(source.getDataHora(), destination.getHora());
            }
        });

        return modelMapper;
	}
}