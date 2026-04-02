Introdução do codigo:
Codigo refeito para poder armazenar os dados salvos da preferencia do usuario, tanto na questao de visual e usabilidade.

Fluxo da tarefa

HomeScreen → chama TaskViewModel → chama TaskRepository → chama TaskDao → acessa o Room

Fluxo da tarefa

HomeScreen → chama TaskViewModel → chama TaskRepository → chama TaskDao → acessa o Room

TaskEntity.kt

Esse arquivo define como uma tarefa é representada no banco de dados.

TaskDao.kt

É a interface que define o que tu pode fazer com a tabela de tarefas:

buscar
inserir
atualizar

AppDatabase.kt

Esse arquivo cria e gerencia o Room Database do app.

O que é Repository

É uma camada intermediária entre ViewModel e DAO.

Ou seja:

a tela não fala direto com o banco
o ViewModel fala com o Repository
o Repository fala com o DAO

ThemePreferenceManager.kt

Esse arquivo salva e busca a preferência do tema usando SharedPreferences.


Imports

Tu importou:

layouts (Box, Column)
lista (LazyColumn, items)
botão e ícones
bottom sheet
estados (remember, mutableStateOf)
coroutines (launch)
TaskCard
TaskViewModel
ThemeMode
Função auxiliar nextThemeMode

Essa função decide qual é o próximo tema ao clicar.

Lógica
SYSTEM → LIGHT
LIGHT → DARK
DARK → SYSTEM
Método usado
when
Função auxiliar themeModeLabel

Ela transforma o enum em texto visível para o usuário.

Exemplo
SYSTEM → "Tema: Automático"
LIGHT → "Tema: Claro"
DARK → "Tema: Escuro"
HomeScreen(viewModel: TaskViewModel)

Essa é a tela principal.

Conclusão:

O projeto foi separado em camadas. Na pasta data, eu defini a entidade da tarefa, o DAO com as operações SQL, o banco Room, o repositório e o gerenciador do tema com SharedPreferences. Na pasta viewmodel, eu criei o TaskViewModel, que faz a ponte entre a interface e os dados, além da TaskViewModelFactory e do enum ThemeMode. Na pasta ui, eu deixei os componentes visuais, como o card da tarefa, a tela principal e os arquivos de tema. A MainActivity inicializa o banco, o repositório, o ViewModel e aplica o tema antes de abrir a Home. Quando o usuário cria uma tarefa, a tela chama o ViewModel, o ViewModel chama o repositório, o repositório chama o DAO e o Room salva no banco. Quando o usuário troca o tema, o ViewModel salva essa escolha nas preferências do app.
