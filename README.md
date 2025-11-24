✈️ AI Travel Planner (Planejador de Viagens com IA)

Um planejador de viagens inteligente que cria roteiros personalizados, detalhados e organizados em segundos, utilizando a potência do modelo Gemini 2.5 Flash do Google.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🚀 Funcionalidades

✨ Geração de Roteiros: Criação automática de itinerários baseados em destino, orçamento, dias e estilo de viagem.

🤖 Integração com IA: Utiliza a API do Google Gemini (versão v1beta / modelo 2.5-flash) para respostas rápidas e criativas.

🎨 Interface Premium: Front-end responsivo construído com HTML5, Bootstrap 5 e JavaScript, com design moderno (Glassmorphism).

💾 Histórico: Salva automaticamente os roteiros gerados no banco de dados para consulta posterior.

🗑️ Gerenciamento: Permite visualizar detalhes de viagens passadas e excluir roteiros antigos.

📱 Timeline Visual: Apresentação do roteiro em formato de linha do tempo vertical, separando atividades por períodos (Manhã, Tarde, Noite).

----------------------------------------------------------------------------------------------------------------------------------------------------

🛠️ Tecnologias Utilizadas

Back-end

Java 17

Spring Boot 3.x (Web, Data JPA, Validation)

H2 Database (Banco de dados em memória para desenvolvimento rápido)

Google Generative AI API (Integração REST)

Front-end

HTML5 & CSS3

Bootstrap 5 (Grid e Componentes)

JavaScript (ES6+) (Fetch API para comunicação com o Back-end)

Bootstrap Icons

-----------------------------------------------------------------------------------------------------------------------------------------------------

⚙️ Como Rodar o Projeto

Pré-requisitos

Java 17 instalado.

Uma chave de API do Google AI Studio (Gemini).

Configure a API Key

Abra o arquivo src/main/java/com/roteiroviagens/poo/service/GeminiService.java.

Substitua a variável API_KEY pela sua chave obtida no Google AI Studio.

Execute a aplicação

Se estiver usando IntelliJ/Eclipse, rode a classe PooApplication.java.

Ou via terminal:

./mvnw spring-boot:run

Acesse no Navegador

Abra http://localhost:8080
