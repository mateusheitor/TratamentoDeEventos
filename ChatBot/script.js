// Variáveis globais
const chatMessages = document.getElementById('chat-messages');
const userInput = document.getElementById('user-input');

// Função para exibir mensagem na interface do chat
function showMessage(message, sender) {
    const messageElement = document.createElement('div');
    messageElement.className = `message ${sender}`;
    messageElement.innerHTML = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Função para lidar com a mensagem do usuário
function handleUserMessage() {
    const message = userInput.value;
    showMessage(message, 'user');
    userInput.value = '';

    // Chamar a função de processamento da IA aqui e obter a resposta
    const response = getBotResponse(message);

    // Exibir a resposta do chatbot
    showMessage(response, 'bot');
}

// Função de exemplo para processamento da IA
function getBotResponse(userMessage) {
    // Aqui você pode usar o framework ou biblioteca de processamento de linguagem natural
    // para processar a mensagem do usuário e obter uma resposta do chatbot
    // Exemplo utilizando uma resposta fixa:
    return 'Olá! Como posso ajudar você hoje?';
}

// Event listener para o input do usuário
userInput.addEventListener('keydown', (event) => {
    if (event.keyCode === 13) { // 13 corresponde à tecla Enter
        handleUserMessage();
    }
});

// Iniciar a conversa com uma mensagem de boas-vindas
showMessage('Bem-vindo ao nosso chatbot de atendimento ao cliente!', 'bot');
