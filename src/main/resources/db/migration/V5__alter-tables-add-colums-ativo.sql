alter table medico add ativo tinyint;
alter table paciente add ativo tinyint;

update medico set ativo = 1;
update paciente set ativo = 1;


