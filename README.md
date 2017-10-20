# Programa para uma Biblioteca

## Tela de Login:
Havera um campo para colocar o Login e a Senha do usuário, conecta com o banco(para ver se existe a conta, se não existir da erro)
e dependendo dos dados que colocar, a pessoa entra na sua conta como Administrador ou como Aluno normal, além de ter um campo para 
fazer o cadastro novo (somente de aluno), o cadastro de um novo Administrador, só entrando na conta do ADM e fazendo um novo 
cadastro do mesmo.

## Tela de cadastro:
Um espaço para colocar o Login do usuário (verifica no banco se já não existe um login igual), a senha, e-mail e o nome. O cadastro 
irá ser mandado para o banco, salvando a nova conta criada.

## Tela do Aluno:
Vai aparecer opções, ver quais livros estão disponíveis para o aluno, ver a data de entrega dos livros que pegou, deixar reservado 
determinado livro, renovação de livros.

- Livros disponíveis - Aparece uma lista, contendo todos os livros que estão disponíveis para o aluno, além da quantidade de
livros que contém na biblioteca.

- Data de entrega - Aparece uma lista contendo todos os livros que o aluno pegou, junto com a sua data de entrega, se passou da data,
aparece também quanto o aluno deve pagar por não ter entregado no dia certo.

- Reservado - O aluno pode reservar um determinado livro (após ser reservado, irá ser tirado do banco 1 livro), ele só pode reservar
o livro por 1 semana.

- Renovação - O aluno pode renovar o livro antes que a data expire, porém, ele é renovado por mais 1 semana, além de ter um limite
máximo de 10 renovações por livro.

## Tela do Administrador:
Aparece opções, adicionar um novo administrador, ver livros que contém na biblioteca, remover livros antigos, adicionar livros 
novos, ver todo mundo com data de atraso, ver os livros que foram reservados.

- Novo administrador - Adicionar um administrador novo no banco de dados.

- Livros na biblioteca - Aparece uma lista contendo todos os livros que estão na biblioteca.

- Remover livros - Remove algum livro que não vai mais ser usado.

- Adicionar livro - Adiciona um novo livro no banco de dados da biblioteca.

- Ver datas - Aparece uma lista contendo todos os alunos que estão com a data de atraso nos livros, junto com o valor que está
devendo por estar em atraso.

- Livros reservados - Aparece uma lista dos alunos que reservaram determinado livro.
