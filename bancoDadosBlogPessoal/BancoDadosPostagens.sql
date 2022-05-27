use db_blogpessoal;

insert into tb_postagens(data, texto, titulo) 
values (current_timestamp(), "Blog Pessoal funcionando!", "Minha primeira postagem");

insert into tb_postagens(data, texto, titulo) 
values (current_timestamp(), "Blog Pessoal Consultando!", "Minha segunda postagem");

select * from tb_postagens;