function handleEnter(event){

    if(event.key === 'Enter'){
        sendMessage();
    }
}

function quickMessage(text){

    document.getElementById('message').value = text;

    sendMessage();
}

function clearChat(){

    let chatBody =
        document.getElementById('chatBody');

    chatBody.innerHTML = `

        <div class="welcome-screen"
             id="welcomeScreen">

            <h1>
                👋 નમસ્તે જી
            </h1>

            <p>
                હું તમારો ગુજરાતી  AI સહાયક છું 😄
            </p>

           

        </div>
    `;
}

function addMessage(text,type){

    let chatBody =
        document.getElementById('chatBody');

    let row =
        document.createElement('div');

    row.className =
        type === 'user'
            ? 'message-row user-row'
            : 'message-row bot-row';

    let message =
        document.createElement('div');

    message.className =
        type === 'user'
            ? 'message user-message'
            : 'message bot-message';

    message.innerText = text;

    row.appendChild(message);

    chatBody.appendChild(row);

    chatBody.scrollTop =
        chatBody.scrollHeight;
}

function sendMessage(){

    let input =
        document.getElementById('message');

    let text =
        input.value.trim();

    if(text === ''){
        return;
    }

    let welcome =
        document.getElementById('welcomeScreen');

    if(welcome){
        welcome.remove();
    }

    addMessage(text,'user');

    input.value = '';

    let chatBody =
        document.getElementById('chatBody');

    let typing =
        document.createElement('div');

    typing.className = 'typing';

    typing.id = 'typing';

    typing.innerText = 'DX-AI typing...';

    chatBody.appendChild(typing);

    chatBody.scrollTop =
        chatBody.scrollHeight;

    fetch('/api/chat',{

        method:'POST',

        headers:{
            'Content-Type':'application/json'
        },

        body:JSON.stringify({
            message:text
        })

    })

    .then(response => response.text())

    .then(data => {

        document
            .getElementById('typing')
            .remove();

        addMessage(data,'bot');

    })

    .catch(error => {

        document
            .getElementById('typing')
            .remove();

        addMessage(
            'AI service unavailable 😔',
            'bot'
        );

        console.error(error);
    });
}